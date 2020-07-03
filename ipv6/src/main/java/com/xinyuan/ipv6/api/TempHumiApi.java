package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * 温湿度
 * Created by pen on 2017/12/25.
 */
public class TempHumiApi {
    private static final String GET_TEMPERATURE = "get temperature";
    private static final String GET_HUMIDITY4 = "get humidity 4";
    private static final String GET_ALL = "get all";

    public static String getTemperature(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_TEMPERATURE.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String getHumidity(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_HUMIDITY4.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String[] getAll(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_ALL.getBytes());
        return Ipv6Util.returnSuccessAll(result);
    }
}
