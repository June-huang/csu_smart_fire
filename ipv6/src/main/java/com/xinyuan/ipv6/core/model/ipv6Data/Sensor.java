package com.xinyuan.ipv6.core.model.ipv6Data;

import com.xinyuan.ipv6.core.util.UnitUtil;

import java.math.BigDecimal;

/**
 * 传感器数据
 * Created by pen on 2018/3/16.
 */
public class Sensor {
    /**
     * 亮度
     */
    private String light;
    private BigDecimal lightValue;
    /**
     * 湿度
     */
    private String humidity;
    private BigDecimal humidityValue;
    /**
     * 温度
     */
    private String temperature;
    private BigDecimal temperatureValue;
    /**
     * 二氧化碳
     */
    private String co2;
    private BigDecimal co2Value;
    /**
     * PM2.5
     */
    private String pm2d5;
    private BigDecimal pm2d5Value;

    /**
     * 噪音
     */
    private String noise;
    private BigDecimal noiseValue;

    /**
     * 烟雾
     */
    private String smoke;
    private BigDecimal smokeValue;

    /**
     * 大于，小于，或者等于
     */
//    private String type;

    public BigDecimal getLightValue() {
        return lightValue;
    }

    public BigDecimal getHumidityValue() {
        return humidityValue;
    }

    public BigDecimal getTemperatureValue() {
        return temperatureValue;
    }

    public BigDecimal getCo2Value() {
        return co2Value;
    }

    public BigDecimal getPm2d5Value() {
        return pm2d5Value;
    }

    public BigDecimal getNoiseValue() {
        return noiseValue;
    }

    public BigDecimal getSmokeValue() {
        return smokeValue;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getPm2d5() {
        return pm2d5;
    }

    public void setPm2d5(String pm2d5) {
        this.pm2d5 = pm2d5;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public void setLightValue(BigDecimal lightValue) {
        this.lightValue = lightValue;
    }

    public void setHumidityValue(BigDecimal humidityValue) {
        this.humidityValue = humidityValue;
    }

    public void setTemperatureValue(BigDecimal temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public void setCo2Value(BigDecimal co2Value) {
        this.co2Value = co2Value;
    }

    public void setPm2d5Value(BigDecimal pm2d5Value) {
        this.pm2d5Value = pm2d5Value;
    }

    public void setNoiseValue(BigDecimal noiseValue) {
        this.noiseValue = noiseValue;
    }

    public void setSmokeValue(BigDecimal smokeValue) {
        this.smokeValue = smokeValue;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "light='" + light + '\'' +
                ", lightValue='" + lightValue + '\'' +
                ", humidity='" + humidity + '\'' +
                ", humidityValue='" + humidityValue + '\'' +
                ", temperature='" + temperature + '\'' +
                ", temperatureValue='" + temperatureValue + '\'' +
                ", co2='" + co2 + '\'' +
                ", co2Value='" + co2Value + '\'' +
                ", pm2d5='" + pm2d5 + '\'' +
                ", pm2d5Value='" + pm2d5Value + '\'' +
                ", noise='" + noise + '\'' +
                ", noiseValue='" + noiseValue + '\'' +
                ", smoke='" + smoke + '\'' +
                ", smokeValue='" + smokeValue + '\'' +
                '}';
    }
}
