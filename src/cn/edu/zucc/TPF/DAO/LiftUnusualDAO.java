package cn.edu.zucc.TPF.DAO;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cn.edu.zucc.TPF.Bean.LiftDataBean;
import cn.edu.zucc.TPF.Bean.LiftUnusualBean;
import cn.edu.zucc.TPF.util.HibernateUtil;

public class LiftUnusualDAO {
	
	public LiftUnusualBean loadData(String id){
		Session session = HibernateUtil.getSession();
		return (LiftUnusualBean)session.get(LiftUnusualBean.class, id);
	}
	/**插入批量数据*/
	public void insertDataList(List <LiftUnusualBean> liftList){
		Session session = HibernateUtil.getSession();
		//设置session的刷新模式，Always为任何代码都会；Auto（默认）...session.setFlushMode(FlushMode.COMMIT);
		Transaction tx = session.beginTransaction();
		try{
		   for(int i=0;i < liftList.size();i++){
			   session.save(liftList.get(i));
			   if(i%50==0){
				   session.flush();
				   session.clear();				  
			   }
		   }
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();		
	}
	
	/**插入单个儿数据*/
	public void insertSingleData(LiftUnusualBean lift){
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
	
	public void removeLiftData(LiftUnusualBean lift){
		Session session = HibernateUtil.getSession();
		session.delete(lift);
	}
	
	public void modifyLiftData(LiftUnusualBean lift){
		Session session = HibernateUtil.getSession();
		session.update(lift);
	}	
	
	public static void main(String args[]){
		LiftUnusualBean lift = new LiftUnusualBean();
		LiftUnusualDAO liftDao = new LiftUnusualDAO();
		lift.setLiftid("A1001");
		lift.setAccx(10.2f);
		lift.setAccy(10.2f);
		lift.setAccz(10.2f);
		lift.setRotatex(2.3f);
		lift.setRotatey(2.3f);
		lift.setRotatez(2.3f);
		lift.setWarninglevel(3);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		lift.setRecordtime(time);
		
		liftDao.insertSingleData(lift);
		
	}

}
