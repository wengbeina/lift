package Connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
   /**从连接池中取出连接*/
   public Connection getConnection() throws SQLException;
   
   /**把连接放回连接池*/
   public void releaseConnection(Connection conn)throws SQLException;
   
   /**关闭连接池*/
   public void close();
}
