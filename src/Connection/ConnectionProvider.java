package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private String jdbc_driver;
    private String url;
    private String user;
    private String password;	
	
	public ConnectionProvider(){
		this.jdbc_driver = PropertiesReader.get(new String("jdbc_driver"));
		this.url = PropertiesReader.get("url");
		this.user = PropertiesReader.get("user");
		this.password = PropertiesReader.get("password");
		
	   try{
			Class.forName(jdbc_driver);
			System.out.println("Driver Loaded!");
	   }catch(ClassNotFoundException e){
		   e.printStackTrace();
	   }
	}
	
	public String getUrl(){
		return url;
	}
	public Connection getConnection () throws SQLException{
		Connection conn=DriverManager.getConnection(url, user, password);
		System.out.println("Database connected!");
		return conn;
	}
}
