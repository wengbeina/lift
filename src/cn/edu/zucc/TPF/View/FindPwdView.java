package cn.edu.zucc.TPF.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cn.edu.zucc.TPF.Bean.UserBean;
import cn.edu.zucc.TPF.DAO.UserDAO;

public class FindPwdView extends JFrame{
	private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    
    private JLabel lbluserid = new JLabel("用户名：");
    private JLabel lblemail = new JLabel("邮箱：");
    private JLabel lblpwd = new JLabel("新密码：");
    private JLabel lblrepeatpwd = new JLabel("确认密码：");
    
    private JLabel checkuserid = new JLabel("用户名不能为空！");
    private JLabel checkemail = new JLabel("邮箱不能为空！");
    private JLabel checkpwd = new JLabel("不少于六字符！");
    private JLabel checkrepeatpwd = new JLabel("不少于六字符！");
    
    private JTextField textuserid = new JTextField(6);
    private JTextField textemail = new JTextField(6);
    private JPasswordField textpwd = new JPasswordField(6);
    private JPasswordField textrepeatpwd = new JPasswordField(6);
    
    private JButton jbtOK = new JButton("找回");
    private JButton jbtreset=new JButton("重置");    
	
	public FindPwdView() {
		// TODO Auto-generated constructor stub
		GridLayout grid = new GridLayout(4,1);
		grid.setHgap(10);
		grid.setVgap(10);
		 
		panel1.setLayout(grid);
		panel2.setLayout(grid);
		panel3.setLayout(grid);
		
		panel1.add(lbluserid);
		panel1.add(lblemail);
		panel1.add(lblpwd);
		panel1.add(lblrepeatpwd);
		
		panel2.add(textuserid);
		panel2.add(textemail);
		panel2.add(textpwd);
		panel2.add(textrepeatpwd);
		
		panel3.add(checkuserid);
		panel3.add(checkemail);
		panel3.add(checkpwd);
		panel3.add(checkrepeatpwd);
		
		panel4.add(jbtOK);
		panel4.add(jbtreset);
		
		this.setTitle("找回密码：");
		this.add(panel1,BorderLayout.WEST);
		this.add(panel2,BorderLayout.CENTER);
		this.add(panel3,BorderLayout.EAST);
		this.add(panel4,BorderLayout.SOUTH);
		
		this.setBounds(340, 160, 380, 250);
		this.setVisible(true);
		
		textuserid.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				judgeisEmpty(textuserid,checkuserid);
			}
			public void removeUpdate(DocumentEvent e){
				judgeisEmpty(textuserid,checkuserid);
			}
		});
		
		textemail.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				judgeisEmpty(textemail,checkemail);
			}
			public void removeUpdate(DocumentEvent e){
				judgeisEmpty(textemail,checkemail);
			}
		});
		
		textpwd.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				judgePwd();
			}
			public void removeUpdate(DocumentEvent e){
				judgePwd();
			}
		});
		
		textrepeatpwd.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				checkRepeatPwd();
			}
			public void removeUpdate(DocumentEvent e){
				checkRepeatPwd();
			}
		});
		
		jbtOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				findPwd();
			}
		});
		
		jbtreset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reset();
			}
		});	
	}
	
	private void judgePwd(){
		char[] pwd = textpwd.getPassword();
		if(pwd.length == 0){
			checkpwd.setText("密码不能为空！");
		}
		else if(pwd.length > 0&&pwd.length < 6){
			checkpwd.setText("不少于六字符！");
		}
		else if(pwd.length>=6 && pwd.length<9){
			checkpwd.setText("密码强度较弱！");
		}
		else if(pwd.length >= 9){
			checkpwd.setText("密码强度较强！");
		}
	}
	
	private void judgeisEmpty(JTextField jtext,JLabel show){
		String check = jtext.getText(); 
		if(check.length() == 0){
			show.setText("不能为空！");
		}
		else if(check.length() < 6)
			show.setText("不少于6字符！");
		else
			show.setText("符合要求");
	}
	
	private void checkRepeatPwd(){
		String pwd = new String(textpwd.getPassword());
		String repeatpwd = new String(textrepeatpwd.getPassword());
		if(pwd.equals(repeatpwd)){
			checkrepeatpwd.setText("前后密码一致");
		}
		else
			checkrepeatpwd.setText("前后密码不一致");
	}
	
	private void findPwd(){
		int flag = 5;
		
		if((textuserid.getText()!=null)&&!(textuserid.getText().equals(""))&&(textuserid.getText().length()>=6)){
			flag -= 1;
		}
		if((textemail.getText()!=null)&&!(textemail.getText().equals(""))&&(textemail.getText().length()>=6)){
			flag -= 1;
		}		
		if((new String(textpwd.getPassword()).length())>=6){
			flag -= 1;
		}
		if(new String(textrepeatpwd.getPassword()).length()>=6){
			flag -= 1;
		}	
		if(new String(textpwd.getPassword()).equals(new String(textrepeatpwd.getPassword()))){
			flag -= 1;
		}
		
		
		if(flag==0){
			//找回密码成功
			UserDAO userDao = new UserDAO();
			String userid = textuserid.getText();
			UserBean user = userDao.loadUser(userid);
			if(user == null){
				    JOptionPane.showMessageDialog(null, "用户名不存在！", "找回密码提示",
						JOptionPane.WARNING_MESSAGE,null);
			}
			else{
				if(!user.getEmail().equals(textemail.getText())){
					JOptionPane.showMessageDialog(null, "邮箱号不匹配！", "找回密码提示",
						JOptionPane.WARNING_MESSAGE,null);	
				}
				else{
					user.setUserpwd(new String(textpwd.getPassword()));
					userDao.modifyUser(user);
					JOptionPane.showMessageDialog(null, "恭喜您！找回密码成功！", "找回密码提示",
							JOptionPane.INFORMATION_MESSAGE,null);
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "找回信息格式不合法，请检查！", "找回密码提示",
					JOptionPane.WARNING_MESSAGE,null);
		}
	}
	
	public void reset(){
		textuserid.setText("");
		textemail.setText("");
		textpwd.setText("");
		textrepeatpwd.setText("");
	}
	
	public static void main(String[]  args){
		FindPwdView frame=new FindPwdView();
		//frame.setSize(340,400);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
	}

}
