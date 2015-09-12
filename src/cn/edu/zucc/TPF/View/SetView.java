package cn.edu.zucc.TPF.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.TPF.util.ThreadNumModify;

public class SetView extends JFrame{
	private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    
    private JLabel lblThreadNum = new JLabel("线程池的最大线程数目：");
    private JTextField textThreadNum = new JTextField(6);
    
    private JButton jbtSave = new JButton("更改");
    private JButton jbtReset = new JButton("重置");
    private ServerView serverView;
    
    private ThreadNumModify threadNumModify = new ThreadNumModify();
    
	public SetView(ServerView serverView) {
		// TODO Auto-generated constructor stub
		this.serverView = serverView;
		GridLayout lay1 = new GridLayout(1,2);
		lay1.setHgap(30);
		lay1.setVgap(10);
		
		panel1.setLayout(lay1);
		//panel2.setLayout(lay1);
		
		panel1.add(lblThreadNum);
		panel1.add(textThreadNum);
		
		panel2.add(jbtSave);
		panel2.add(jbtReset);
		
		this.add(panel1,BorderLayout.NORTH);
		this.add(panel3,BorderLayout.SOUTH);
		this.add(panel2,BorderLayout.CENTER);
		
		this.setTitle("线程池的设置");	
		this.setBounds(500, 320, 350, 120);
	    this.setVisible(true);
	    
	    jbtSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save();
			}
		});	
	    
	    jbtReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reset();
			}
		});	
	}
	
	private void save(){
		if(textThreadNum.getText() == null || textThreadNum.getText().equals("")){
			    JOptionPane.showMessageDialog(null, "线程数目不能为空", "设置提示",
					JOptionPane.WARNING_MESSAGE,null);
		}
		else{
			String numSet = textThreadNum.getText();
			try{
			   int threadNum = Integer.parseInt(numSet);
			   threadNumModify.put("threadNum", numSet);
			   ThreadPoolExecutor temp = (ThreadPoolExecutor)serverView.getExecutor();			   
			   /*temp.setMaximumPoolSize(threadNum);*/
			   serverView.setThreadNum(threadNum);
			   
			   JOptionPane.showMessageDialog(null, "更改成功！", "设置提示",
						JOptionPane.INFORMATION_MESSAGE,null);
			   this.setVisible(false);
			   
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "有非数字字符！", "设置提示",
						JOptionPane.WARNING_MESSAGE,null);
			}			
		}
	}
	
	private void reset(){
		textThreadNum.setText("");
	}
}
