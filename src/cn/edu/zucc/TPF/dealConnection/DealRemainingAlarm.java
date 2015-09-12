package cn.edu.zucc.TPF.dealConnection;

import java.sql.Timestamp;
import java.util.List;
import cn.edu.zucc.TPF.Bean.AlarmDealBean;
import cn.edu.zucc.TPF.Bean.GpsDataBean;
import cn.edu.zucc.TPF.DAO.AlarmDealDAO;
import cn.edu.zucc.TPF.DAO.GpsDataDAO;
import cn.edu.zucc.TPF.View.ServerView;
import cn.edu.zucc.TPF.util.ArcDistence;
import cn.edu.zucc.TPF.util.DistenceCompute;

public class DealRemainingAlarm implements Runnable{
    private long period = 5*1000;
    private List <AlarmDealBean> alarmList;
    private AlarmDealDAO alarmDao = new AlarmDealDAO();
    private GpsDataDAO gpsDataDao = new GpsDataDAO();
    private List <GpsDataBean> gpsList;
    private float distence = 6400;
	private int index = 0;
	private DistenceCompute dCompute = new ArcDistence();
	private ServerView serverView ;
	public DealRemainingAlarm(ServerView serverView) {
		// TODO Auto-generated constructor stub
		this.serverView = serverView;
	}
	
	public void run(){
		while(true){
			alarmList = alarmDao.queryNonDealedAlarm();
			gpsList = gpsDataDao.queryGpsData();
			if(alarmList.size() > 0 && gpsList.size() > 0){
				for(int i=0; i<alarmList.size(); i++){
					distence = 6400;
					index = 0;
					float sLatitude = alarmList.get(i).getLiftlatitude();
					float sLongitude = alarmList.get(i).getLiftlongitude();
					for(int j=0;j<gpsList.size();j++){
						float dLatitude = gpsList.get(j).getLatitude();
						float dLongitude = gpsList.get(j).getLongitude();
						float distenceTemp =  dCompute.getDistenceOfTwoPoint(sLatitude, sLongitude, 
								   dLatitude, dLongitude);
						if(distence > distenceTemp){
							   distence = distenceTemp;
							   index = j;
						}
					}
					alarmList.get(i).setSenderid(gpsList.get(index).getUserid());
					alarmList.get(i).setDistence(distence);
					alarmList.get(i).setDealtag(0);
					alarmList.get(i).setRecordtime(new Timestamp(System.currentTimeMillis()));
					alarmDao.modifyAlarmData(alarmList.get(i));
					serverView.getAlarmShow().ModifyAlarmData(alarmList.get(0));
				}				
			}
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
