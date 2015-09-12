package cn.edu.zucc.TPF.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cn.edu.zucc.TPF.Bean.GpsDataBean;
import cn.edu.zucc.TPF.util.HibernateUtil;

public class GpsDataDAO {
	public GpsDataDAO() {
		// TODO Auto-generated constructor stub
	}
    
	public GpsDataBean loadGpsData(int id){
		Session session = HibernateUtil.getSession();
		return (GpsDataBean)session.get(GpsDataBean.class, id);
	}
	
	
	/**插入单个儿数据*/
	public void insertSingleData(GpsDataBean gps){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.save(gps);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();		
	}
	
	public void removeGpsData(GpsDataBean gps){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.delete(gps);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}
	
	public void modifyGpsData(GpsDataBean gps){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.update(gps);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}
	
	//查询在线的所有维保人员的最新Gps数据信息
	public List <GpsDataBean> queryGpsData(){
		Session session = HibernateUtil.getSession();
		Timestamp recordTime = new Timestamp(System.currentTimeMillis() - 1000*60*60);
		
		String hql = "from GpsDataBean gps where gps.recordtime >= :recordTime";
		Query query = session.createQuery(hql);
		query.setParameter("recordTime", recordTime);
		List <GpsDataBean> gpsList = query.list();
		
		return gpsList;
	}
	
	public static void main(String args[]){
		GpsDataDAO gpsDataDao = new GpsDataDAO();
		List <GpsDataBean> gpsList = gpsDataDao.queryGpsData();
		for(GpsDataBean i:gpsList){
			System.out.print(i.getId()+"——");
		}
		/*GpsDataBean gpsData = new GpsDataBean();
		gpsData.setLongitude(30.11111111f);
		gpsData.setLatitude(120.88888f);
		gpsData.setUserid("31001472");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		gpsData.setRecordtime(time);
		gpsDataDao.insertSingleData(gpsData);*/		
	}
}
