package cn.edu.zucc.TPF.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ThreadNumModify {
	static private Properties ps;
	
	public ThreadNumModify(){
		init();
	}
	
	private void init(){
		ps =  new Properties();
		try {
			InputStream input = new FileInputStream("properties/threadNum.properties");
			ps.load(input);
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		//return (String) config.getProperty(key);
		return (String) ps.get(key);
	}
	
	public void put(String key, String value){
		try {
			FileOutputStream output = new FileOutputStream("properties/threadNum.properties");
			ps.setProperty(key, value);
			ps.store(output, "Save properties!");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void  main(String[] args){
		ThreadNumModify temp = new ThreadNumModify();
		temp.put("threadNum", "30");
	}
}
