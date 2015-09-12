package Connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
   /**�����ӳ���ȡ������*/
   public Connection getConnection() throws SQLException;
   
   /**�����ӷŻ����ӳ�*/
   public void releaseConnection(Connection conn)throws SQLException;
   
   /**�ر����ӳ�*/
   public void close();
}
