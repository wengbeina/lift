package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTest extends JFrame{
    
	public DrawTest(){
       this.add(new NewPanel());
		// TODO Auto-generated constructor stub
	}
    
	
	class NewPanel extends JPanel{
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.setColor(new Color(240,249,255));
			g.fillRect(20, 20, 700, 500);
			g.setColor(Color.BLACK);
			g.drawLine(100, 500, 700, 500);
			g.drawLine(100, 500, 100, 100);
			
			g.setFont(new Font("黑体",Font.BOLD,28));
			g.drawString("电梯加速度监控曲线图", 250, 50);
			
			g.setFont(new Font("宋体",Font.PLAIN,16));
			
			g.setColor(Color.RED);
			g.drawString("X方向", 650, 60);
			g.setColor(Color.GREEN);
			g.drawString("Y方向", 650, 90);
			g.setColor(Color.BLUE);
			g.drawString("Z方向", 650, 120);
			
			g.setColor(Color.BLACK);
			g.drawString("时间", 660, 530);
			g.drawString("加速度（米每平方秒）", 50, 80);
			
			g.drawString("0", 70, 500);
			String[] jiasudu = {"1.0","2.0","3.0","4.0","5.0"};
			String[] time = {"14:30","14:32","14:34","14:36","14:38","14:40"};
			
			for(int i=0;i<jiasudu.length;i++){
				g.setColor(Color.BLACK);
				g.drawString(jiasudu[i], 70, 425-75*i);
				g.setColor(Color.GRAY);
				g.drawLine(100, 425-75*i, 700, 425-75*i);
			}
			
			for(int i=0;i < time.length;i++){
				g.setColor(Color.RED);
				g.drawString(time[i], 150+i*80, 530);
			}
			
			double[] yPoint ={2, 5.3, 3.5, 4.8, 1.5, 2.8, 3.3};
			int[] xX = new int[yPoint.length];
			int[] yY = new int[yPoint.length];
			
			for(int i=0;i< yPoint.length;i++){
				int xi = (int)(1.0*600/yPoint.length*(i+1)+100);
				int yi = (int)(500-yPoint[i]*75);
				xX[i] = xi;
				yY[i] = yi;	
				g.setColor(Color.BLACK);
				g.drawString(".", xi-2, yi+2);
			}
			
			g.setColor(Color.RED);
			g.drawPolyline(xX, yY, xX.length);
			
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DrawTest frame = new DrawTest();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		// TODO Auto-generated method stub

	}

}
