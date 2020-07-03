package com.xinyuan.ipv6.core.service.deviceService;

import com.alibaba.fastjson.JSON;
import com.xinyuan.common.util.BeanUtils;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.ipv6.api.ComputerApi;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.mapper.Ipv6DeviceMapper;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.SwitchButton;
import com.xinyuan.ipv6.core.service.Ipv6DeviceService;
import com.xinyuan.ipv6.core.util.Ipv6CacheUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 电脑
 * Created by pen on 2018/4/2.
 */
@Service
public class ComputerService implements BaseIpv6Service, BaseSwicthService {

    @Resource
    private Ipv6DeviceService ipv6DeviceService;
    @Resource
    private Ipv6DeviceMapper ipv6DeviceMapper;

    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        List<SwitchButton> switchButtons = new ArrayList<>(1);
        String setName = ipv6Device.getSwitchName();
        String[] names = null;
        if (setName != null) {
            names = setName.split(",");
        }
        String name = "开关";
        if (ArrayUtils.isNotEmpty(names)) {
            name = names[0];
        }
        SwitchButton switchButton = new SwitchButton();
        switchButton.setIndex(1);
        switchButton.setOnOff(false);
        switchButton.setName(name);
        switchButtons.add(switchButton);
        ipv6DeviceInfo.setSwitchButtons(switchButtons);
        return ipv6DeviceInfo;
    }

    @Override
    public boolean setOn(String ip, int port, String mac, Integer index) {
        return ComputerApi.OpenComputer(mac, ip);
    }

    @Override
    public boolean setOff(String ip, int port, Integer index) {
        return ComputerApi.closeComputer(ip);
    }

    @Override
    public boolean operate(Ipv6DeviceDto ipv6DeviceDto) {
        Object value = ipv6DeviceDto.getValue();
        if (value == null) {
            return false;
        }
        boolean result;
        if (Integer.valueOf(value.toString()) == 0) {
            result = setOff(ipv6DeviceDto.getIp(), ipv6DeviceDto.getPort(), ipv6DeviceDto.getIndex());
        } else {
            result = setOn(ipv6DeviceDto.getIp(), ipv6DeviceDto.getPort(), ipv6DeviceDto.getMac(), ipv6DeviceDto.getIndex());
        }
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
                while (i++ < 12) {
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

    @Override
    public boolean runScene(SceneDevice sceneDevice) {
        Ipv6DeviceDto ipv6DeviceDto = BeanUtils.map(sceneDevice, Ipv6DeviceDto.class);
        if (sceneDevice.getInfo() != null) {
            Ipv6DeviceInfo info = JSON.parseObject(sceneDevice.getInfo().toString(), Ipv6DeviceInfo.class);
            ipv6DeviceDto.setInfo(info);
            int value = 0;
            if (info != null && info.getSwitchButtons() != null) {
                SwitchButton button = info.getSwitchButtons().get(0);
                //如果设置 为开
                if (button.getOnOff()) {
                    value = 1;
                }
                ipv6DeviceDto.setValue(value);
                return operate(ipv6DeviceDto);
            }
        }
        return false;
    }

    /**
     * 从一堆IPv6中找一个ipv6
     *
     * @param list
     * @param ipv6DeviceId
     * @return
     */
    private Ipv6Device findDevice(List<Ipv6Device> list, String ipv6DeviceId) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (Ipv6Device ipv6Device1 : list) {
            if (Objects.equals(ipv6Device1.getId(), ipv6DeviceId)) {
                return ipv6Device1;
            }
        }
        return null;
    }
}
