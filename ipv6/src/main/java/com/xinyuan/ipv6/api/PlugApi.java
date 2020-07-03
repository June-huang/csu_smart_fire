package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * 排插
 * Created by pen on 2017/12/25.
 */
public class PlugApi {

    /**
     * 电压
     */
    private static final String GET_VOLTAGE = "get voltage";
    /**
     * 电流
     */
    private static final String GET_CURRENT = "get current";
    /**
     * 功率
     */
    private static final String GET_POWER = "get power";
    /**
     * 用电量
     */
    private static final String GET_ENERGY = "get energy";

    private static final String GET_ALL = "get all";


    public static String getVoltage(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_VOLTAGE);
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String getCurrent(String ip, int port) {
        return SocketUtil.sendSocket(ip, port, GET_CURRENT);
    }

    public static String getPower(String ip, int port) {
        return SocketUtil.sendSocket(ip, port, GET_POWER);
    }

    public static String getEnergy(String ip, int port) {
        return SocketUtil.sendSocket(ip, port, GET_ENERGY);
    }

    public static String[] getAll(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_ALL);
        return Ipv6Util.returnSuccessAll(result);
    }
}
