package com.xinyuan.ipv6.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by pen on 2017/12/25.
 */
public class Ipv6Util {
    /**
     * 返回成功的信息。失败则返回Null
     *
     * @param result
     * @return
     */
    public static String returnSuccessMsg(String result) {
        if (success(result)) {
            return getMessage(result);
        }
        return null;
    }

    /**
     * 获取多个数据的时候，按顺序返回数据。失败返回Null
     *
     * @param result
     * @return
     */
    public static String[] returnSuccessAll(String result) {
        if (success(result)) {
            String msg = getMessage(result);
            return msg.split(",");
        }
        return null;
    }

    /**
     * 判断访问接口是否成功
     *
     * @param msg
     * @return
     */
    public static boolean success(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return false;
        }
        return msg.startsWith("ok:");
    }

    /**
     * 获取返回的消息（去掉前面的ok或者error字段）,去掉空格
     *
     * @param msg
     * @return
     */
    public static String getMessage(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return "";
        }
        if (!msg.contains(":")) {
            return "";
        }
        return msg.substring(msg.indexOf(":") + 1).replace(" ", "");
    }
}
