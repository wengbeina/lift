package cn.edu.zucc.TPF.Bean;

import java.sql.Timestamp;

public class LogBean {
	private int id;
	private String operaterid;
	private String operateType;
	private Timestamp logtime;

	public LogBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the operaterid
	 */
	public String getOperaterid() {
		return operaterid;
	}

	/**
	 * @param operaterid the operaterid to set
	 */
	public void setOperaterid(String operaterid) {
		this.operaterid = operaterid;
	}

	/**
	 * @return the operateType
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * @param operateType the operateType to set
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * @return the logtime
	 */
	public Timestamp getLogtime() {
		return logtime;
	}

	/**
	 * @param logtime the logtime to set
	 */
	public void setLogtime(Timestamp logtime) {
		this.logtime = logtime;
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

}
