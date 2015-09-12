package cn.edu.zucc.TPF.dealConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class NonBlockConnectionServer implements Runnable{
	private Selector selector = null;
	private ServerSocketChannel serverSocketChannel = null;
	private int port = 8000;
	private Charset charset= Charset.forName("GBK");
	private ExecutorService executor;
	private int tag;
	private Object lock =new Object();

	public NonBlockConnectionServer(ExecutorService executor ,int tag) throws IOException {
		this.executor = executor;
		this.tag = tag;
		selector = Selector.open();		
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().setReuseAddress(true);
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		System.out.println("Server Start...");
		// TODO Auto-generated constructor stub
	}
    
	public void run(){
		service();
		while(tag==0){
			try {
				SocketChannel socketChannel = serverSocketChannel.accept();
				socketChannel.configureBlocking(false);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				
				synchronized(lock){
					selector.wakeup();
					socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void service(){
		while(tag==0){
			synchronized(lock){
				SelectionKey key = null;				
			try {
				if(selector.select()==0) continue;					
				Set <SelectionKey> readyKeys = selector.selectedKeys();
				Iterator <SelectionKey> iterator = readyKeys.iterator();
				
				while(iterator.hasNext()){
					key = iterator.next();
					iterator.remove();
					if(key.isReadable()){
						//
					}
				}
			} catch (IOException e) {
						// TODO Auto-generated catch block
				e.printStackTrace();									
			}
			}
		}
	}
	
	//解码
	public String decode(ByteBuffer buffer){
		CharBuffer charBuffer = charset.decode(buffer); 
		return charBuffer.toString();
	}
	
	//编码
	public ByteBuffer encode(String str){
		return charset.encode(str);
	}
	
	public void receive(SelectionKey key){
		ByteBuffer buffer = (ByteBuffer)key.attachment();
		SocketChannel socketChannel = (SocketChannel)key.channel();
		ByteBuffer readBuff = ByteBuffer.allocate(32);
		try {
			socketChannel.read(readBuff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readBuff.flip();
		/**把读到的数据放到buffer中*/
		buffer.limit(buffer.capacity());
		buffer.put(readBuff);
		//Save to DataBase
		
	}
	
	public void write(){
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
