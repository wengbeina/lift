package cn.edu.zucc.TPF.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import cn.edu.zucc.TPF.Bean.UserBean;
import cn.edu.zucc.TPF.DAO.UserDAO;

public class LoginView extends JFrame{
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    
    private ImageIcon imageIcon = new ImageIcon(getClass().getResource("/login.gif"));
    private JLabel lblid = new JLabel("                                              ͨ��֤�ţ�");
    private JLabel lblpwd = new JLabel("                                              ���룺");
    
    private JTextField textid =new JTextField(9);
    private JPasswordField textpwd = new JPasswordField(9);
    
    private JButton jbtregister = new JButton("ע��");
    private JButton jbtfindpwd = new JButton("�һ�����");
    private JButton jbtlogin = new JButton("��¼");
    private JButton jbtreset = new JButton("����");
    private RegisterView registerView = null;
    private FindPwdView findPwdView = null;
    
	public LoginView() {
		panel1.add(new JButton(imageIcon));
		
		GridLayout lay1 = new GridLayout(2,3);
		lay1.setHgap(30);
		lay1.setVgap(10);
		panel1.add(new JButton(imageIcon));
		
		panel2.setLayout(lay1);
		panel2.add(lblid);
		panel2.add(textid);
		panel2.add(jbtregister);
		
		panel2.add(lblpwd);		
		panel2.add(textpwd);	
		panel2.add(jbtfindpwd);
		
		panel3.add(jbtlogin);
		panel3.add(jbtreset);
		
		//this.add(panel1,BorderLayout.NORTH);
		this.add(panel1,BorderLayout.NORTH);
		this.add(panel2,BorderLayout.CENTER);
		this.add(panel3,BorderLayout.SOUTH);
		this.setTitle("��ӭ����¼��������Ԥ��������ϵͳ��");	
		this.setBounds(200, 100, 700, 400);
	    this.setVisible(true);
		
	    
		jbtlogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					loginDeal();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		jbtreset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reset();
			}
		});
		
		jbtregister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(registerView != null) registerView.setVisible(true);
				else
				   registerView = new RegisterView();
			}
		});
		
		jbtfindpwd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(findPwdView != null) {
					findPwdView.setVisible(true);
					findPwdView.reset();
				}
				
				else
					findPwdView = new FindPwdView();
			}
		});
		// TODO Auto-generated constructor stub
	}
	
	private void loginDeal() throws IOException{
		String id = textid.getText();
		String pwd = new String(textpwd.getPassword());
		int flag =2;
		if(!id.equals("")){
			flag -= 1;
		}
		if(pwd.length()>=6){
			flag -= 1;
		}
		
		if(flag!=0){
			JOptionPane.showMessageDialog(null, "�û������������벻�Ϸ������飡", "��¼��ʾ",
					JOptionPane.WARNING_MESSAGE,null);
		}
		else{
			//��¼����
			UserDAO userDao = new UserDAO();
			UserBean user = userDao.loadUser(id);
			if(user == null || user.getUsertype() == 0){
				JOptionPane.showMessageDialog(null, "�û��������ڣ����飡", "��¼��ʾ",
						JOptionPane.INFORMATION_MESSAGE,null);
			}
			else{
				if(user.getIsregistered()==0){
					JOptionPane.showMessageDialog(null, "�����δͨ������ȴ���", "��¼��ʾ",
							JOptionPane.INFORMATION_MESSAGE,null);
				}
				else{
					if(!user.getUserpwd().equals(pwd)){
						JOptionPane.showMessageDialog(null, "����������⣡", "��¼��ʾ",
								JOptionPane.INFORMATION_MESSAGE,null);
					}
					else{
						JOptionPane.showMessageDialog(null, "��¼�ɹ���", "��¼��ʾ",
								JOptionPane.INFORMATION_MESSAGE,null);
						//ת�������ͼ�ι������
						this.setVisible(false);
						ServerView serview = new ServerView(user);
					}
				}
				
			}
		}
		
	}
	
	private void work() throws IOException{
		
	}
	
	private void reset(){
		textid.setText("");
		textpwd.setText("");
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
       LoginView frame = new LoginView();
       frame.setBounds(200, 100, 700, 400);
	}
}
