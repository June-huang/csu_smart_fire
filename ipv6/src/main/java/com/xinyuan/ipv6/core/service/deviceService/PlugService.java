package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.ipv6.api.PlugApi;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.model.ipv6Data.Meter;
import com.xinyuan.ipv6.core.util.UnitUtil;
import org.springframework.stereotype.Service;

/**
 * 能耗插座（只测功率，不作为开关）
 * Created by pen on 2017/12/25.
 */
@Service
public class PlugService implements BaseIpv6Service {

    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        String power = PlugApi.getPower(ipv6Device.getIp(), ipv6Device.getPort());
        String energy = PlugApi.getEnergy(ipv6Device.getIp(), ipv6Device.getPort());
//        power = "100";
//        energy = "100";
        Ipv6DeviceInfo ipv6DeviceInfo = new Ipv6DeviceInfo();
        Meter meter = new Meter();
        if (power == null || energy == null) {
            ipv6DeviceInfo.setConnectState(false);
        } else {
            meter.setPower(UnitUtil.stringToBigDecimal(power));
            meter.setElectricity(UnitUtil.stringToBigDecimal(energy));
        }
        ipv6DeviceInfo.setMeter(meter);
        return ipv6DeviceInfo;
    }
}
