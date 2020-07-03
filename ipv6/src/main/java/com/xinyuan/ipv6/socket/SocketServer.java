package com.xinyuan.ipv6.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xinyuan.ipv6.core.mapper.ControlPanelCmdMapper;
import com.xinyuan.ipv6.core.service.SceneService;

@Component
public class SocketServer{
	
	private int port = 9000;//端口号
	private ServerSocket server;//声明服务器
	private static Socket socket;//声明客户端
	
	 @Resource
    private ControlPanelCmdMapper controlPanelCmdMapper;
    @Resource
    private SceneService sceneService;
	    
	/**
	 * 监听程序
	 * @throws Exception
	 */
	public void startLisent(){
		System.out.println("打开监听--稳定版调了顺序的");
	    // 监听指定的端口
	    try {
			server = new ServerSocket(port);
			// server将一直等待连接的到来
			//如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
			ExecutorService threadPool = Executors.newFixedThreadPool(100);
			while (true) {
				socket = server.accept();
				MyThread runnable = new MyThread(socket, this);
				threadPool.submit(runnable);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }


	/**
	 * 2.根据条件找到场景，并执行场景
	 * @param IP 客户端ip
	 * @param controlPanelCmd	中控发过来的质量
	 */
	public void runScene(String IP, String controlPanelCmd) {
		System.out.println("参数1=" + IP + "/n参数2=" + controlPanelCmd);
		//在ip_control_panel_cmd中找到场景id
        String sceneId = controlPanelCmdMapper.querySceneIdByIpAndCode(IP, controlPanelCmd);
        if (sceneId != null) {
        	//创建线程池
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("demo-pool-%d").build();
            ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
            singleThreadPool.execute(() -> sceneService.runScene(sceneId));
            singleThreadPool.shutdown();
        }
	}
}
