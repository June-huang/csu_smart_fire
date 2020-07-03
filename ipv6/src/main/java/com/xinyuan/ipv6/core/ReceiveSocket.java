package com.xinyuan.ipv6.core;

import com.xinyuan.ipv6.core.service.deviceService.ControlPanelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * 持续接收socket数据，断开自动重连
 * Created by pen on 2018/3/27.
 */
public class ReceiveSocket {
    private final Logger logger = LoggerFactory.getLogger("ipv6Logger");
    private String ip;
    private int port;
    private String controlPanelId;
    private ControlPanelService controlPanelService;
    private Socket socket;
    //连接状态（是否正在连接）
    private boolean isConnecting = false;

    public ReceiveSocket(String ip, int port, String controlPanelId, ControlPanelService controlPanelService) {
        this.ip = ip;
        this.port = port;
        this.controlPanelId = controlPanelId;
        this.controlPanelService = controlPanelService;
        send();
    }

    public synchronized void connect() {
        socket = new Socket();
        try {
            logger.info("中控面板" + ip + ":正在连接");
            isConnecting = true;
            socket.connect(new InetSocketAddress(ip, port), 5000);
            isConnecting = false;
            respond();
        } catch (IOException e) {
            logger.error("中控面板" + ip + ":连接失败，3秒后重连……");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            this.connect();
        }
    }

    private void respond() {
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                StringBuffer stringBuffer = new StringBuffer();
                byte[] b = new byte[128];
                int n = inputStream.read(b);
                stringBuffer.append(new String(b, 0, n));
                logger.info("中控面板接收结果：" + stringBuffer.toString());
                controlPanelService.runScene(controlPanelId, stringBuffer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.connect();
        }
    }

    private void send() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(60);
                    socket.sendUrgentData(0xFF);
                } catch (IOException e) {
                    if (!isConnecting) {
                        this.connect();
                    }
                } catch (InterruptedException e) {
                    if (!isConnecting) {
                        this.connect();
                    }
                }
            }
        }).start();
    }
}
