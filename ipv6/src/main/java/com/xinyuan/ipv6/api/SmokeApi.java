package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * 烟雾
 * Created by pen on 2017/12/25.
 */
public class SmokeApi {
    private static final String GET_ALARM = "get alarm";
    private static final String GET_ALL = "get all";


    public static String[] getAll(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_ALL);
        return Ipv6Util.returnSuccessAll(result);
    }

    /**
     * 获取烟雾浓度数据
     * @param ip
     * @param port
     * @return
     */
    public static String getSmoke(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_ALARM);
        return Ipv6Util.returnSuccessMsg(result);
    }
}
