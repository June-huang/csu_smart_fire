package com.xinyuan.ipv6.api;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * 开关
 * Created by pen on 2017/12/25.
 */
public class SwitchApi {
    private static final String GET_ALL = "get all";
    private static  String SET_ON = "set on %s";
    private static  String SET_OFF = "set off %s";
    private static final String GET_NUM = "get num";//有几个开关


    public static String setOn(String ip, int port, int index) {
        String result=  SocketUtil.sendSocket(ip, port, String.format(SET_ON, index));
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String setOff(String ip, int port,int index) {
        String result=  SocketUtil.sendSocket(ip, port, String.format(SET_OFF, index));
        return Ipv6Util.returnSuccessMsg(result);
    }
    public static String getNum(String ip, int port) {
        String result=  SocketUtil.sendSocket(ip, port,GET_NUM);
        return Ipv6Util.returnSuccessMsg(result);
    }

    public static String[] getAll(String ip, int port) {
        String result= SocketUtil.sendSocket(ip, port, GET_ALL.getBytes());
        return Ipv6Util.returnSuccessAll(result);
    }
}