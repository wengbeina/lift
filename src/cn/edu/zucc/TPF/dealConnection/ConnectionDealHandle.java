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
				// ��������
				char[] messageToArray = message.toCharArray();
				String conveyCrc = new String(messageToArray, messageToArray.length - 4, 4);
				String conveyContent = new String(messageToArray, 0, messageToArray.length - 4);
				int computeCrc = crcCompute.GetDataCrc(conveyContent.getBytes());

				if (crcCompute.ChangeToHexCrc(computeCrc).equals(conveyCrc)) {
					String type = new String(messageToArray, 0, 2);
					content = new String(messageToArray, 2, messageToArray.length - 6);
					if (type.equals("00")) {
						// ���ݵ�¼����
						dealLiftLogin();
					} else if (type.equals("01")) {
						// �������ݴ洢����
						dealLiftData();
					} else if (type.equals("02")) {
						// ά����Աע�ᴦ��
						dealUserRegister();
					} else if (type.equals("03")) {
						// ά����Ա��¼����
						dealUserLogin();
					} else if (type.equals("04")) {
						// ά����ԱGps��λ��Ϣ����
						dealGpsData();
					} else if (type.equals("05")) {
						// ά����Ա����ù��Ϻ�����ȡ��Ԥ����Ϣ��
						breakDown();
					} else {
						// ��������
						dealOther();
					}
				}

				else {
					System.out.println("�������˼���õ���Ч����Ϊ��" + crcCompute.ChangeToHexCrc(computeCrc));
					System.out.println("Ч�������");
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
			// 2��ʾ���ݱ�Ų����ڣ�1��ʾ���ݱ�ź����붼��ȷ������¼�ɹ���0��ʾ��¼�������
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
			System.out.println("����ID��" + lift.getLiftid());
			LiftDataState judge = new LiftDataState();

			for (int i = 0; i < liftDataSource.size(); i++) {
				LiftDataBean temp = ObjectChange.MapToLiftDataBean(liftDataSource.get(i));
				// �ж�Ԥ���ȼ�
				int warningLevel = judge.JudgeState(lift, temp);
				if (warningLevel > 0) {
					LiftUnusualBean unusual = new LiftUnusualBean(temp, warningLevel);
					liftUnusualList.add(unusual);
				}
				liftDataList.add(temp);
			}
			// �������������ݲ���tbl_liftdata,���쳣���ݲ���tbl_unusualdata,����������������־��tbl_log
			LiftDataDAO liftDataDao = new LiftDataDAO();
			LiftUnusualDAO unusualDao = new LiftUnusualDAO();
			liftDataDao.insertDataList(liftDataList);
			// �����쳣���ݵĴ���
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
					// ��Ԥ�����в�������
					alarm = DealUnusualData.createAlarmData(unusualData, gpsChoosed, lift, distence);
				} else {
					alarm = DealUnusualData.createAlarmData(unusualData, lift);
				}

				// ��ͼ�ι���������ʾ
				serverView.setAlarmCount(serverView.getAlarmCount() + 1);
				if (serverView.getJtab().getSelectedComponent() == serverView.getPanelAlarm()) {
					serverView.getRecordCount().setAlarmType(serverView.getAlarmCount(), serverView.getBeginTime());
				} else
					;
				serverView.getAlarmShow().InsertAlarmData(alarm);

				unusualDao.insertDataList(liftUnusualList);
			}
			// ������Ľ����ӳ��ͼ������������
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

	// �����汾��ӵĸ�������
	private void dealUserRegister() {

	}

	private void dealUserLogin() {
		try {
			UserBean userConveyed = map.readValue(content, UserBean.class);
			UserDAO userDao = new UserDAO();
			UserBean user = userDao.loadUser(userConveyed.getUserid());
			String checkResult = null;
			// 2��ʾ�û��������ڣ�1��ʾ�û��������붼��ȷ������¼�ɹ���0��ʾ��¼�������
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

	// �ų����ϣ�ȡ��Ԥ����Ϣ��
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
			// �ڴ˽�gpsData�Ŀͻ���IP����Ϊ��Socket�л�õģ�������������������ͨ��
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

		// ��ѯԤ����Ϣ������������ظ��ͻ���
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

	// �����汾��ӵ���չ����
	public void dealOther() {

	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}
}
