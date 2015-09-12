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
		this.setText("��ͼ���ͣ���¼"+tab+"�ۼƵ�¼������"+loginCount+tab+"ʱ��Σ���"+beginTime
				+tab+"��"+endTime);
	}
	
	public void setLiftConveyType(int liftDataCount, int unusualDataCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("��ͼ���ͣ����ݴ�������"+tab+"�ۼƽ������ݰ���"+liftDataCount+tab+"����Ԥ�������ݰ���"
				+unusualDataCount+tab+"ʱ��Σ���"+beginTime+tab+"��"+endTime);
	}
	
	public void setGpsConveyType(int gpsDataCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("��ͼ���ͣ�Gps��λ����"+tab+"�ۼƽ������ݰ���"+gpsDataCount+tab+"ʱ��Σ���"+beginTime+tab+"��"+endTime);
	}
	
	public void setAlarmType(int alarmCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("��ͼ���ͣ�Ԥ������"+tab+"�ۼƼ�¼���ݰ���"+alarmCount+tab+"ʱ��Σ���"+beginTime+tab+"��"+endTime);
	}
	
	public void setRegisterType(int registerCount, Timestamp beginTime){
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String tab = "     ";
		this.setText("��ͼ���ͣ�ע������"+tab+"�ۼƽ������ݰ���"+registerCount+tab+"ʱ��Σ���"+beginTime+tab+"��"+endTime);
	}
}
