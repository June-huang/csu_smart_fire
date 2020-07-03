package com.xinyuan.ipv6.core.model.ipv6Data;

import java.math.BigDecimal;

/**
 * 能耗数据
 * Created by pen on 2018/3/16.
 */
public class Meter {
    /**
     * 功率
     */
    private BigDecimal power;
    /**
     * 用电量
     */
    private BigDecimal electricity;

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public BigDecimal getElectricity() {
        return electricity;
    }

    public void setElectricity(BigDecimal electricity) {
        this.electricity = electricity;
    }

    @Override
    public String toString() {
        return "Meter{" +
                "power='" + power + '\'' +
                ", electricity='" + electricity + '\'' +
                '}';
    }
}
