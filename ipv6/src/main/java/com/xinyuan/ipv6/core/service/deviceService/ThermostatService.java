package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.ipv6.api.SerialApi;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.mapper.Ipv6DeviceMapper;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.SwitchButton;
import com.xinyuan.ipv6.core.util.Ipv6CacheUtil;
import com.xinyuan.system.core.mapper.smartclass.DeviceControllerKeyMapper;
import com.xinyuan.system.core.model.smartclass.DeviceControllerKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by pen on 2018/3/20.
 */
@Service
public class ThermostatService implements BaseIpv6Service, BaseSwicthService {
    @Resource
    private DeviceControllerKeyMapper deviceControllerKeyMapper;
    @Resource
    private Ipv6DeviceMapper ipv6DeviceMapper;

    /**
     * 获取一个设备的状态
     */
    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
    	//根据设备型号ID,获取教学设备控制命令  SYS_DEVICE_CONTROLLER_KEY 
        List<DeviceControllerKey> deviceControllerKeys = deviceControllerKeyMapper.queryByModelId(ipv6Device.getModelId());
        //开关按钮
        List<SwitchButton> switchButtonList = new ArrayList<>(2);
        //其他按钮
        List<SwitchButton> otherButtonList = new ArrayList<>();
        for (DeviceControllerKey key : deviceControllerKeys) {
            SwitchButton switchButton = new SwitchButton();
            switchButton.setCmd(key.getCmd());
            //第几个开关
            switchButton.setIndex(key.getSort());
            switchButton.setName(key.getName());
            switchButton.setType(key.getType());
            //0或者1是开关按钮
            if (Objects.equals(key.getType(), 0) || Objects.equals(key.getType(), 1)) {
                switchButtonList.add(switchButton);
            } else {
                otherButtonList.add(switchButton);
            }
        }
        
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        ipv6DeviceInfo.setSwitchButtons(switchButtonList);
        ipv6DeviceInfo.setOtherButton(otherButtonList);
        return ipv6DeviceInfo;
    }

    @Override
    public boolean setOn(String ip, int port, String mac, Integer index) {
        return operate(ip, port, 1);
    }

    @Override
    public boolean setOff(String ip, int port, Integer index) {
        return operate(ip, port, 0);
    }

    @Override
    public boolean operate(Ipv6DeviceDto ipv6DeviceDto) {
        Object value = ipv6DeviceDto.getValue();
        if (value == null) {
            return false;
        }
        boolean result = SerialApi.sendCmd(ipv6DeviceDto.getIp(), ipv6DeviceDto.getPort(), value.toString());
        if (result) {
            updateInfo(ipv6DeviceDto.getId());
        }
        return result;
    }

    @Override
    public void updateInfo(String deviceId) {
        Ipv6Device energyDevice = ipv6DeviceMapper.queryEnergyDeviceByDeviceId(deviceId);
        if (energyDevice != null) {
            new Thread(() -> {
                int i = 0;
                while (i++ < 5) {
                    Ipv6DeviceInfo ipv6DeviceInfo = getInfo(energyDevice);
                    Ipv6CacheUtil.set(deviceId, ipv6DeviceInfo);
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 改动
     * 	特别注意：空调要执行两次，第一次是激活
     */
    @Override
    public boolean runScene(SceneDevice sceneDevice) {
        if (sceneDevice.getValue() != null) {
            //return SerialApi.sendCmd(sceneDevice.getIp(), sceneDevice.getPort(), sceneDevice.getValue().toString());
            
            boolean result = SerialApi.sendCmd(sceneDevice.getIp(), sceneDevice.getPort(), sceneDevice.getValue().toString());
            if (result) {
            	SerialApi.sendCmd(sceneDevice.getIp(), sceneDevice.getPort(), sceneDevice.getValue().toString());
                //updateInfo(sceneDevice.getId());
            }
            return result;
        }
        return false;
    }

    /**
     * 执行开或者关操作
     *
     * @param ip
     * @param port
     * @param type 0为关，1为开
     * @return
     */
    private boolean operate(String ip, int port, int type) {
        List<DeviceControllerKey> keys = ipv6DeviceMapper.queryKeyByDeviceIp(ip, type);
        if (CollectionUtils.isEmpty(keys)) {
            return false;
        }
        return SerialApi.sendCmd(ip, port, keys.get(0).getCmd());
    }
}
