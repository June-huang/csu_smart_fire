package com.xinyuan.ipv6.core.service.deviceService;

import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;

/**
 * Created by pen on 2018/3/15.
 */
public interface BaseIpv6Service {

    /**
     * 从IPv6中获取数据
     *
     * @param ipv6Device
     */
    Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device);

}
