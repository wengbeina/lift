package Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolTest implements Runnable{
    
	private ConnectionPool pool = new ConnectionPoolManager();;
	public ConnectionPoolTest() {
		
	}
	
	public void run(){
		try {
			Connection conn=pool.getConnection();
			System.out.println(Thread.currentThread().getName()+":从连接池取出一个连接"+conn);
			Statement sta=conn.createStatement();
			sta.close();
			pool.releaseConnection(conn);
			System.out.println(Thread.currentThread().getName()+":释放链接"+conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
	}

}
