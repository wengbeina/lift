package cn.edu.zucc.TPF.Bean;

import java.sql.Date;

public class GpsData {
	private Date date;
    private int GpsLength;
    private int satelliteNo;
    private double latitude;//Î³¶È
    private double longitude;
    private float speed;
    private String GpsPositionStyle;
    private String GpsPositionJudge;
    private String latitudeDirection;
    private String longitudeDirection;
    private int directionDegree;
    private int MCC;
    private int MNC;
    private int LAC;
    private int CellID;
	public GpsData() {
		// TODO Auto-generated constructor stub
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getGpsLength() {
		return GpsLength;
	}
	public void setGpsLength(int gpsLength) {
		GpsLength = gpsLength;
	}
	public int getSatelliteNo() {
		return satelliteNo;
	}
	public void setSatelliteNo(int satelliteNo) {
		this.satelliteNo = satelliteNo;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public String getGpsPositionStyle() {
		return GpsPositionStyle;
	}
	public void setGpsPositionStyle(String gpsPositionStyle) {
		GpsPositionStyle = gpsPositionStyle;
	}
	public String getGpsPositionJudge() {
		return GpsPositionJudge;
	}
	public void setGpsPositionJudge(String gpsPositionJudge) {
		GpsPositionJudge = gpsPositionJudge;
	}
	public String getLatitudeDirection() {
		return latitudeDirection;
	}
	public void setLatitudeDirection(String latitudeDirection) {
		this.latitudeDirection = latitudeDirection;
	}
	public String getLongitudeDirection() {
		return longitudeDirection;
	}
	public void setLongitudeDirection(String longitudeDirection) {
		this.longitudeDirection = longitudeDirection;
	}
	public int getDirectionDegree() {
		return directionDegree;
	}
	public void setDirectionDegree(int directionDegree) {
		this.directionDegree = directionDegree;
	}
	public int getMCC() {
		return MCC;
	}
	public void setMCC(int mCC) {
		MCC = mCC;
	}
	public int getMNC() {
		return MNC;
	}
	public void setMNC(int mNC) {
		MNC = mNC;
	}
	public int getLAC() {
		return LAC;
	}
	public void setLAC(int lAC) {
		LAC = lAC;
	}
	public int getCellID() {
		return CellID;
	}
	public void setCellID(int cellID) {
		CellID = cellID;
	}

}
