package cn.edu.zucc.TPF.DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.TPF.Bean.AlarmDealBean;
import cn.edu.zucc.TPF.util.HibernateUtil;

public class AlarmDealDAO {

	public AlarmDealDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public AlarmDealBean loadAlarmData(int id){
		Session session = HibernateUtil.getSession();
		return (AlarmDealBean)session.get(AlarmDealBean.class, id);
	}
	
	
	/**插入单个儿数据*/
	public void insertSingleData(AlarmDealBean alarm){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.save(alarm);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();		
	}
	
	public void removeAlarmData(AlarmDealBean alarm){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.delete(alarm);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();	
	}
	
	public void modifyAlarmData(AlarmDealBean alarm){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try{
		   session.update(alarm);
		   tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();	
	}
	
	//
	public List <AlarmDealBean> queryAlarmData(String userid){
		Session session = HibernateUtil.getSession();
		
		String hql = "from AlarmDealBean alarm where alarm.senderid= :userid "+"and alarm.dealtag = 0";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		List <AlarmDealBean> alarmList = query.list();		
		return alarmList;
	}
	
	public List <AlarmDealBean> queryNonDealedAlarm(){
		Session session = HibernateUtil.getSession();
		
		String hql = "from AlarmDealBean alarm where alarm.dealtag = 3";
		Query query = session.createQuery(hql);
		List <AlarmDealBean> alarmList = query.list();		
		return alarmList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlarmDealDAO dao = new AlarmDealDAO();
		List <AlarmDealBean> list = dao.queryAlarmData("31001471");
		/*AlarmDealBean data = new AlarmDealBean();
		data.setSenderid("31001471");
		data.setLiftid("A1002");
		Timestamp time1 = new Timestamp(System.currentTimeMillis());
		Timestamp time2 = new Timestamp(System.currentTimeMillis() - 1000*60);
		data.setUnusualtime(time1);
		data.setRecordtime(time2);
		
		dao.insertSingleData(data);*/
		System.out.println(list.size());
		for(AlarmDealBean i: list){
			System.out.println(i.getLiftid());
		}

	}

}
