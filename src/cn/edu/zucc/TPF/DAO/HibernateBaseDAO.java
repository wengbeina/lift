/**
 * 
 */
package cn.edu.zucc.TPF.DAO;

/**
 * @author tan
 *
 */
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import cn.edu.zucc.TPF.util.HibernateUtil;

public class HibernateBaseDAO {
   public Session getSession(){
	   return HibernateUtil.getSession();
   }
   
   public void beginTransaction(){
	   this.getSession().beginTransaction();       
   }
   
   public void rollbackTransaction(){
	   this.getSession().getTransaction().rollback();
   }
   
   public void commitTransaction(){
	   this.getSession().getTransaction().commit();
   }
   
   public List<?> qry(String hql){
	   Query qry=this.getSession().createQuery(hql);
	   return qry.list();
   }
   
   
}
