package cn.edu.zucc.TPF.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
    private static SessionFactory sessionfactory;
    private static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	
	public static Session getSession(){
		Session session=threadLocal.get();
		if(session!=null&&session.isOpen()) return session;
		if(sessionfactory==null){
			Configuration config=new Configuration();
			config.configure();/**读取配置文件*/
			sessionfactory=config.buildSessionFactory();/**得到工厂*/
		}
		session=sessionfactory.openSession();
		threadLocal.set(session);
		return session;
	}
	
	public static void closeSession(){
		Session session=threadLocal.get();
		threadLocal.set(null);
		if(session!=null)
			session.close();
	}
}
