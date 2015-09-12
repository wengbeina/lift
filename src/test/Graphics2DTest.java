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
	    g2d.setFont(new Font("宋体",Font.PLAIN,22));
	    g2d.drawString("浙江大学城市学院", 15, 25);
	    int visit[] = new int[6];
	    
	    for(int i=0; i <visit.length;i++){
	    	visit[i] = 1+(int)(Math.random()*100);
	    }
	    
	    String month[] ={"1月","2月","3月","4月","5月","6月"};
	    g2d.setFont(new Font("宋体", Font.BOLD,16));
	    g2d.drawString("访问量（单位：万次）", 20, 50);
	    g2d.drawString("月份", 550, 465);
	    
	    int visitValue = 0;
	    
	    
		// TODO Auto-generated constructor stub
	}
	

}
