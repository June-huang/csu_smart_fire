package com.xinyuan.ipv6.api;

import org.springframework.util.StringUtils;

import com.xinyuan.common.util.SocketUtil;
import com.xinyuan.ipv6.core.util.Ipv6Util;

/**
 * Created by pen on 2017/12/25.
 */
public class SerialApi {
	
    static final String SET_CMD = "set:";
    /**
     * IPV4的
     * GC设备，发命令
     *
     * @param ip
     * @param port
     * @param cmd
     * @return
     */
//    public static boolean sendCmd(String ip, int port, String cmd) {
//        return SocketUtil.sendSocketNotResult(ip, port, toByteArray(cmd));
//    }


    /**
     * IPV6的
     * @param ip
     * @param port
     * @param cmd
     * @return
     */
    public static boolean sendCmd(String ip, int port, String cmd) {
        byte[] sets = SET_CMD.getBytes();
        byte[] cmdByte = toByteArray(cmd);
        byte[] datas = new byte[sets.length + cmdByte.length];
        System.arraycopy(sets, 0, datas, 0, sets.length);
        System.arraycopy(cmdByte, 0, datas, sets.length, cmdByte.length);
        String result= SocketUtil.sendSocket(ip, port, datas);
        return Ipv6Util.success(result);
    }


    /**
     * 16进制转化为字节数组
     * @param hexString
     * @return
     */
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            throw new IllegalArgumentException("this hexString must not be empty");
        }

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        // 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
        for (int i = 0; i < byteArray.length; i++) {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }
    
}
