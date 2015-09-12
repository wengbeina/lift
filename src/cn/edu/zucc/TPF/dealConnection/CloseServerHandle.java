package cn.edu.zucc.TPF.dealConnection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class CloseServerHandle implements Runnable{
    private ExecutorService executor;
	public CloseServerHandle(ExecutorService executor) {
		this.executor = executor;
		// TODO Auto-generated constructor stub
	}
	
	public void run(){
		Long beginTime = System.currentTimeMillis();
		executor.shutdown();
		while(!executor.isTerminated()){
			try {
				executor.awaitTermination(30, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("关闭服务器总共花费了:"+(endTime-beginTime)/1000+"秒");
	}
}
