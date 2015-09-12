package cn.edu.zucc.TPF.util;

public interface DistenceCompute {
	//单位为公里
	public float getDistenceOfTwoPoint(float aLatitude, float aLongitude,
			float bLatitude, float bLongitude);
}
