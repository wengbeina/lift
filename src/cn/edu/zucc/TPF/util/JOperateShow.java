package cn.edu.zucc.TPF.util;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import cn.edu.zucc.TPF.Bean.AlarmDealBean;
import cn.edu.zucc.TPF.Bean.GpsDataBean;
import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.LiftDataBean;
import cn.edu.zucc.TPF.Bean.UserBean;

public class JOperateShow extends JTextPane{

	/**Tan
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JOperateShow() {
		// TODO Auto-generated constructor stub
	}
	
	public void InsertLiftLogin(LiftBean lift){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "     ";
		Document doc = this.getStyledDocument();
		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
		content = "���ݱ�ţ�"+lift.getLiftid()+tab+"��¼ʱ�䣺"+loginTime+"\n";
		StyleConstants.setForeground(set, Color.GREEN);
		StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    try {
			doc.insertString(doc.getLength(), content, set);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	public void InsertUserLogin(UserBean user){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "     ";
		Document doc = this.getStyledDocument();
		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
		content = "�û�����"+user.getUserid()+tab+"��¼ʱ�䣺"+loginTime+"\n";
		StyleConstants.setForeground(set, Color.GREEN);
		StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    try {
			doc.insertString(doc.getLength(), content, set);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
    
	public void InsertLiftData(List<LiftDataBean> liftDataList, int unusualCount){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String headContent;
		String content ;
		String tab = "     ";
		LiftDataBean liftData = liftDataList.get(0);		
		Document doc = this.getStyledDocument();
		
		headContent = "���ݱ�ţ�"+liftData.getLiftid()+tab+liftData.getRecordtime()+tab;
		content = "�������ݸ�����"+liftDataList.size()+tab+"�쳣���ݸ�����";
		
		if( unusualCount > 0){
		    StyleConstants.setForeground(set, Color.RED);//����������ɫ
		    content += unusualCount +"\n";
		}
		else{
			StyleConstants.setForeground(set, Color.GREEN);
			content += "0"+"\n";
		}		
	    StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    
	    try {
			doc.insertString(doc.getLength(), headContent+content, set);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	public void InsertLiftRegister(LiftBean lift){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "     ";		
		Document doc = this.getStyledDocument();
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		content = "���ݱ�ţ�"+lift.getLiftid()+tab+"ע��ʱ�䣺"+currentTime+"\n";
				
		StyleConstants.setForeground(set, Color.GREEN);
	    StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    
	    try {
			doc.insertString(doc.getLength(), content, set);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	public void InsertUserRegister(UserBean user){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "     ";		
		Document doc = this.getStyledDocument();
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		content = "�û�����"+user.getUserid()+tab+"ע��ʱ�䣺"+currentTime+"\n";
				
		StyleConstants.setForeground(set, Color.GREEN);
	    StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    
	    try {
			doc.insertString(doc.getLength(), content, set);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	public void InsertGpsData(GpsDataBean gps){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "  ";		
		Document doc = this.getStyledDocument();
		content = "�û�����"+gps.getUserid()+tab+"γ�ȣ�"+gps.getLatitude()+tab+"���ȣ�"+
		     gps.getLongitude()+tab+"IP��"+gps.getClientip()+tab+"����ʱ�䣺"+gps.getRecordtime()+"\n";
				
		StyleConstants.setForeground(set, Color.BLUE);
	    StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    
	    try {
			doc.insertString(doc.getLength(), content, set);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	public void InsertAlarmData(AlarmDealBean alarm){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "  ";		
		Document doc = this.getStyledDocument();
		if(alarm.getDealtag() == 0){
			content = "���ݱ�ţ�"+alarm.getLiftid()+tab+"Ԥ�������ˣ�"+alarm.getSenderid()+
					tab+"��¼ʱ�䣺"+alarm.getRecordtime()+tab+"����״̬��"+"������"+"\n";
			StyleConstants.setForeground(set, Color.BLUE);
		}
		else if(alarm.getDealtag() == 1){
			content = "���ݱ�ţ�"+alarm.getLiftid()+tab+"Ԥ�������ˣ�"+alarm.getSenderid()+
					tab+"��¼ʱ�䣺"+alarm.getRecordtime()+tab+"����״̬��"+"�������"+"\n";
			StyleConstants.setForeground(set, Color.GREEN);
		}
		else{
			content = "���ݱ�ţ�"+alarm.getLiftid()+tab+"Ԥ�������ˣ�����"+tab+"��¼ʱ�䣺"+
		            alarm.getRecordtime()+tab+"����״̬��"+"�ȴ�����"+"\n";
			StyleConstants.setForeground(set, Color.RED);
		}
	    StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    
	    try {
			doc.insertString(doc.getLength(), content, set);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	public void ModifyAlarmData(AlarmDealBean alarm){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "  ";		
		Document doc = this.getStyledDocument();
		content = "���ݱ�ţ�"+alarm.getLiftid()+tab+"Ԥ�������ˣ�"+alarm.getSenderid()+
					tab+"��¼ʱ�䣺"+alarm.getRecordtime()+tab+"����״̬��"+"�ɵȴ�����תΪ������"+"\n";
		StyleConstants.setForeground(set, Color.BLUE);
	    StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    
	    try {
			doc.insertString(doc.getLength(), content, set);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	
	public void SelectRecord(String type){
		SimpleAttributeSet set = new SimpleAttributeSet();
		String content ;
		String tab = "     ";		
		Document doc = this.getStyledDocument();
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		content = "��ѯ���ͣ�"+ type + tab +"��ѯʱ�䣺"+ currentTime +"\n";
				
		StyleConstants.setForeground(set, Color.GREEN);
	    StyleConstants.setFontSize(set, 14);//���������С
	    StyleConstants.setFontFamily(set, "����");//������������
	    StyleConstants.setBold(set, false);
	    StyleConstants.setItalic(set, false);
	    
	    try {
			doc.insertString(doc.getLength(), content, set);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
}
