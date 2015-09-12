package Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConnectionPoolManager implements ConnectionPool{
    private ConnectionProvider provider = new ConnectionProvider();
    
    private final List <Connection> pool = new ArrayList<Connection>();
    private int poolSize=5;
    public ConnectionPoolManager(){
    }
	
    public ConnectionPoolManager(int poolSize) {
		this.poolSize=poolSize;
	}
	
	public Connection getConnection() throws SQLException{
		synchronized(pool){
			if(!pool.isEmpty()){
				int last = pool.size()-1;
				Connection conn=pool.remove(last);
				return conn;
			}			
		}
		
		Connection conn=provider.getConnection();
		return conn;
	}
	
	public void releaseConnection(Connection conn){
		synchronized(pool){
			int currentSize = pool.size();
			if(currentSize < poolSize){
				pool.add(conn);
				return ;
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		Iterator <Connection> iter=pool.iterator();
		while(iter.hasNext()){
			try {
				iter.next().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pool.clear();
	}
}
