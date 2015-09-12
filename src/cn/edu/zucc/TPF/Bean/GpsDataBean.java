package cn.edu.zucc.TPF.Bean;

import java.sql.Timestamp;

public class GpsDataBean {
    private int id;
    private String userid;
    private float latitude;//纬度
    private float longitude;//经度
    private String clientip;
    private Timestamp recordtime;//记录时间
    
	public GpsDataBean() {
		// TODO Auto-generated constructor stub
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
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
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
	 * @return the clientip
	 */
	public String getClientip() {
		return clientip;
	}
	/**
	 * @param clientip the clientip to set
	 */
	public void setClientip(String clientip) {
		this.clientip = clientip;
	}
}
