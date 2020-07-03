package com.xinyuan.ipv6.core.service.deviceService;

import com.alibaba.fastjson.JSON;
import com.xinyuan.ipv6.api.SwitchApi;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.SwitchButton;
import com.xinyuan.ipv6.core.service.Ipv6DeviceService;
import com.xinyuan.ipv6.core.util.Ipv6CacheUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 开关
 * Created by pen on 2017/12/25.
 */
@Service
public class SwitchService implements BaseIpv6Service, BaseSwicthService {

    @Resource
    private Ipv6DeviceService ipv6DeviceService;

    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String[] datas = SwitchApi.getAll(ipv6Device.getIp(), ipv6Device.getPort());
//        datas = new String[]{"1", "1", "0", "1", "0", "1"};
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        Integer switchNum = ipv6Device.getSwitchNum();
        List<SwitchButton> switchButtons = new ArrayList<>(switchNum);
        if (datas == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            if (switchNum == null || switchNum > datas.length) {
                switchNum = datas.length;
            }
            String setName = ipv6Device.getSwitchName();
            String[] names = null;
            if (setName != null) {
                names = setName.split(",");
            }
            for (int i = 0; i < switchNum; i++) {
                String name = "开关";
                if (names != null && names.length > i) {
                    name = names[i];
                }
                SwitchButton switchButton = new SwitchButton();

                switchButton.setIndex(i + 1);
                switchButton.setOnOff(Objects.equals("1", datas[i]));
                switchButton.setName(name);
                switchButtons.add(switchButton);
            }
        }
        ipv6DeviceInfo.setSwitchButtons(switchButtons);
        return ipv6DeviceInfo;
    }

    @Override
    public boolean setOn(String ip, int port, String mac, Integer index) {
        return SwitchApi.setOn(ip, port, index) != null;
    }

    @Override
    public boolean setOff(String ip, int port, Integer index) {
        return SwitchApi.setOff(ip, port, index) != null;
    }

    @Override
    public boolean operate(Ipv6DeviceDto ipv6DeviceDto) {
        Object value = ipv6DeviceDto.getValue();
        if (value == null) {
            return false;
        }
        boolean result;
        if (Objects.equals(value.toString(), "0")) {
            result = setOff(ipv6DeviceDto.getIp(), ipv6DeviceDto.getPort(), ipv6DeviceDto.getIndex());
        } else {
            result = setOn(ipv6DeviceDto.getIp(), ipv6DeviceDto.getPort(), null, ipv6DeviceDto.getIndex());
        }
        if (result) {
            updateInfo(ipv6DeviceDto.getId());
        }
        return result;
    }

    @Override
    public void updateInfo(String deviceId) {
        Ipv6Device ipv6Device = ipv6DeviceService.selectById(deviceId);
        Ipv6DeviceInfo ipv6DeviceInfo = getInfo(ipv6Device);
        Ipv6CacheUtil.set(deviceId, ipv6DeviceInfo);
    }

    /**
     * 开关  
     * 		灯光
     */
    @Override
    public boolean runScene(SceneDevice sceneDevice) {
        boolean success = true;
        if (sceneDevice.getInfo() != null) {
            Ipv6DeviceInfo info = JSON.parseObject(sceneDevice.getInfo().toString(), Ipv6DeviceInfo.class);
            if (info != null && info.getSwitchButtons() != null) {
                List<SwitchButton> buttons = info.getSwitchButtons();
                for (SwitchButton switchButton : buttons) {
                    //如果设置 为开
                    if (switchButton.getOnOff()) {
                        success = success && setOn(sceneDevice.getIp(), sceneDevice.getPort(), null, switchButton.getIndex());
                    } else {
                        success = success && setOff(sceneDevice.getIp(), sceneDevice.getPort(), switchButton.getIndex());
                    }
                }
            }
        }
        return success;
    }
}