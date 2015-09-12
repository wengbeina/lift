package cn.edu.zucc.TPF.Bean;

import java.sql.Timestamp;

public class LiftUnusualBean {
	private int id;
    private String liftid;
    private int warninglevel;
    private float accx;
    private float accy;
    private float accz;
    private float rotatex;
    private float rotatey;
    private float rotatez;
    private Timestamp recordtime;
    private int isDealed;
    
	public LiftUnusualBean() {
		// TODO Auto-generated constructor stub
	}
	
	public LiftUnusualBean(LiftDataBean liftdata, int warningLevel){
		this.liftid = liftdata.getLiftid();
		this.warninglevel = warningLevel;
		this.accx =  liftdata.getAccx();
		this.accy = liftdata.getAccy();
		this.accz = liftdata.getAccz();
		this.rotatex = liftdata.getRotatex();
		this.rotatey = liftdata.getRotatey();
		this.rotatez = liftdata.getRotatez();
		this.recordtime = liftdata.getRecordtime();		
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the liftid
	 */
	public String getLiftid() {
		return liftid;
	}
	/**
	 * @param liftid the liftid to set
	 */
	public void setLiftid(String liftid) {
		this.liftid = liftid;
	}
	/**
	 * @return the warninglevel
	 */
	public int getWarninglevel() {
		return warninglevel;
	}
	/**
	 * @param warninglevel the warninglevel to set
	 */
	public void setWarninglevel(int warninglevel) {
		this.warninglevel = warninglevel;
	}
	/**
	 * @return the accx
	 */
	public float getAccx() {
		return accx;
	}
	/**
	 * @param accx the accx to set
	 */
	public void setAccx(float accx) {
		this.accx = accx;
	}
	/**
	 * @return the accy
	 */
	public float getAccy() {
		return accy;
	}
	/**
	 * @param accy the accy to set
	 */
	public void setAccy(float accy) {
		this.accy = accy;
	}
	/**
	 * @return the accz
	 */
	public float getAccz() {
		return accz;
	}
	/**
	 * @param accz the accz to set
	 */
	public void setAccz(float accz) {
		this.accz = accz;
	}
	/**
	 * @return the rotatex
	 */
	public float getRotatex() {
		return rotatex;
	}
	/**
	 * @param rotatex the rotatex to set
	 */
	public void setRotatex(float rotatex) {
		this.rotatex = rotatex;
	}
	/**
	 * @return the rotatey
	 */
	public float getRotatey() {
		return rotatey;
	}
	/**
	 * @param rotatey the rotatey to set
	 */
	public void setRotatey(float rotatey) {
		this.rotatey = rotatey;
	}
	/**
	 * @return the rotatez
	 */
	public float getRotatez() {
		return rotatez;
	}
	/**
	 * @param rotatez the rotatez to set
	 */
	public void setRotatez(float rotatez) {
		this.rotatez = rotatez;
	}
	/**
	 * @return the recordtime
	 */
	public Timestamp getRecordtime() {
		return recordtime;
	}
	/**
	 * @param recordtime the recordtime to set
	 */
	public void setRecordtime(Timestamp recordtime) {
		this.recordtime = recordtime;
	}

	/**
	 * @return the isDealed
	 */
	public int getIsDealed() {
		return isDealed;
	}

	/**
	 * @param isDealed the isDealed to set
	 */
	public void setIsDealed(int isDealed) {
		this.isDealed = isDealed;
	}

}
