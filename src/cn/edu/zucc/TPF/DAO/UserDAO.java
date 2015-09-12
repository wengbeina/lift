package cn.edu.zucc.TPF.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.UserBean;
import cn.edu.zucc.TPF.util.HibernateUtil;

public class UserDAO {

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
    
	public UserBean loadUser(String id){
		Session session = HibernateUtil.getSession();
		return (UserBean)session.get(UserBean.class, id);
	}
	
	/**插入单个儿数据*/
	public void insertSingleUser(UserBean user){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.save(user);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();		
	}
	
	public void removeUser(UserBean user){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.delete(user);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}
	
	public void modifyUser(UserBean user){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.update(user);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}
	
	public static void main(String args[]){
		UserDAO userDao = new UserDAO();
		/*UserBean user = new UserBean();
		user.setUserid("31001471");
		user.setUserpwd("123456");
		user.setUsername("王小吉");
		user.setUsertype(0);
		user.setCellphone("635130");
		user.setEmail("815196746@qq.com");
		user.setIsregistered(1);*/
		//userDao.insertSingleUser(user);
		UserBean user = userDao.loadUser("31001471");
		System.out.println(user.getUsername());
			
	}
}
