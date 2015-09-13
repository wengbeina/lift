package cn.edu.zucc.TPF.dealConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zucc.TPF.Bean.AlarmDealBean;
import cn.edu.zucc.TPF.Bean.GpsDataBean;
import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.LiftDataBean;
import cn.edu.zucc.TPF.Bean.LiftUnusualBean;
import cn.edu.zucc.TPF.Bean.LogBean;
import cn.edu.zucc.TPF.Bean.UserBean;
import cn.edu.zucc.TPF.DAO.AlarmDealDAO;
import cn.edu.zucc.TPF.DAO.GpsDataDAO;
import cn.edu.zucc.TPF.DAO.LiftDAO;
import cn.edu.zucc.TPF.DAO.LiftDataDAO;
import cn.edu.zucc.TPF.DAO.LiftUnusualDAO;
import cn.edu.zucc.TPF.DAO.LogDAO;
import cn.edu.zucc.TPF.DAO.UserDAO;
import cn.edu.zucc.TPF.View.ServerView;
import cn.edu.zucc.TPF.util.ArcDistence;
import cn.edu.zucc.TPF.util.CrcCompute;
import cn.edu.zucc.TPF.util.DealUnusualData;
import cn.edu.zucc.TPF.util.DistenceCompute;
import cn.edu.zucc.TPF.util.LiftDataState;
import cn.edu.zucc.TPF.util.ObjectChange;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConnectionDealHandle implements Runnable {
	private CrcCompute crcCompute;
	private Socket socket;
	private ObjectMapper map;
	private String content;
	private ServerView serverView;
	private AlarmDealBean alarm;

	public ConnectionDealHandle(ServerView serverView, Socket socket) {
		this.serverView = serverView;
		this.socket = socket;
		// TODO Auto-generated constructor stub
	}

	public void run() {
		map = new ObjectMapper();
		crcCompute = new CrcCompute(CrcCompute.CRC_16);

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = null;
			message = br.readLine();

			if (message.length() > 6) {
				System.out.println(message);
				// 分流处理
				char[] messageToArray = message.toCharArray();
				String conveyCrc = new String(messageToArray, messageToArray.length - 4, 4);
				String conveyContent = new String(messageToArray, 0, messageToArray.length - 4);
				int computeCrc = crcCompute.GetDataCrc(conveyContent.getBytes());

				if (crcCompute.ChangeToHexCrc(computeCrc).equals(conveyCrc)) {
					String type = new String(messageToArray, 0, 2);
					content = new String(messageToArray, 2, messageToArray.length - 6);
					if (type.equals("00")) {
						// 电梯登录处理
						dealLiftLogin();
					} else if (type.equals("01")) {
						// 电梯数据存储处理
						dealLiftData();
					} else if (type.equals("02")) {
						// 维保人员注册处理
						dealUserRegister();
					} else if (type.equals("03")) {
						// 维保人员登录处理
						dealUserLogin();
					} else if (type.equals("04")) {
						// 维保人员Gps定位信息处理
						dealGpsData();
					} else if (type.equals("05")) {
						// 维保人员处理好故障后反馈（取消预警信息）
						breakDown();
					} else {
						// 其他处理
						dealOther();
					}
				}

				else {
					System.out.println("服务器端计算得到的效验码为：" + crcCompute.ChangeToHexCrc(computeCrc));
					System.out.println("效验码错误！");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dealLiftLogin() {
		try {
			LiftBean liftLogin = map.readValue(content, LiftBean.class);
			LiftDAO liftDao = new LiftDAO();
			LiftBean lift = liftDao.loadLift(liftLogin.getLiftid());
			String checkResult = null;
			// 2表示电梯编号不存在，1表示电梯编号和密码都正确——登录成功，0表示登录密码错误
			if (lift == null) {
				checkResult = "2";
			} else {
				if (liftLogin.getPwd().equals(lift.getPwd())) {
					checkResult = "1";
					serverView.setLoginCount(serverView.getLoginCount() + 1);
					serverView.getLoginShow().InsertLiftLogin(liftLogin);

					if (serverView.getJtab().getSelectedComponent() == serverView.getPanelLogin()) {
						serverView.getRecordCount().setLoginType(serverView.getLoginCount(), serverView.getBeginTime());
					}

				} else
					checkResult = "0";
			}
			PrintWriter pw = getWriter(socket);
			pw.println(checkResult);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dealLiftData() {
		try {
			List<Map<String, Object>> liftDataSource = map.readValue(content, List.class);
			List<LiftDataBean> liftDataList = new ArrayList<LiftDataBean>();
			List<LiftUnusualBean> liftUnusualList = new ArrayList<LiftUnusualBean>();
			LiftDAO liftDao = new LiftDAO();
			LiftBean lift = liftDao.loadLift(liftDataSource.get(0).get("liftid").toString());
			System.out.println("电梯ID：" + lift.getLiftid());
			LiftDataState judge = new LiftDataState();

			for (int i = 0; i < liftDataSource.size(); i++) {
				LiftDataBean temp = ObjectChange.MapToLiftDataBean(liftDataSource.get(i));
				// 判断预警等级
				int warningLevel = judge.JudgeState(lift, temp);
				if (warningLevel > 0) {
					LiftUnusualBean unusual = new LiftUnusualBean(temp, warningLevel);
					liftUnusualList.add(unusual);
				}
				liftDataList.add(temp);
			}
			// 将电梯所有数据插入tbl_liftdata,将异常数据插入tbl_unusualdata,将处理的情况插入日志表tbl_log
			LiftDataDAO liftDataDao = new LiftDataDAO();
			LiftUnusualDAO unusualDao = new LiftUnusualDAO();
			liftDataDao.insertDataList(liftDataList);
			// 对于异常数据的处理
			if (liftUnusualList.size() > 0) {
				LiftUnusualBean unusualData = liftUnusualList.get(0);
				LiftBean liftTemp = liftDao.loadLift(unusualData.getLiftid());
				float sLatitude = liftTemp.getLatitude();
				float sLongitude = liftTemp.getLongitude();
				DistenceCompute dCompute = new ArcDistence();
				GpsDataDAO gpsDataDao = new GpsDataDAO();
				List<GpsDataBean> gpsList = gpsDataDao.queryGpsData();
				if (gpsList.size() > 0) {
					float distence = 6400;
					int index = 0;
					for (int i = 0; i < gpsList.size(); i++) {
						float dLatitude = gpsList.get(i).getLatitude();
						float dLongitude = gpsList.get(i).getLongitude();
						float distenceTemp = dCompute.getDistenceOfTwoPoint(sLatitude, sLongitude, dLatitude,
								dLongitude);
						if (distence > distenceTemp) {
							distence = distenceTemp;
							index = i;
						}
					}

					GpsDataBean gpsChoosed = gpsList.get(index);
					// 在预警表中插入数据
					alarm = DealUnusualData.createAlarmData(unusualData, gpsChoosed, lift, distence);
				} else {
					alarm = DealUnusualData.createAlarmData(unusualData, lift);
				}

				// 在图形管理界面的显示
				serverView.setAlarmCount(serverView.getAlarmCount() + 1);
				if (serverView.getJtab().getSelectedComponent() == serverView.getPanelAlarm()) {
					serverView.getRecordCount().setAlarmType(serverView.getAlarmCount(), serverView.getBeginTime());
				} else
					;
				serverView.getAlarmShow().InsertAlarmData(alarm);

				unusualDao.insertDataList(liftUnusualList);
			}
			// 将处理的结果反映在图像管理界面上面
			if (liftDataList.size() > 0) {
				serverView.setLiftDataCount(serverView.getLiftDataCount() + 1);
				if (liftUnusualList.size() > 0) {
					serverView.setUnusualDataCount(serverView.getUnusualDataCount() + 1);
				} else
					;
				if (serverView.getJtab().getSelectedComponent() == serverView.getPanelLiftData()) {
					serverView.getRecordCount().setLiftConveyType(serverView.getLiftDataCount(),
							serverView.getUnusualDataCount(), serverView.getBeginTime());
				} else
					;
				serverView.getLiftDataShow().InsertLiftData(liftDataList, liftUnusualList.size());
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将来版本添加的辅助功能
	private void dealUserRegister() {

	}

	private void dealUserLogin() {
		try {
			UserBean userConveyed = map.readValue(content, UserBean.class);
			UserDAO userDao = new UserDAO();
			UserBean user = userDao.loadUser(userConveyed.getUserid());
			String checkResult = null;
			// 2表示用户名不存在，1表示用户名和密码都正确——登录成功，0表示登录密码错误
			if (user == null || user.getUsertype() != userConveyed.getUsertype()) {
				checkResult = "2";
			} else {
				if (user.getUserpwd().equals(userConveyed.getUserpwd())) {
					checkResult = "1";
					serverView.setLoginCount(serverView.getLoginCount() + 1);
					serverView.getLoginShow().InsertUserLogin(user);

					if (serverView.getJtab().getSelectedComponent() == serverView.getPanelLogin()) {
						serverView.getRecordCount().setLoginType(serverView.getLoginCount(), serverView.getBeginTime());
						serverView.getRecordCount().setGpsConveyType(serverView.getGpsDataCount(),
								serverView.getBeginTime());
					}
				} else
					checkResult = "0";
			}
			System.out.println(checkResult);
			PrintWriter pw = getWriter(socket);
			pw.println(checkResult);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 排除故障（取消预警信息）
	private void breakDown() {
		// TODO Auto-generated method stub
		try {
			LiftUnusualBean liftUnusual = map.readValue(content, LiftUnusualBean.class);
			
			
//			returnUnusualData();

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void dealGpsData() {
		try {
			GpsDataBean gpsData = map.readValue(content, GpsDataBean.class);
			InetAddress addr = socket.getInetAddress();
			// 在此将gpsData的客户端IP设置为从Socket中获得的，这样做可以适用外网通信
			gpsData.setClientip(addr.getHostAddress());

			GpsDataDAO gpsDataDao = new GpsDataDAO();
			gpsDataDao.insertSingleData(gpsData);

			returnUnusualData(gpsData.getUserid());

			serverView.setGpsDataCount(serverView.getGpsDataCount() + 1);
			serverView.getGpsDataShow().InsertGpsData(gpsData);
			if (serverView.getJtab().getSelectedComponent() == serverView.getPanelGpsData()) {
				serverView.getRecordCount().setGpsConveyType(serverView.getGpsDataCount(), serverView.getBeginTime());
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void returnUnusualData(String userid) {

		// 查询预警信息表，并将结果返回给客户端
		AlarmDealDAO alarmDao = new AlarmDealDAO();
		List<AlarmDealBean> alarmList = alarmDao.queryAlarmData(userid);
		String messageReturn = null;
		PrintWriter pw;
		try {
			pw = getWriter(socket);
			if (alarmList != null && alarmList.size() > 0) {
				messageReturn = map.writeValueAsString(alarmList);
			} else {
				messageReturn = "NOITEM";
			}
			int crcResult = crcCompute.GetDataCrc(messageReturn.getBytes());
			String crcToHex = crcCompute.ChangeToHexCrc(crcResult);
			messageReturn = messageReturn + crcToHex;
			pw.println(messageReturn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 将来版本添加的拓展功能
	public void dealOther() {

	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}
}
