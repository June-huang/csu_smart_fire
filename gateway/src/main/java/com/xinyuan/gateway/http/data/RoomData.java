package com.xinyuan.gateway.http.data;


import com.xinyuan.gateway.http.util.GatewayHttpUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 从新网关获取教室数据
 * Created by pen on 2017/10/16.
 */
@Component
public class RoomData {

    private static String url = "http://%s/zwave/v2/campus";

    /**
     * 根据ip获取网关中的房间
     */
    public GatewayResult getpage(String path, int offset, int limit) {

        Map<String, Object> map = new HashMap<>();
        map.put("offset", offset);
        map.put("limit", limit);
        return GatewayHttpUtils.doGet(String.format(url, path), map);

    }

    public GatewayResult add(String path, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        return GatewayHttpUtils.doPost(String.format(url, path), map);
    }

    public GatewayResult update(String path, String id, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("id", id);
        return GatewayHttpUtils.doPut(String.format(url, path), map);
    }

    public GatewayResult delete(String path, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return GatewayHttpUtils.deDeleteWithJson(String.format(url, path), map);
    }
}
