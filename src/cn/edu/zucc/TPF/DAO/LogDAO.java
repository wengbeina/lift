package cn.edu.zucc.TPF.DAO;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.LogBean;
import cn.edu.zucc.TPF.util.HibernateUtil;

public class LogDAO {
	public LogBean loadLog(int id){
		Session session = HibernateUtil.getSession();
		return (LogBean)session.get(LogBean.class, id);
	}
	
	/**插入单个儿数据*/
	public void insertLog(LogBean log){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.save(log);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();		
	}
	/**根据操作名称查询日志*/
	/**根据时间段查询日志*/
	/**根据操作者ID查询所有日志*/
	/**根据操作者ID和给定时间段，查询所有日志*/
	
	public void removeLog(LogBean log){
		Session session = HibernateUtil.getSession();
		session.delete(log);
	}
	
	public static void main(String args[]){
		LogDAO logDao = new LogDAO();
		LogBean log = new LogBean();
		log.setOperaterid("A1003");
		log.setOperateType("login");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		log.setLogtime(time);
		logDao.insertLog(log);
			
	}

}
