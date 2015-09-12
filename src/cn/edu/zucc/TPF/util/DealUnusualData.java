package cn.edu.zucc.TPF.util;

import java.sql.Timestamp;

import cn.edu.zucc.TPF.Bean.AlarmDealBean;
import cn.edu.zucc.TPF.Bean.GpsDataBean;
import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.LiftUnusualBean;
import cn.edu.zucc.TPF.DAO.AlarmDealDAO;

public class DealUnusualData {

	public DealUnusualData() {
		// TODO Auto-generated constructor stub
	}
    
	public static AlarmDealBean createAlarmData(LiftUnusualBean unusualData, GpsDataBean gpsChoosed ,LiftBean lift, 
			float distence){
		   AlarmDealBean alarm = new AlarmDealBean();
		   AlarmDealDAO alarmDao = new AlarmDealDAO();
		   alarm.setLiftid(unusualData.getLiftid());
		   alarm.setSenderid(gpsChoosed.getUserid());
		   alarm.setAccx(unusualData.getAccx());
		   alarm.setAccy(unusualData.getAccy());
		   alarm.setAccz(unusualData.getAccz());
		   alarm.setAddress(lift.getAddress());
		   alarm.setLiftlatitude(lift.getLatitude());
		   alarm.setLiftlongitude(lift.getLongitude());
		   alarm.setDistence(distence);
		   alarm.setWarninglevel(unusualData.getWarninglevel());
		   alarm.setUnusualtime(unusualData.getRecordtime());
		   alarm.setRecordtime(new Timestamp(System.currentTimeMillis()));
		   alarm.setDealtag(0);
		   alarmDao.insertSingleData(alarm);	
		   
		   return alarm;
	}
	
	public static AlarmDealBean createAlarmData(LiftUnusualBean unusualData, LiftBean lift){
		   AlarmDealBean alarm = new AlarmDealBean();
		   AlarmDealDAO alarmDao = new AlarmDealDAO();
		   alarm.setLiftid(unusualData.getLiftid());
		   alarm.setAccx(unusualData.getAccx());
		   alarm.setAccy(unusualData.getAccy());
		   alarm.setAccz(unusualData.getAccz());
		   alarm.setAddress(lift.getAddress());
		   alarm.setLiftlatitude(lift.getLatitude());
		   alarm.setLiftlongitude(lift.getLongitude());
		   alarm.setWarninglevel(unusualData.getWarninglevel());
		   alarm.setUnusualtime(unusualData.getRecordtime());
		   alarm.setRecordtime(new Timestamp(System.currentTimeMillis()));
		   alarm.setDealtag(3);
		   alarmDao.insertSingleData(alarm);
		   
		   return alarm;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
