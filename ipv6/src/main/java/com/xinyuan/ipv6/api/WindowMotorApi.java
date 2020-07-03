package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * Created by pen on 2017/12/25.
 */
public class WindowMotorApi {
    private static final String OPEN = "open";
    private static final String STOP = "stop";
    private static final String CLOSE = "close";
    private static final String SET_POS = "set pos ";//最后空格
    private static final String GET_POS = "get pos";


    public static String open(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, OPEN.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String stop(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, STOP.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String close(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, CLOSE.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String setPos(String ip, int port, double pos) {
        String result = SocketUtil.sendSocket(ip, port, (SET_POS + pos).getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String getPos(String ip, int port) {
        String result = SocketUtil.sendSocket(ip, port, GET_POS.getBytes());
        return Ipv6Util.returnSuccessMsg(result);
    }
}
