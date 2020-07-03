package com.xinyuan.ipv6.core.util;

import com.xinyuan.common.util.RedisUtil;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;

/**
 * 缓存IPV6数据
 * Created by pen on 2018/5/11.
 */
public class Ipv6CacheUtil {

    /**
     * 过期时间默认1800秒（30分钟）
     */
    private static Integer EXPIRE = 1800;
    /**
     * redis 里存放ipv6数据的文件夹名称
     */
    private static String PREFIX = "ipv6:info:";

    /**
     * 往redis缓存Ipv6数据
     *
     * @param id
     * @param ipv6DeviceInfo
     */
    public static void set(String id, Ipv6DeviceInfo ipv6DeviceInfo) {
        RedisUtil.set(PREFIX + id, ipv6DeviceInfo, EXPIRE);
    }

    /**
     * 从缓存里获取Ipv6信息
     *
     * @param id
     * @return
     */
    public static Ipv6DeviceInfo get(String id) {
        return RedisUtil.get(PREFIX + id, Ipv6DeviceInfo.class);
    }
}
