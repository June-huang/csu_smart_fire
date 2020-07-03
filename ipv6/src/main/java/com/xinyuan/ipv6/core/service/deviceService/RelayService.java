package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.common.util.BeanUtils;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.common.util.RedisUtil;
import com.xinyuan.gateway.http.data.DeviceData;
import com.xinyuan.ipv6.api.SwitchApi;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.SwitchButton;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 投影幕布（有两个电机）
 * Created by pen on 2018/4/2.
 */
@Service
public class RelayService implements BaseIpv6Service, BaseSwicthService {

    final Logger logger = LoggerFactory.getLogger("ipv6Logger");

    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String[] datas = SwitchApi.getAll(ipv6Device.getIp(), ipv6Device.getPort());
//        datas = new String[]{"0","1"};
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        if (datas == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            List<SwitchButton> switchButtons = new ArrayList<>(datas.length);
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
        }
        return ipv6DeviceInfo;
    }

    /**
     * 幕布升
     */
    @Override
    public boolean setOn(String ip, int port, String mac, Integer index) {
        if (!stop(ip, port)) {
            return false;
        }
        return SwitchApi.setOn(ip, port, 1) != null;
    }

    /**
     * 幕布降
     */
    @Override
    public boolean setOff(String ip, int port, Integer index) {
        if (!stop(ip, port)) {
            return false;
        }
        return SwitchApi.setOn(ip, port, 2) != null;
    }

    private boolean stop(String ip, int port) {
        String result1 = SwitchApi.setOff(ip, port, 1);
        String result2 = SwitchApi.setOff(ip, port, 2);
        return result1 != null && result2 != null;
    }

    @Override
    public boolean operate(Ipv6DeviceDto ipv6DeviceDto) {
        Object value = ipv6DeviceDto.getValue();
        if (value == null) {
            return false;
        }
        String ip = ipv6DeviceDto.getIp();
        Integer port = ipv6DeviceDto.getPort();
        int valueInt = Integer.valueOf(value.toString());
        if (valueInt == 0) {
            if (!setOff(ip, port, ipv6DeviceDto.getIndex())) {
                return false;
            }
        } else if (valueInt == 1) {
            if (!setOn(ip, port, null, ipv6DeviceDto.getIndex())) {
                return false;
            }
        } else {
            return stop(ip, port);
        }
        return true;
    }


    @Override
    public void updateInfo(String deviceId) {
    }

    @Override
    public boolean runScene(SceneDevice sceneDevice) {
        return operate(BeanUtils.map(sceneDevice, Ipv6DeviceDto.class));
    }
}
