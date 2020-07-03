package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.ipv6.api.NoiseApi;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.Sensor;
import com.xinyuan.ipv6.core.util.UnitUtil;
import org.springframework.stereotype.Service;

/**
 * 噪音
 * Created by pen on 2017/12/25.
 */
@Service
public class NoiseService implements BaseIpv6Service {
    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String data = NoiseApi.getNoise(ipv6Device.getIp(), ipv6Device.getPort());
//        data = "300";
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        Sensor sensor = new Sensor();
        if (data == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            sensor.setNoise(data);
            sensor.setNoiseValue(UnitUtil.stringToBigDecimal(data));
        }
        ipv6DeviceInfo.setSensor(sensor);
        return ipv6DeviceInfo;
    }
}
