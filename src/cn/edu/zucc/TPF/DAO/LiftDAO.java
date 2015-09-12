package cn.edu.zucc.TPF.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.util.HibernateUtil;

public class LiftDAO {
	public LiftBean loadLift(String id){
		Session session = HibernateUtil.getSession();
		return (LiftBean)session.get(LiftBean.class, id);
	}
	
	/**插入单个儿数据*/
	public void insertSingleLift(LiftBean lift){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.save(lift);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();		
	}
	
	public void removeLift(LiftBean lift){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.delete(lift);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}
	
	public void modifyLift(LiftBean lift){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.update(lift);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}	
	
	public static void main(String args[]){
		LiftDAO liftDao = new LiftDAO();
		LiftBean lift1 = liftDao.loadLift("A1003");
		System.out.println(lift1.getLiftid()+lift1.getLiftname());
			
	}

}
