package com.xinyuan.gateway.http.data;

import com.xinyuan.gateway.http.util.GatewayHttpUtils;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 从新网关获取设备数据
 * Created by pen on 2017/10/16.
 */
@Component(value = "deviceData")
public class DeviceData {

    private String url = "http://%s/zwave/v2/nodes/manager";

    /**
     * 获取网关所有设备
     * @param path
     * @return
     */
    public GatewayResult getAll(String path) {
        return GatewayHttpUtils.doGet(String.format(url, path));
    }
    

    public GatewayResult getRoomAll(String path,Map<String, Object>  map) {
        return GatewayHttpUtils.doGet(String.format(url, path),map);
    }
    

    private void test() {
        GatewayResult gatewayResult = getAll("192.168.90.22:5000");
        System.out.println(gatewayResult);
    }

    public static void main(String[] args) {
        new DeviceData().test();
    }
}
