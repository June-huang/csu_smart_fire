package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.ipv6.api.TempHumiApi;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.Sensor;
import com.xinyuan.ipv6.core.util.UnitUtil;
import org.springframework.stereotype.Service;

/**
 * 温湿度
 * Created by pen on 2017/12/25.
 */
@Service
public class TempHumiService implements BaseIpv6Service {

    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String[] datas = TempHumiApi.getAll(ipv6Device.getIp(), ipv6Device.getPort());
//        datas = new String[]{"23C", "80%"};
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        Sensor sensor = new Sensor();
        if (datas == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            sensor.setTemperature(datas[0]);
            sensor.setTemperatureValue(UnitUtil.stringToBigDecimal(datas[0]));
            sensor.setHumidity(datas[1]);
            sensor.setHumidityValue(UnitUtil.stringToBigDecimal(datas[1]));
        }
        ipv6DeviceInfo.setSensor(sensor);
        return ipv6DeviceInfo;
    }
}
