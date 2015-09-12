package cn.edu.zucc.TPF.dealConnection;

import java.util.concurrent.ThreadPoolExecutor;

import cn.edu.zucc.TPF.View.ServerView;

public class ObserveThreadsHandle implements Runnable{
    private ServerView serverView;
    private long period = 2*100;
    
	public ObserveThreadsHandle(ServerView serverView) {
		this.serverView = serverView;
		// TODO Auto-generated constructor stub
	}
	
	public void run(){
		while(true){
			ThreadPoolExecutor threadTemp = (ThreadPoolExecutor)serverView.getExecutor();
			serverView.setActiveThreadCount(threadTemp.getActiveCount());
			serverView.setTaskDealedCount(threadTemp.getCompletedTaskCount());
			serverView.setCurrentTaskCount(threadTemp.getTaskCount());
			serverView.ThreadShow();
			
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
