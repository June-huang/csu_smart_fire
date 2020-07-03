package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * PM2.5
 * Created by pen on 2017/12/25.
 */
public class Pm2d5Api {
    private static final String GET_PM1D0 = "get pm1.0";
    private static final String GET_PM2D5 = "get pm2.5";
    private static final String GET_PM10 = "get pm10";
    private static final String GET_ALL = "get all";


    /**
     * 获取pm1.0数据
     * @param ip
     * @param port
     * @return
     */
    public static String getPm1d0(String ip, int port) {
        String result= SocketUtil.sendSocket(ip, port, GET_PM1D0.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    /**
     * pm2.5数据
     * @param ip
     * @param port
     * @return
     */
    public static String getPm2d5(String ip, int port) {
        String result= SocketUtil.sendSocket(ip, port, GET_PM2D5.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    /**
     * pm10
     * @param ip
     * @param port
     * @return
     */
    public static String getPm10(String ip, int port) {
        String result= SocketUtil.sendSocket(ip, port, GET_PM10.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    /**
     * 同时返回pm1.0, pm2.5, pm10
     * @param ip
     * @param port
     * @return
     */
    public static String[] getAll(String ip, int port) {
        String result= SocketUtil.sendSocket(ip, port, GET_ALL.getBytes());
        return Ipv6Util.returnSuccessAll(result);
    }
}
