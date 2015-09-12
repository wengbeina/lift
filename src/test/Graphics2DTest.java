package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Graphics2DTest {
	private int width = 600;
	private int height = 500;
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private Graphics2D g2d;
	private Graphics g;
	
	public Graphics2DTest() {
	    g = image.getGraphics();
	    g2d = (Graphics2D)g;
	    g2d.setColor(new Color(240,249,255));
	    g2d.fillRect(0, 0, width, height);
	    g2d.setColor(Color.BLACK);
	    g2d.setFont(new Font("����",Font.PLAIN,22));
	    g2d.drawString("�㽭��ѧ����ѧԺ", 15, 25);
	    int visit[] = new int[6];
	    
	    for(int i=0; i <visit.length;i++){
	    	visit[i] = 1+(int)(Math.random()*100);
	    }
	    
	    String month[] ={"1��","2��","3��","4��","5��","6��"};
	    g2d.setFont(new Font("����", Font.BOLD,16));
	    g2d.drawString("����������λ����Σ�", 20, 50);
	    g2d.drawString("�·�", 550, 465);
	    
	    int visitValue = 0;
	    
	    
		// TODO Auto-generated constructor stub
	}
	

}
