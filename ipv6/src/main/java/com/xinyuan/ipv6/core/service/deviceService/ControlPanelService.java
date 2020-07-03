package com.xinyuan.ipv6.core.service.deviceService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.ipv6.core.ReceiveSocket;
import com.xinyuan.ipv6.core.mapper.ControlPanelCmdMapper;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.service.Ipv6DeviceService;
import com.xinyuan.ipv6.core.service.SceneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

/**
 * 中控面板
 * Created by pen on 2018/3/27.
 */
@Service
public class ControlPanelService {
    @Resource
    private Ipv6DeviceService ipv6DeviceService;
    @Resource
    private ControlPanelCmdMapper controlPanelCmdMapper;
    @Resource
    private SceneService sceneService;

    /**
     * 1
     * 运行中控面板，等待接收数据
     */
    public void runControlPanel() {
        List<Ipv6Device> controlPanels = ipv6DeviceService.listControlPanel();
        if (CollectionUtils.isEmpty(controlPanels)) {
            return;
        }
        for (Ipv6Device controlPanel : controlPanels) {
            new Thread(() -> {
                ReceiveSocket receiveSocket = new ReceiveSocket(controlPanel.getIp(), controlPanel.getPort(), controlPanel.getId(), this);
                receiveSocket.connect();
            }).start();
        }
    }

    /**
     * 通过返回的命令控制场景
     *
     * @param deviceId        中控面板的ID
     * @param controlPanelCmd 中控面板返回的命令
     */
    public void runScene(String deviceId, String controlPanelCmd) {
    	//System.out.println("参数1=" + deviceId + "/n参数2=" + controlPanelCmd);
        String sceneId = controlPanelCmdMapper.querySceneIdByDeviceIdAndCode(deviceId, controlPanelCmd);
        //System.out.println("返回结果="+ sceneId);
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
