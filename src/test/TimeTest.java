package test;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Calendar c1 = Calendar.getInstance();
       c1.set(2014, 0, 1, 0, 0, 0);
       Timestamp t1 = new Timestamp(c1.getTimeInMillis());
       
       Calendar c2 = Calendar.getInstance();
       c2.set(2014, 0, 1, 0, 2, 0);
       Timestamp t2 = new Timestamp(c1.getTimeInMillis());
       
    
       
	}

}
