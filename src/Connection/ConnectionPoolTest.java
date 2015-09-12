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
			System.out.println(Thread.currentThread().getName()+":�����ӳ�ȡ��һ������"+conn);
			Statement sta=conn.createStatement();
			sta.close();
			pool.releaseConnection(conn);
			System.out.println(Thread.currentThread().getName()+":�ͷ�����"+conn);
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
