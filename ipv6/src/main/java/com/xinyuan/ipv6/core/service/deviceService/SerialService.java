package com.xinyuan.ipv6.core.service.deviceService;

import com.alibaba.fastjson.JSON;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.ipv6.api.SerialApi;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.mapper.Ipv6DeviceMapper;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.SwitchButton;
import com.xinyuan.ipv6.core.service.Ipv6DeviceService;
import com.xinyuan.system.core.mapper.smartclass.DeviceControllerKeyMapper;
import com.xinyuan.system.core.model.smartclass.DeviceControllerKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by pen on 2017/12/25.
 */
@Service
public class SerialService implements BaseIpv6Service, BaseSwicthService {
    @Resource
    private DeviceControllerKeyMapper deviceControllerKeyMapper;
    @Resource
    private Ipv6DeviceService ipv6DeviceService;
    @Resource
    private Ipv6DeviceMapper ipv6DeviceMapper;
    
    private final Logger logger = LoggerFactory.getLogger("ipv6Logger");

    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        List<DeviceControllerKey> deviceControllerKeys = deviceControllerKeyMapper.queryByModelId(ipv6Device.getModelId());
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        List<SwitchButton> switchButtonList = new ArrayList<>(2);
        List<SwitchButton> otherButtonList = new ArrayList<>();
        for (DeviceControllerKey key : deviceControllerKeys) {
            SwitchButton switchButton = new SwitchButton();
            switchButton.setCmd(key.getCmd());
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
        ipv6DeviceInfo.setSwitchButtons(switchButtonList);
        ipv6DeviceInfo.setOtherButton(otherButtonList);
        return ipv6DeviceInfo;
    }

    @Override
    public boolean setOn(String ip, int port, String mac, Integer index) {
        return operateOnOff(ip, port, 1);
    }

    @Override
    public boolean setOff(String ip, int port, Integer index) {
        return operateOnOff(ip, port, 0);
    }

    /**
     * 执行开或者关操作
     *
     * @param ip
     * @param port
     * @param type 0为关，1为开
     * @return
     */
    private boolean operateOnOff(String ip, int port, int type) {
        List<DeviceControllerKey> keys = ipv6DeviceMapper.queryKeyByDeviceIp(ip, type);
        if (CollectionUtils.isEmpty(keys)) {
            return false;
        }
        return SerialApi.sendCmd(ip, port, keys.get(0).getCmd());
    }

    /**
     * 操作设备
     *
     * @param ipv6DeviceDto
     * @return
     */
    @Override
    public boolean operate(Ipv6DeviceDto ipv6DeviceDto) {
        Object value = ipv6DeviceDto.getValue();
        if (value == null) {
            return false;
        }
        logger.info("操作一个设备===ip:"+ipv6DeviceDto.getIp()+",port:" + ipv6DeviceDto.getPort()+",value:"+ value.toString());
        return SerialApi.sendCmd(ipv6DeviceDto.getIp(), ipv6DeviceDto.getPort(), value.toString());
    }

    @Override
    public void updateInfo(String deviceId) {
        Ipv6Device ipv6Device = ipv6DeviceService.selectById(deviceId);
        String energyId = ipv6Device.getEnergyDeviceId();
        if (energyId != null) {
        }
    }

    @Override
    public boolean runScene(SceneDevice sceneDevice) {
        if (sceneDevice.getValue() != null) {
            return SerialApi.sendCmd(sceneDevice.getIp(), sceneDevice.getPort(), sceneDevice.getValue().toString());
        }
        if (sceneDevice.getInfo() == null) {
            return false;
        }
        Ipv6DeviceInfo info = JSON.parseObject(sceneDevice.getInfo().toString(), Ipv6DeviceInfo.class);
        if (info != null && info.getOnOff() != null) {
            List<SwitchButton> buttons = info.getSwitchButtons();
            String cmd;
            //如果设置为开
            if (info.getOnOff()) {
                cmd = getCmd(buttons, true);
            } else {
                cmd = getCmd(buttons, false);
            }
            if (cmd != null) {
                return SerialApi.sendCmd(sceneDevice.getIp(), sceneDevice.getPort(), cmd);
            } else {
                //如果没有拿到命令，就从数据库里拿了
                if (info.getOnOff()) {
                    return setOn(sceneDevice.getIp(), sceneDevice.getPort(), null, null);
                } else {
                    return setOff(sceneDevice.getIp(), sceneDevice.getPort(), null);
                }
            }
        }
        return false;
    }

    /**
     * 获取开关的命令
     *
     * @param switchButtons
     * @param on            是否为开的命令（是为开，否为关）
     * @return
     */
    private String getCmd(List<SwitchButton> switchButtons, boolean on) {
        for (SwitchButton switchButton : switchButtons) {
            if (on && Objects.equals(switchButton.getType(), 1)) {
                return switchButton.getCmd();
            } else if (!on && Objects.equals(switchButton.getType(), 0)) {
                return switchButton.getCmd();
            }
        }
        return null;
    }
}
