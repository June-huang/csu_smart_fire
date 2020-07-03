package com.xinyuan.ipv6.core.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 资产类型
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-14
 */
@TableName("ip_device_type")
public class Ipv6DeviceType extends Model<Ipv6DeviceType> {

    private static final long serialVersionUID = 1L;

    /**
     * 资产类别ID
     */
    @TableId("ID")
    private String id;
    /**
     * 资产类别名称
     */
    @TableField("NAME")
    private String name;
    /**
     */
    @TableField("CODE")
    private Integer code;

    /**
     * 两次更新的间隔时间
     */
    @TableField("INTERVAL_TIME")
    private Integer IntervalTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getIntervalTime() {
        return IntervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        IntervalTime = intervalTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Ipv6DeviceType{" +
                ", id=" + id +
                ", name=" + name +
                ", code=" + code +
                "}";
    }
}
