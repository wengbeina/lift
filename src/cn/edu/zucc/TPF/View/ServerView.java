package cn.edu.zucc.TPF.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import cn.edu.zucc.TPF.Bean.UserBean;
import cn.edu.zucc.TPF.dealConnection.ConnectionReceiveHandle;
import cn.edu.zucc.TPF.dealConnection.DealRemainingAlarm;
import cn.edu.zucc.TPF.dealConnection.ObserveThreadsHandle;
import cn.edu.zucc.TPF.util.JOperateShow;
import cn.edu.zucc.TPF.util.JRecordCount;
import cn.edu.zucc.TPF.util.ThreadNumModify;

public class ServerView extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4199221810128788689L;
	private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    //private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panelLogin = new JPanel();
    private JPanel panelLiftData = new JPanel();
    private JPanel panelRegister = new JPanel();
    private JPanel panelGpsData = new JPanel();
    private JPanel panelSelect = new JPanel();
    private JPanel panelAlarm = new JPanel();
    
    private JOperateShow loginShow = new JOperateShow();
    private JOperateShow liftDataShow = new JOperateShow();
    private JOperateShow registerShow = new JOperateShow();
    private JOperateShow gpsDataShow = new JOperateShow();
    private JOperateShow selectShow = new JOperateShow();
    private JOperateShow alarmShow = new JOperateShow();
    
    private JButton jbtStart = new JButton("����������");
    private JButton jbtStop = new JButton("�رշ�����");
    private JButton jbtSet = new JButton("����");
    private JButton jbtExit = new JButton("�˳�");
    private JButton jbtReset = new JButton("������м�¼"); 
    
    private JRecordCount recordCount = new JRecordCount();
    private JTabbedPane jtab = new JTabbedPane(JTabbedPane.TOP);
    
    private Timestamp beginTime = new Timestamp(System.currentTimeMillis());
    
    private int loginCount = 0;
    private int liftDataCount = 0;
    private int unusualDataCount = 0;
    private int registerCount = 0;
    private int gpsDataCount = 0 ;  
    private int alarmCount = 0;
 
    private Thread connectionReceive;
    private int threadNum ;//�̳߳����̵߳�����
    private int activeThreadCount = 0;//�̳߳��л�Ծ�̵߳���Ŀ����������ִ�������
    private long taskDealedCount = 0;//�̳߳����Ѿ�ִ�е�������������Ϊ������߳�һֱ�ڶ�̬�仯�����Ը�ֵ��һ������ֵ
    private long currentTaskCount = 0;//�ƻ�ִ�е��������������ǵ�ǰʱ�̵�����������
    
    private JLabel lblThread = new JLabel("�߳�����Ŀ��"+threadNum);
    private JLabel lblActiveThread = new JLabel("��ǰ��Ծ��Ŀ��"+activeThreadCount);
    private JLabel lblDealedThread = new JLabel("�Ѵ���������: "+taskDealedCount);
    private JLabel lblCurrentThread =  new JLabel("��ǰ������������"+(currentTaskCount - taskDealedCount));
    private ExecutorService executor ;
    private ThreadNumModify threadNumModify = new ThreadNumModify();
    private UserBean user;
    private SetView setView;
    
    public ServerView(UserBean user) {
		// TODO Auto-generated constructor stub    	
    	this.user = user;
    	threadNum = Integer.parseInt(threadNumModify.get("threadNum"));
    	executor = Executors.newFixedThreadPool(threadNum);
    			
    	this.setTitle("��ӭ����"+user.getUserid()+"����¼����Ԥ����ά�����ٷ�������");
    	BoxLayout box1 = new BoxLayout(panel1,BoxLayout.X_AXIS);
    	panel1.setLayout(box1);
    	panel1.add(Box.createRigidArea(new Dimension(10,20)));
    	panel1.add(jbtStart);    	
    	panel1.add(Box.createRigidArea(new Dimension(10,20)));
    	panel1.add(jbtStop);    	
    	panel1.add(Box.createGlue());
    	panel1.add(jbtSet);
    	panel1.add(Box.createRigidArea(new Dimension(10,20)));
    	panel1.add(jbtExit);
    	panel1.add(Box.createRigidArea(new Dimension(10,20)));
    	
    	jbtStart.setBackground(Color.GREEN);
    	jbtStop.setBackground(Color.RED);
    	
    	BoxLayout box2 = new BoxLayout(panel2,BoxLayout.X_AXIS);
    	panel2.setLayout(box2);
    	panel2.add(recordCount);
    	
    	BoxLayout box4 = new BoxLayout(panel4,BoxLayout.Y_AXIS);
    	panel4.setLayout(box4);
    	panel4.add(Box.createRigidArea(new Dimension(10,40)));
    	panel4.add(jbtReset);
    	panel4.add(Box.createRigidArea(new Dimension(10,20)));
    	panel4.add(lblThread);
    	panel4.add(Box.createRigidArea(new Dimension(10,20)));
    	panel4.add(lblActiveThread);
    	panel4.add(Box.createRigidArea(new Dimension(10,20)));
    	panel4.add(lblDealedThread);
    	panel4.add(Box.createRigidArea(new Dimension(10,20)));
    	panel4.add(lblCurrentThread);    	
    	
    	BoxLayout box5 = new BoxLayout(panel5,BoxLayout.Y_AXIS);
    	panel5.setLayout(box5);

    	panel5.add(jtab);
    	jtab.add("��¼��¼", panelLogin);
    	jtab.add("���ݼ�¼", panelLiftData);
    	jtab.add("Ԥ����¼", panelAlarm);
    	jtab.add("ά����ԱGPS��¼", panelGpsData);    	   	
    	
    	panelLogin.setLayout(new BorderLayout());
    	panelLogin.add(new JScrollPane(loginShow), BorderLayout.CENTER);
    	panelLiftData.setLayout(new BorderLayout());
    	panelLiftData.add(new JScrollPane(liftDataShow), BorderLayout.CENTER);
    	panelRegister.setLayout(new BorderLayout());
    	panelRegister.add(new JScrollPane(registerShow), BorderLayout.CENTER);
    	panelGpsData.setLayout(new BorderLayout());
    	panelGpsData.add(new JScrollPane(gpsDataShow), BorderLayout.CENTER);
    	panelSelect.setLayout(new BorderLayout());
    	panelSelect.add(new JScrollPane(selectShow), BorderLayout.CENTER);
    	panelAlarm.setLayout(new BorderLayout());
    	panelAlarm.add(new JScrollPane(alarmShow), BorderLayout.CENTER);
    	
    	panel2.setBackground(Color.WHITE);    	
    	
		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.SOUTH);
		this.add(panel4, BorderLayout.EAST);
		this.add(panel5, BorderLayout.CENTER);
        
		jbtStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jbtStart.setBackground(Color.GREEN);
				jbtStart.setEnabled(false);
				jbtStop.setEnabled(true);
				startServer();
			}			
		});
		jbtStop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jbtStop.setBackground(Color.RED);				
				jbtStop.setEnabled(false);
				jbtStart.setEnabled(true);
				stopServer();
			}			
		});
		
		jtab.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(jtab.getSelectedComponent() == panelLogin){
					recordCount.setLoginType(loginCount, beginTime);
				}
				else if(jtab.getSelectedComponent() == panelLiftData){
				    recordCount.setLiftConveyType(liftDataCount, unusualDataCount, beginTime);
				}
				else if(jtab.getSelectedComponent() == panelRegister){
				    recordCount.setRegisterType(registerCount, beginTime);
				}
				else if(jtab.getSelectedComponent() == panelGpsData){
				    recordCount.setGpsConveyType(gpsDataCount, beginTime);
				}
				else if(jtab.getSelectedComponent() == panelAlarm){
					recordCount.setAlarmType(alarmCount, beginTime);
				}
			}
			
		});
		
		jbtSet.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			   set();
			}			
		});
		
		jbtReset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			   reset();
			}			
		});
		
		jbtExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
               System.exit(EXIT_ON_CLOSE);
			}			
		});
		this.setBounds(150, 60, 1000, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Thread observeThread = new Thread(new ObserveThreadsHandle(this));
		observeThread.start();
	}

	//����������
	private void startServer(){
		
		try {
			connectionReceive = new Thread(new ConnectionReceiveHandle(executor, this));
			connectionReceive.start();
			executor.execute(new DealRemainingAlarm(this));
			JOptionPane.showMessageDialog(null, "�������Ѿ�������", "������ʾ",
					JOptionPane.INFORMATION_MESSAGE,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�رշ�����
	private void stopServer(){
		this.executor.shutdown();
		this.executor = null;
		this.connectionReceive = null;
		JOptionPane.showMessageDialog(null, "�������Ѿ��رգ�", "�ر���ʾ",
				JOptionPane.WARNING_MESSAGE,null);
	}
	
	public void ThreadShow(){
		lblThread.setText("�߳�����Ŀ��"+threadNum);
	    lblActiveThread.setText("��ǰ��Ծ��Ŀ��"+activeThreadCount);
		lblDealedThread.setText("�Ѵ���������: "+taskDealedCount);
		lblCurrentThread.setText("��ǰ������������"+currentTaskCount);
	}
	
	//���ð�ť�ļ����¼�
	public void set(){
		if(setView == null){
		   setView = new SetView(this);
		}
		else{
			setView.setVisible(true);
		}		
	}
	
	public void reset(){
		loginShow.setText("");
		liftDataShow.setText("");
		registerShow.setText("");
		selectShow.setText("");	
		gpsDataShow.setText("");
	}
	/**
	 * @return the loginShow
	 */
	public JOperateShow getLoginShow() {
		return loginShow;
	}

	/**
	 * @return the liftDataShow
	 */
	public JOperateShow getLiftDataShow() {
		return liftDataShow;
	}

	/**
	 * @return the loginCount
	 */
	public int getLoginCount() {
		return loginCount;
	}

	/**
	 * @param loginCount the loginCount to set
	 */
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * @return the liftDataCount
	 */
	public int getLiftDataCount() {
		return liftDataCount;
	}

	/**
	 * @param liftDataCount the liftDataCount to set
	 */
	public void setLiftDataCount(int liftDataCount) {
		this.liftDataCount = liftDataCount;
	}

	/**
	 * @return the unusualDataCount
	 */
	public int getUnusualDataCount() {
		return unusualDataCount;
	}

	/**
	 * @param unusualDataCount the unusualDataCount to set
	 */
	public void setUnusualDataCount(int unusualDataCount) {
		this.unusualDataCount = unusualDataCount;
	}

	/**
	 * @return the jtab
	 */
	public JTabbedPane getJtab() {
		return jtab;
	}
	
	/**
	 * @return the recordCount
	 */
	public JRecordCount getRecordCount() {
		return recordCount;
	}

	/**
	 * @return the beginTime
	 */
	public Timestamp getBeginTime() {
		return beginTime;
	}

	/**
	 * @return the panelLogin
	 */
	public JPanel getPanelLogin() {
		return panelLogin;
	}

	/**
	 * @return the panelLiftData
	 */
	public JPanel getPanelLiftData() {
		return panelLiftData;
	}

	/**
	 * @return the executor
	 */
	public ExecutorService getExecutor() {
		return executor;
	}

	/**
	 * @return the activeThreadCount
	 */
	public int getActiveThreadCount() {
		return activeThreadCount;
	}

	/**
	 * @return the taskDealedCount
	 */
	public long getTaskDealedCount() {
		return taskDealedCount;
	}

	/**
	 * @return the currentTaskCount
	 */
	public long getCurrentTaskCount() {
		return currentTaskCount;
	}

	/**
	 * @param activeThreadCount the activeThreadCount to set
	 */
	public void setActiveThreadCount(int activeThreadCount) {
		this.activeThreadCount = activeThreadCount;
	}

	/**
	 * @param taskDealedCount the taskDealedCount to set
	 */
	public void setTaskDealedCount(long taskDealedCount) {
		this.taskDealedCount = taskDealedCount;
	}

	/**
	 * @param currentTaskCount the currentTaskCount to set
	 */
	public void setCurrentTaskCount(long currentTaskCount) {
		this.currentTaskCount = currentTaskCount;
	}

	/**
	 * @param threadNum the threadNum to set
	 */
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	/**
	 * @return the panelRegister
	 */
	public JPanel getPanelRegister() {
		return panelRegister;
	}

	/**
	 * @return the panelSelect
	 */
	public JPanel getPanelSelect() {
		return panelSelect;
	}

	/**
	 * @return the gpsDataCount
	 */
	public int getGpsDataCount() {
		return gpsDataCount;
	}

	/**
	 * @param gpsDataCount the gpsDataCount to set
	 */
	public void setGpsDataCount(int gpsDataCount) {
		this.gpsDataCount = gpsDataCount;
	}

	/**
	 * @return the gpsDataShow
	 */
	public JOperateShow getGpsDataShow() {
		return gpsDataShow;
	}

	/**
	 * @return the panelGpsData
	 */
	public JPanel getPanelGpsData() {
		return panelGpsData;
	}

	/**
	 * @return the alarmCount
	 */
	public int getAlarmCount() {
		return alarmCount;
	}

	/**
	 * @param alarmCount the alarmCount to set
	 */
	public void setAlarmCount(int alarmCount) {
		this.alarmCount = alarmCount;
	}

	/**
	 * @return the panelAlarm
	 */
	public JPanel getPanelAlarm() {
		return panelAlarm;
	}

	/**
	 * @return the alarmShow
	 */
	public JOperateShow getAlarmShow() {
		return alarmShow;
	}
}
