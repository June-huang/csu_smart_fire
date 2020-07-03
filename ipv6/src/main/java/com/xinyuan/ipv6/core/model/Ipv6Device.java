package com.xinyuan.ipv6.core.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.system.core.model.smartclass.DeviceTitle;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-14
 */
@TableName("IP_DEVICE")
public class Ipv6Device extends Model<Ipv6Device> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;
    /**
     * IP
     */
    @TableField("IP")
    private String ip;

    /**
     * 物理地址
     */
    @TableField("MAC")
    private String mac;
    /**
     * 端口
     */
    @TableField("PORT")
    private Integer port;
    /**
     * 名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 类别ID
     */
    @TableField("DEVICE_TYPE_ID")
    private String deviceTypeId;
    /**
     * 开关数量
     */
    @TableField("SWITCH_NUM")
    private Integer switchNum;
    /**
     * 开关名称
     */
    @TableField("SWITCH_NAME")
    private String switchName;
    /**
     * 教室
     */
    @TableField("CLASSROOM_NUM")
    private String classroomNum;

    /**
     * 表头ID（逗号隔开）
     */
    @TableField("DEVICE_TITLE_ID")
    private String deviceTitleId;

    /**
     * 设备型号ID
     */
    @TableField("MODEL_ID")
    private String modelId;

    /**
     * 能耗设备id，测功率的
     */
    @TableField("ENERGY_DEVICE_ID")
    private String energyDeviceId;
    /**
     * 设备类型
     */
    @TableField("DEVICE_TYPE")
    private Integer deviceType;

    /**
     * 设备类型
     */
    @TableField(exist = false)
    private Ipv6DeviceType ipv6DeviceType;

    @TableField(exist = false)
    private Ipv6DeviceInfo info;

    /**
     * 能耗设备
     */
    @TableField(exist = false)
    private Ipv6Device energyDevice;

    /**
     * 表头（一个设备可能有多个表头）
     */
    @TableField(exist = false)
    private List<DeviceTitle> deviceTitles;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Integer getSwitchNum() {
        return switchNum;
    }

    public void setSwitchNum(Integer switchNum) {
        this.switchNum = switchNum;
    }

    public String getSwitchName() {
        return switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

    public Ipv6DeviceType getIpv6DeviceType() {
        return ipv6DeviceType;
    }

    public void setIpv6DeviceType(Ipv6DeviceType ipv6DeviceType) {
        this.ipv6DeviceType = ipv6DeviceType;
    }

    public Ipv6DeviceInfo getInfo() {
        return info;
    }

    public void setInfo(Ipv6DeviceInfo info) {
        this.info = info;
    }

    public String getClassroomNum() {
        return classroomNum;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setClassroomNum(String classroomNum) {
        this.classroomNum = classroomNum;
    }

    public String getDeviceTitleId() {
        return deviceTitleId;
    }

    public void setDeviceTitleId(String deviceTitleId) {
        this.deviceTitleId = deviceTitleId;
    }

    public String getEnergyDeviceId() {
        return energyDeviceId;
    }

    public void setEnergyDeviceId(String energyDeviceId) {
        this.energyDeviceId = energyDeviceId;
    }

    public Ipv6Device getEnergyDevice() {
        return energyDevice;
    }

    public void setEnergyDevice(Ipv6Device energyDevice) {
        this.energyDevice = energyDevice;
    }

    public List<DeviceTitle> getDeviceTitles() {
        return deviceTitles;
    }

    public void setDeviceTitles(List<DeviceTitle> deviceTitles) {
        this.deviceTitles = deviceTitles;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Ipv6Device{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", name='" + name + '\'' +
                ", deviceTypeId='" + deviceTypeId + '\'' +
                ", switchNum=" + switchNum +
                ", switchName='" + switchName + '\'' +
                ", classroomNum='" + classroomNum + '\'' +
                ", deviceTitleId='" + deviceTitleId + '\'' +
                ", modelId='" + modelId + '\'' +
                ", energyDeviceId='" + energyDeviceId + '\'' +
                ", ipv6DeviceType=" + ipv6DeviceType +
                ", info=" + info +
                ", energyDevice=" + energyDevice +
                ", deviceTitles=" + deviceTitles +
                ", deviceType=" + deviceType +
                "} " + super.toString();
    }
}
