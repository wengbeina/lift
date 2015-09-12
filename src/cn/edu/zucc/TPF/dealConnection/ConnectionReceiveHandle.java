package cn.edu.zucc.TPF.dealConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.edu.zucc.TPF.View.ServerView;

public class ConnectionReceiveHandle implements Runnable{
	private ServerSocket serverSocket ;
    private int port = 9900;
    private ExecutorService executor;
    private ServerView serverView;

	public ConnectionReceiveHandle( ExecutorService executor, ServerView serverView) throws IOException {
		this.executor = executor;
		serverSocket = new ServerSocket(port);
		this.serverView = serverView;
		System.out.println("Server Start...");
		// TODO Auto-generated constructor stub
	}
	
	public void run(){
		while(true){
			try {
				Socket socket = serverSocket.accept();
				executor.execute(new ConnectionDealHandle(serverView, socket));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void work(){
		
		
	}
}
