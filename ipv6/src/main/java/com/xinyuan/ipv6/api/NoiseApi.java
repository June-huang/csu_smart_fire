package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * 噪音
 * Created by pen on 2017/12/25.
 */
public class NoiseApi {
    private static final String GET_NOISE = "get noise 50";


    public static String getNoise(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_NOISE);
        return Ipv6Util.returnSuccessMsg(result);
    }
}
