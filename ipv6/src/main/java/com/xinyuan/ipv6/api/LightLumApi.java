package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * 光照亮度
 * Created by pen on 2017/12/25.
 */
public class LightLumApi {
    private static final String GET_TEMPERATURE = "get temperature";
    private static final String GET_HUMIDITY = "get humidity";
    private static final String GET_ILLUMINANCE = "get illuminance";//亮度
    private static final String GET_ALL = "get all"; //返回温度湿度亮度

    public static String getTemperature(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_TEMPERATURE);
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String getHumidity(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_HUMIDITY);
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String getIlluminance(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_ILLUMINANCE);
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String[] getAll(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_ALL);
        return Ipv6Util.returnSuccessAll(result);
    }
}
