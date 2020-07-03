package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.ipv6.api.Pm2d5Api;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.Sensor;
import com.xinyuan.ipv6.core.util.UnitUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * PM2.5
 * Created by pen on 2017/12/25.
 */
@Service
public class Pm2d5Service implements BaseIpv6Service {


    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String data = Pm2d5Api.getPm2d5(ipv6Device.getIp(), ipv6Device.getPort());
//        data = "200";
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        Sensor sensor = new Sensor();
        if (data == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            sensor.setPm2d5(data);
            sensor.setPm2d5Value(UnitUtil.stringToBigDecimal(data));
        }
        ipv6DeviceInfo.setSensor(sensor);
        return ipv6DeviceInfo;
    }
}
