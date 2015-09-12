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
		content = "电梯编号："+lift.getLiftid()+tab+"登录时间："+loginTime+"\n";
		StyleConstants.setForeground(set, Color.GREEN);
		StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
		content = "用户名："+user.getUserid()+tab+"登录时间："+loginTime+"\n";
		StyleConstants.setForeground(set, Color.GREEN);
		StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
		
		headContent = "电梯编号："+liftData.getLiftid()+tab+liftData.getRecordtime()+tab;
		content = "电梯数据个数："+liftDataList.size()+tab+"异常数据个数：";
		
		if( unusualCount > 0){
		    StyleConstants.setForeground(set, Color.RED);//设置文字颜色
		    content += unusualCount +"\n";
		}
		else{
			StyleConstants.setForeground(set, Color.GREEN);
			content += "0"+"\n";
		}		
	    StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
		content = "电梯编号："+lift.getLiftid()+tab+"注册时间："+currentTime+"\n";
				
		StyleConstants.setForeground(set, Color.GREEN);
	    StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
		content = "用户名："+user.getUserid()+tab+"注册时间："+currentTime+"\n";
				
		StyleConstants.setForeground(set, Color.GREEN);
	    StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
		content = "用户名："+gps.getUserid()+tab+"纬度："+gps.getLatitude()+tab+"经度："+
		     gps.getLongitude()+tab+"IP："+gps.getClientip()+tab+"传输时间："+gps.getRecordtime()+"\n";
				
		StyleConstants.setForeground(set, Color.BLUE);
	    StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
			content = "电梯编号："+alarm.getLiftid()+tab+"预警处理人："+alarm.getSenderid()+
					tab+"记录时间："+alarm.getRecordtime()+tab+"处理状态："+"处理中"+"\n";
			StyleConstants.setForeground(set, Color.BLUE);
		}
		else if(alarm.getDealtag() == 1){
			content = "电梯编号："+alarm.getLiftid()+tab+"预警处理人："+alarm.getSenderid()+
					tab+"记录时间："+alarm.getRecordtime()+tab+"处理状态："+"处理完毕"+"\n";
			StyleConstants.setForeground(set, Color.GREEN);
		}
		else{
			content = "电梯编号："+alarm.getLiftid()+tab+"预警处理人：暂无"+tab+"记录时间："+
		            alarm.getRecordtime()+tab+"处理状态："+"等待处理"+"\n";
			StyleConstants.setForeground(set, Color.RED);
		}
	    StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
		content = "电梯编号："+alarm.getLiftid()+tab+"预警处理人："+alarm.getSenderid()+
					tab+"记录时间："+alarm.getRecordtime()+tab+"处理状态："+"由等待处理转为处理中"+"\n";
		StyleConstants.setForeground(set, Color.BLUE);
	    StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
		content = "查询类型："+ type + tab +"查询时间："+ currentTime +"\n";
				
		StyleConstants.setForeground(set, Color.GREEN);
	    StyleConstants.setFontSize(set, 14);//设置字体大小
	    StyleConstants.setFontFamily(set, "宋体");//设置字体类型
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
