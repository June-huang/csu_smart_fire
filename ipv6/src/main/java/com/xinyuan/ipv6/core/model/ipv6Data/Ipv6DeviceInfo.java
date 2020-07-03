package com.xinyuan.ipv6.core.model.ipv6Data;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * ipv6的状态，开关状态等
 * Created by pen on 2018/3/14.
 */
public class Ipv6DeviceInfo {

    /**
     * 连接状态
     */
    private boolean connectState = true;

    /**
     * 上次更新时间
     */
    private Long updateTime;

    /**
     * 传感器数据
     */
    private Sensor sensor;

    /**
     * 窗帘等的进度条
     */
    private String SwichMutilevel;

    /**
     * 能耗数据
     */
    private Meter meter;

    /**
     * 扩展的value
     */
    private Object value;

    /**
     * 开关
     */
    private Boolean onOff;

    /**
     * 开关按钮
     */
    private List<SwitchButton> switchButtons;

    /**
     * 其他按钮
     */
    private List<SwitchButton> otherButton;

    /**
     * 空调的属性
     */
    private Thermostat thermostat;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public String getSwichMutilevel() {
        return SwichMutilevel;
    }

    public void setSwichMutilevel(String swichMutilevel) {
        SwichMutilevel = swichMutilevel;
    }

    public List<SwitchButton> getSwitchButtons() {
        return switchButtons;
    }

    public void setSwitchButtons(List<SwitchButton> switchButtons) {
        this.switchButtons = switchButtons;
    }

    public List<SwitchButton> getOtherButton() {
        return otherButton;
    }

    public void setOtherButton(List<SwitchButton> otherButton) {
        this.otherButton = otherButton;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(Boolean onOff) {
        this.onOff = onOff;
    }

    public Thermostat getThermostat() {
        return thermostat;
    }

    public void setThermostat(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    public Boolean getConnectState() {
        return connectState;
    }

    public void setConnectState(Boolean connectState) {
        this.connectState = connectState;
    }

    @Override
    public String toString() {
        return "Ipv6DeviceInfo{" +
                "connectState=" + connectState +
                ", sensor=" + sensor +
                ", SwichMutilevel='" + SwichMutilevel + '\'' +
                ", meter=" + meter +
                ", value=" + value +
                ", onOff=" + onOff +
                ", switchButtons=" + switchButtons +
                ", otherButton=" + otherButton +
                ", thermostat=" + thermostat +
                '}';
    }
}
