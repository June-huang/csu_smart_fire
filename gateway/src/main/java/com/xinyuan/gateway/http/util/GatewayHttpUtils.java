package com.xinyuan.gateway.http.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinyuan.gateway.http.data.GatewayResult;
import com.xinyuan.gateway.http.exception.GatewayException;

/**
 * Created by pen on 2017/5/24.
 */
public class GatewayHttpUtils {

    /**
     * 默认超时时间，5秒
     */
    private static final int DEFAULT_TIMEOUT = 5000;

    /**
     * 只传入Url
     *
     * @param url
     * @return
     */
    public static GatewayResult doGet(String url) {
        return doGet(url, null, DEFAULT_TIMEOUT);
    }

    /**
     * 传入url，查询参数
     *
     * @param url
     * @param map
     * @return
     */
    public static GatewayResult doGet(String url, Map<String, Object> map) {
        return doGet(url, map, DEFAULT_TIMEOUT);
    }

    public static GatewayResult doGet(String url, Map<String, Object> map, int connectTimeout) {
        HttpGet httpGet = new HttpGet();
        return doGetOrDelete(httpGet, url, map, connectTimeout);
    }

    /**
     * 正常的删除请求
     *
     * @param url
     * @param map
     * @return
     */
    public static GatewayResult doDelete(String url, Map<String, Object> map) {
        return doDelete(url, map, DEFAULT_TIMEOUT);
    }

    public static GatewayResult doDelete(String url, Map<String, Object> map, int connectTimeour) {
        HttpDelete httpDelete = new HttpDelete();
        return doGetOrDelete(httpDelete, url, map, connectTimeour);
    }

    /**
     * 带json的删除请求
     *
     * @param url
     * @param map
     * @return
     */
    public static GatewayResult deDeleteWithJson(String url, Map<String, Object> map) {
        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
        return doPostOrPut(httpDeleteWithBody, JSON.toJSONString(map), DEFAULT_TIMEOUT);
    }

    /**
     * 传递json格式的参数
     *
     * @param url
     * @param json
     * @return
     */
    public static GatewayResult doPost(String url, String json) {
        return doPost(url, json, DEFAULT_TIMEOUT);
    }

    public static GatewayResult doPost(String url, Map<String, Object> map) {
        return doPost(url, JSON.toJSONString(map));
    }

    public static GatewayResult doPost(String url, String json, int connectTime) {
        HttpPost httpPost = new HttpPost(url);
        return doPostOrPut(httpPost, json, connectTime);
    }

    public static GatewayResult doPut(String url, Map<String, Object> map) {
        return doPut(url, JSON.toJSONString(map), DEFAULT_TIMEOUT);
    }

    public static GatewayResult doPut(String url, String json, int connectTime) {
        HttpPut httpPut = new HttpPut(url);
        return doPostOrPut(httpPut, json, connectTime);
    }

    /**
     * get或者是正常的delete方法
     *
     * @param t
     * @param connectTimeout
     * @return
     */
    private static <T extends HttpRequestBase> GatewayResult doGetOrDelete(T t, String url, Map<String, Object> map, int connectTimeout) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        GatewayResult gatewayResult = null;
        CloseableHttpResponse httpResponse = null;
        List<NameValuePair> params = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), Objects.toString(entry.getValue())));
            }
        }
        try {
            //转换为键值对
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
            URI uri = URI.create(url + (StringUtils.isEmpty(str) ? "" : "?" + str));
            t.setURI(uri);
            System.out.println("正在访问：" + t.getURI());
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(connectTimeout)
                    .build();
            t.setConfig(requestConfig);
            httpResponse = httpClient.execute(t);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity, "utf-8");
            System.out.println("访问结果：" + result);
            gatewayResult = JSONObject.parseObject(result, GatewayResult.class);
            httpResponse.close();
        } catch (IOException e) {
            System.err.println("访问失败");
            e.printStackTrace();
            throw new GatewayException("访问失败");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gatewayResult;
    }

    /**
     * post或者是Put方法，或者是带json的delete方法
     *
     * @param t
     * @param json
     * @param connectTime
     * @param <T>
     * @return
     */
    private static <T extends HttpEntityEnclosingRequestBase> GatewayResult doPostOrPut(T t, String json, int connectTime) {
        System.out.println("正在连接：" + t.getURI());
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        t.setHeader("Content-Type", "application/json;charset=UTF-8");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTime)
                .setSocketTimeout(DEFAULT_TIMEOUT)
                .build();
        t.setConfig(requestConfig);
        GatewayResult gatewayResult = null;
        CloseableHttpResponse response = null;
        try {
            StringEntity se = new StringEntity(json, "utf-8");
            se.setContentType("text/json");
            t.setEntity(se);
            response = httpClient.execute(t);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            response.close();
            gatewayResult = JSONObject.parseObject(result, GatewayResult.class);
        } catch (IOException e) {
            System.out.println("访问失败：" + t.getURI());
            e.printStackTrace();
            throw new GatewayException("访问失败");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gatewayResult;
    }
}
