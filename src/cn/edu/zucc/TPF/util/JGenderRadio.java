package cn.edu.zucc.TPF.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JGenderRadio extends JPanel{
	private JRadioButton textmale = new JRadioButton("ÄÐ");
	private JRadioButton textfemale = new JRadioButton("Å®");

	public JGenderRadio() {
		this.add(textmale);
		this.add(textfemale);
		textmale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textmale.isSelected()){
					textfemale.setSelected(false);
				}
				else
					textfemale.setSelected(true);
			}
		});
		
		textfemale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textfemale.isSelected()){
					textmale.setSelected(false);
				}
				else
					textmale.setSelected(true);
			}
		});
	}
   public String getText(){
	   if(textmale.isSelected()){
		   return "ÄÐ";
	   }
	   else if(textfemale.isSelected()){
		   return "Å®";
	   }
	   else
		   return "";		   
   }
   public static void main(String[] args){
	   JFrame frame=new JFrame();
	   JGenderRadio text=new JGenderRadio();
	   frame.add(text);
	   frame.setSize(300,400);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setVisible(true);
   }
   
}
