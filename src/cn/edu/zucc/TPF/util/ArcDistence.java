package cn.edu.zucc.TPF.util;

public class ArcDistence implements DistenceCompute{

	public ArcDistence() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getDistenceOfTwoPoint(float aLatitude, float aLongitude,
			float bLatitude, float bLongitude) {
		// TODO Auto-generated method stub
		float R = 6400;
		float result;
		float aX,aY,aZ;
		float bX,bY,bZ;
		float lineLength = 0;
		
		aX = (float)(Math.cos(aLatitude)*Math.cos(aLongitude));
		aY = (float)(Math.cos(aLatitude)*Math.sin(aLongitude));
		aZ = (float)(Math.sin(aLatitude));
		
		bX = (float)(Math.cos(bLatitude)*Math.cos(bLongitude));
		bY = (float)(Math.cos(bLatitude)*Math.sin(bLongitude));
		bZ = (float)(Math.sin(bLatitude));
		
		lineLength = (float)Math.sqrt(Math.pow(aX -bX, 2) + Math.pow(aY-bY, 2) + Math.pow(aZ-bZ, 2));
		
		result =(float)(2*Math.PI*R*Math.asin(0.5*lineLength)/180);
		return result;
	}
	
	public static void  main(String[] args){
		DistenceCompute t = new ArcDistence();
		float a = t.getDistenceOfTwoPoint(30.33028221f ,120.34346008f, 30.331164f, 120.149351f);		
		System.out.print(a);
	}

}
