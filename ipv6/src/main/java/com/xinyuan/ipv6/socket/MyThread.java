package com.xinyuan.ipv6.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyThread implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger("ipv6Logger");
	
	private Socket socket;
	
	private SocketServer socketServer;

	private BufferedReader br = null;  
	
	private PrintWriter pw = null;
	
	MyThread(Socket socket, SocketServer socketServer) {
		this.socket = socket;
		this.socketServer = socketServer;
	}

	/**
	 * 1.接收中控面板发过来的数据，并解析
	 */
	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
            while (true) {
                StringBuffer stringBuffer = new StringBuffer();
                byte[] b = new byte[128];
                int n = inputStream.read(b);
                stringBuffer.append(new String(b, 0, n));
                String IP = socket.getInetAddress().toString().replace("/", "");
                logger.info("中控面板"+ IP +"接收结果：" + stringBuffer.toString());
                //System.out.println("中控面板"+ IP +"接收结果：" + stringBuffer.toString());
                socketServer.runScene(IP, stringBuffer.toString());
            }
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null){
					br.close();
				}
				if(pw!=null){
					pw.close();
				}
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
