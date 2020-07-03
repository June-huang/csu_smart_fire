package com.xinyuan.ipv6.core.model.ipv6Data;

/**
 * Created by pen on 2018/3/20.
 */
public class Thermostat {
    /**
     * 温度
     */
    private Integer temperature;

    /**
     * 模式
     */
    private Integer mode;

    /**
     * 模式的名称
     */
    private String modeName;

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }
}
