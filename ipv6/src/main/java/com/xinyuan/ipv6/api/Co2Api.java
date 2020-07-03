package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * 二氧化碳
 * Created by pen on 2017/12/25.
 */
public class Co2Api {
    private static final String GET_CO2 = "get co2";
    private static final String GET_ALL = "get all";


    public static String[] getAll(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_ALL);
        return Ipv6Util.returnSuccessAll(result);
    }

    /**
     * 获取二氧化碳浓度数据
     * @param ip
     * @param port
     * @return
     */
    public static String getCo2(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_CO2);
        return Ipv6Util.returnSuccessMsg(result);
    }
}
