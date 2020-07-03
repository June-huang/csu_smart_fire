package com.xinyuan.ipv6.core.dto;

import java.io.Serializable;
import java.util.List;

import com.xinyuan.ipv6.core.model.Ipv6DeviceType;
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
public class Ipv6DeviceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;
    /**
     * IP
     */
    private String ip;

    /**
     * 物理地址
     */
    private String mac;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 名称
     */
    private String name;
    /**
     * 类别ID
     */
    private String deviceTypeId;
    /**
     * 开关数量
     */
    private Integer switchNum;
    /**
     * 开关名称
     */
    private String switchName;
    /**
     * 教室
     */
    private String classroomNum;

    /**
     * 表头ID（逗号隔开）
     */
    private String deviceTitleId;

    /**
     * 能耗设备id，测功率的
     */
    private String energyDeviceId;
    /**
     * 连接状态
     */
    private Boolean connectState;

    /**
     * 设备类型
     */
    private Ipv6DeviceType ipv6DeviceType;

    private Ipv6DeviceInfo info;

    /**
     * 设备类型
     */
    private Integer deviceType;
    
    public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
     * 设备型号ID
     */
    private String modelId;

    /**
     * 一个设备内的第几个设备（比如一个设备有多个开关）
     */
    private Integer index;

    /**
     * 数据
     */
    private Object value;


    /**
     * 表头（一个设备可能有多个表头）
     */
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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

    public Boolean getConnectState() {
        return connectState;
    }

    public void setConnectState(Boolean connectState) {
        this.connectState = connectState;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
	public String toString() {
		return "Ipv6DeviceDto [id=" + id + ", ip=" + ip + ", mac=" + mac + ", port=" + port + ", name=" + name
				+ ", deviceTypeId=" + deviceTypeId + ", switchNum=" + switchNum + ", switchName=" + switchName
				+ ", classroomNum=" + classroomNum + ", deviceTitleId=" + deviceTitleId + ", energyDeviceId="
				+ energyDeviceId + ", connectState=" + connectState + ", ipv6DeviceType=" + ipv6DeviceType + ", info="
				+ info + ", deviceType=" + deviceType + ", modelId=" + modelId + ", index=" + index + ", value=" + value
				+ ", deviceTitles=" + deviceTitles + "]";
	}
}
