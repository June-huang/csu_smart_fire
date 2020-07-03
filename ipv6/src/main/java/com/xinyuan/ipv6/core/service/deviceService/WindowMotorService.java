package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.common.util.BeanUtils;
import com.xinyuan.ipv6.api.WindowMotorApi;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.service.Ipv6DeviceService;
import com.xinyuan.ipv6.core.util.Ipv6CacheUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by pen on 2017/12/25.
 */
@Service
public class WindowMotorService implements BaseIpv6Service, BaseSwicthService {

    @Resource
    private Ipv6DeviceService ipv6DeviceService;

    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String data = WindowMotorApi.getPos(ipv6Device.getIp(), ipv6Device.getPort());
//        data = "100";
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        if (data == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            ipv6DeviceInfo.setSwichMutilevel(data);
        }
        return ipv6DeviceInfo;
    }

    @Override
    public boolean setOn(String ip, int port, String mac, Integer index) {
        return WindowMotorApi.open(ip, port) != null;
    }

    @Override
    public boolean setOff(String ip, int port, Integer index) {
        return WindowMotorApi.close(ip, port) != null;
    }

    @Override
    public boolean operate(Ipv6DeviceDto ipv6DeviceDto) {
        Object value = ipv6DeviceDto.getValue();
        if (value == null) {
            return false;
        }
        String ip = ipv6DeviceDto.getIp();
        int port = ipv6DeviceDto.getPort();
        String flag;
        int pos = Integer.valueOf(value.toString());
        switch (pos) {
            case -1:
                flag = WindowMotorApi.stop(ip, port);
                break;
            case 0:
                flag = WindowMotorApi.close(ip, port);
                break;
            case 100:
                flag = WindowMotorApi.open(ip, port);
                break;
            default:
                flag = WindowMotorApi.setPos(ip, port, pos);
                break;
        }
        //updateInfo(ipv6DeviceDto.getId());
        return flag != null;
    }

    @Override
    public void updateInfo(String deviceId) {
        Ipv6Device ipv6Device = ipv6DeviceService.selectById(deviceId);
        if (ipv6Device != null) {
            new Thread(() -> {
                int i = 0;
                while (i++ < 12) {
                    Ipv6DeviceInfo ipv6DeviceInfo = getInfo(ipv6Device);
                    Ipv6CacheUtil.set(deviceId, ipv6DeviceInfo);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public boolean runScene(SceneDevice sceneDevice) {
        return operate(BeanUtils.map(sceneDevice, Ipv6DeviceDto.class));
    }
}
