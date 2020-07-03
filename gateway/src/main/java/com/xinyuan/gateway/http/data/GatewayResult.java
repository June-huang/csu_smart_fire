package com.xinyuan.gateway.http.data;

/**
 * 从网关返回的数据
 * Created by pen on 2017/10/16.
 */
public class GatewayResult {
    /**
     * 访问返回的状态码
     */
    private Integer code;
    /**
     * 返回的数据
     */
    private Object data;
    /**
     * 访问失败返回的提示
     */
    private String erroinfo;
    /**
     * 状态
     */
    private String state;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErroinfo() {
        return erroinfo;
    }

    public void setErroinfo(String erroinfo) {
        this.erroinfo = erroinfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "GatewayResult{" +
                "code=" + code +
                ", data=" + data +
                ", erroinfo='" + erroinfo + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
