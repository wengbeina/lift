package cn.edu.zucc.TPF.util;

import java.sql.Timestamp;
import javax.swing.JLabel;

public class JRecordCount extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JRecordCount() {
		// TODO Auto-generated constructor stub
	}
	
	public void setLoginType(int loginCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("视图类型：登录"+tab+"累计登录数量："+loginCount+tab+"时间段：从"+beginTime
				+tab+"到"+endTime);
	}
	
	public void setLiftConveyType(int liftDataCount, int unusualDataCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("视图类型：电梯传输数据"+tab+"累计接收数据包："+liftDataCount+tab+"出现预警的数据包："
				+unusualDataCount+tab+"时间段：从"+beginTime+tab+"到"+endTime);
	}
	
	public void setGpsConveyType(int gpsDataCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("视图类型：Gps定位数据"+tab+"累计接收数据包："+gpsDataCount+tab+"时间段：从"+beginTime+tab+"到"+endTime);
	}
	
	public void setAlarmType(int alarmCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("视图类型：预警数据"+tab+"累计记录数据包："+alarmCount+tab+"时间段：从"+beginTime+tab+"到"+endTime);
	}
	
	public void setRegisterType(int registerCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("视图类型：注册数据"+tab+"累计接收数据包："+registerCount+tab+"时间段：从"+beginTime+tab+"到"+endTime);
	}
}
