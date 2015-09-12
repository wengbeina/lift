package Connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;


public class PropertiesReader {
	private static PropertiesConfiguration config;
	static private Properties ps;
	
	static{
		ps = new Properties();
		try {
		//	config = new PropertiesConfiguration("jdbc.config.properties");
			InputStream in = PropertiesReader.class.getResourceAsStream("db.conf");
			ps.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		//return (String) config.getProperty(key);
		return (String) ps.get(key);
	}
}
