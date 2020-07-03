package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;

/**
 * Created by pen on 2018/3/15.
 */
public interface BaseSwicthService {
    /**
     * 开
     *
     * @param ip    IP
     * @param port  端口
     * @param mac  mac地址（电脑等用到）
     * @param index 第几个（一个设备有多个开关）
     * @return
     */
    boolean setOn(String ip, int port, String mac,Integer index);


    /**
     * 关
     *
     * @param ip    IP
     * @param port  端口
     * @param index 第几个（一个设备有多个开关）
     * @return
     */
    boolean setOff(String ip, int port, Integer index);


    /**
     * 操作一个设备
     *
     * @param ipv6DeviceDto
     * @return
     */
    boolean operate(Ipv6DeviceDto ipv6DeviceDto);

    /**
     * 强制刷新数据
     *
     * @param deviceId
     */
    void updateInfo(String deviceId);

    /**
     * 运行场景里的设备
     *
     * @return
     */
    boolean runScene(SceneDevice sceneDevice);

}
