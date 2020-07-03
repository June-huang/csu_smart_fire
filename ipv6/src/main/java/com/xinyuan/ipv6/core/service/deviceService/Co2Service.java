package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.ipv6.api.Co2Api;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.Sensor;
import com.xinyuan.ipv6.core.util.UnitUtil;
import org.springframework.stereotype.Service;

/**
 * 二氧化碳
 * Created by pen on 2017/12/25.
 */
@Service
public class Co2Service implements BaseIpv6Service {
    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String data = Co2Api.getCo2(ipv6Device.getIp(), ipv6Device.getPort());
//        data = "200";
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        Sensor sensor = new Sensor();
        if (data == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            sensor.setCo2(data);
            sensor.setCo2Value(UnitUtil.stringToBigDecimal(data));
        }
        ipv6DeviceInfo.setSensor(sensor);
        return ipv6DeviceInfo;
    }
}
