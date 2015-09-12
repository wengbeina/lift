package cn.edu.zucc.TPF.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cn.edu.zucc.TPF.Bean.UserBean;
import cn.edu.zucc.TPF.DAO.UserDAO;
import cn.edu.zucc.TPF.util.JGenderRadio;

public class RegisterView extends JFrame{
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    
    private JLabel lbluserid = new JLabel("�û�����");
    private JLabel lblname = new JLabel("������");
    private JLabel lblpwd = new JLabel("���룺");
    private JLabel lblrepeatpwd = new JLabel("ȷ�����룺");
    private JLabel lblgender = new JLabel("�Ա�");
    private JLabel lblcellphone = new JLabel("�ֻ����룺");
    private JLabel lblemail = new JLabel("���䣺");
    
    private JLabel checkuserid = new JLabel("�û�������Ϊ�գ�");
    private JLabel checkname = new JLabel("��������Ϊ�գ�");
    private JLabel checkpwd = new JLabel("���������ַ���");
    private JLabel checkrepeatpwd = new JLabel("���������ַ���");
    private JLabel checkgender = new JLabel("��ѡ���Ա�");
    private JLabel checkcellphone = new JLabel("������11λ��");
    private JLabel checkemail = new JLabel("");
    private JGenderRadio textgender = new JGenderRadio();
    
    private JTextField textuserid = new JTextField(6);
    private JTextField textname = new JTextField(6);
    private JPasswordField textpwd = new JPasswordField(6);
    private JPasswordField textrepeatpwd = new JPasswordField(6);
    private JTextField textcellphone = new JTextField(6);
    private JTextField textemail = new JTextField(6);
    
    private JButton jbtOK = new JButton("OK");
    private JButton jbtreset=new JButton("Reset");
    
	public RegisterView() {
	    GridLayout grid = new GridLayout(7,1);
	    grid.setHgap(10);
	    grid.setVgap(10);
		panel1.setLayout(grid);
		panel2.setLayout(grid);
		panel3.setLayout(grid);
		
		panel1.add(lbluserid);
		panel1.add(lblname);
		panel1.add(lblpwd);
		panel1.add(lblrepeatpwd);
		panel1.add(lblgender);
		panel1.add(lblcellphone);
		panel1.add(lblemail);
		
		panel2.add(textuserid);
		panel2.add(textname);		
		panel2.add(textpwd);		
		panel2.add(textrepeatpwd);	
		panel2.add(panel5);		
		panel2.add(textcellphone);		
		panel2.add(textemail);		
		
		panel3.add(checkuserid);
		panel3.add(checkname);		
		panel3.add(checkpwd);		
		panel3.add(checkrepeatpwd);		
		panel3.add(checkgender);		
		panel3.add(checkcellphone);		
		panel3.add(checkemail);		
		
		panel4.add(jbtOK);
		panel4.add(jbtreset);
		
		panel5.add(textgender);
		
		this.setTitle("�û�ע�᣺");
		this.add(panel1,BorderLayout.WEST);
		this.add(panel2,BorderLayout.CENTER);
		this.add(panel3,BorderLayout.EAST);
		this.add(panel4,BorderLayout.SOUTH);
		
		this.setBounds(920, 100, 380, 400);
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
		
		textname.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				judgeisEmpty(textname,checkname);
			}
			public void removeUpdate(DocumentEvent e){
				judgeisEmpty(textname,checkname);
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
		
		textcellphone.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				checkCellphone();
			}
			public void removeUpdate(DocumentEvent e){
				checkCellphone();
			}
		});
		
		jbtOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				register();
			}
		});
		
		jbtreset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reset();
			}
		});		
		// TODO Auto-generated constructor stub
	}
	
	private void judgePwd(){
		char[] pwd = textpwd.getPassword();
		if(pwd.length == 0){
			checkpwd.setText("���벻��Ϊ�գ�");
		}
		else if(pwd.length > 0&&pwd.length < 6){
			checkpwd.setText("���������ַ���");
		}
		else if(pwd.length>=6 && pwd.length<9){
			checkpwd.setText("����ǿ�Ƚ�����");
		}
		else if(pwd.length >= 9){
			checkpwd.setText("����ǿ�Ƚ�ǿ��");
		}
	}
	
	private void judgeisEmpty(JTextField jtext,JLabel show){
		String check = jtext.getText(); 
		if(check.length() == 0){
			show.setText("����Ϊ�գ�");
		}
		else if(check.length() < 6)
			show.setText("������6�ַ���");
		else
			show.setText("����Ҫ��");
	}
	
	private void checkRepeatPwd(){
		String pwd = new String(textpwd.getPassword());
		String repeatpwd = new String(textrepeatpwd.getPassword());
		if(pwd.equals(repeatpwd)){
			checkrepeatpwd.setText("ǰ������һ��");
		}
		else
			checkrepeatpwd.setText("ǰ�����벻һ��");
	}
	
	private void checkCellphone(){
		String cellphone = textcellphone.getText();
		if(cellphone ==null) checkcellphone.setText("���벻��Ϊ�գ�");
		else if(cellphone.length()!=11)
			checkcellphone.setText("λ������11��");
		else {
			try{
		      long phoneNum = Long.parseLong(cellphone);
		      checkcellphone.setText("����Ҫ��");
			}
			catch(Exception e){
				e.printStackTrace();
				checkcellphone.setText("���з������ַ���");
			}
		}
	}
	
	private void register(){
		UserBean user = new UserBean();
		int flag = 8;
		
		user.setUserid(textuserid.getText());
		user.setUsername(textname.getText());
		user.setUserpwd(new String(textpwd.getPassword()));
		user.setGender(textgender.getText());
		user.setCellphone(textcellphone.getText());
		user.setEmail(textemail.getText());
		user.setUsertype(1);
		user.setIsregistered(1);
		
		if((textuserid.getText()!=null)&&!(textuserid.getText().equals(""))&&(textuserid.getText().length()>=6)){
			flag -= 1;
		}
		if((textname.getText()!=null)&&!(textname.getText().equals(""))&&(textname.getText().length()>=6)){
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
		if((textgender.getText()!=null)&&!textgender.getText().equals("")){
			flag -= 1;
		}
		if((textcellphone.getText()!=null)&&!textcellphone.getText().equals("")&&(textcellphone.getText().length()==11)){
			flag -= 1;
		}
		if((textemail.getText()!=null)&&!textemail.getText().equals("")){
			flag -= 1;
		}		
		
		if(flag==0){
			//ע��ɹ�
			UserDAO userDao = new UserDAO();
			userDao.insertSingleUser(user);
			JOptionPane.showMessageDialog(null, "��ϲ����ע��ɹ���", "ע����ʾ",
						JOptionPane.INFORMATION_MESSAGE,null);
		}
		else{
			JOptionPane.showMessageDialog(null, "ע���ʽ���Ϸ������飡", "ע����ʾ",
					JOptionPane.WARNING_MESSAGE,null);
		}
	}
	
	private void reset(){
		textuserid.setText("");
		textname.setText("");
		textpwd.setText("");
		textrepeatpwd.setText("");
		textcellphone.setText("");
		textemail.setText("");
	}
	
	public static void main(String[]  args){
		RegisterView frame=new RegisterView();
		//frame.setSize(340,400);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
	}

}
