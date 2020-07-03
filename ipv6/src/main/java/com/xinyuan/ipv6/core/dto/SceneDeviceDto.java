package com.xinyuan.ipv6.core.dto;

import java.io.Serializable;

/**
 * <p>
 * 一个场景里的设备及对应的操作
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
public class SceneDeviceDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private String sdId;
    /**
     * 设备的id(）
     */
    private String id;
    /**
     * IP
     */
    private String ip;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 名称
     */
    private String name;

    /**
     * 教室号
     */
    private String classroomNum;

    /**
     * 设备的类型
     */
    private Integer deviceType;
    /**
     * 状态值
     */
    private Object value;
    /**
     * 场景的ID
     */
    private String sceneId;

    private Object info;

    public String getSdId() {
        return sdId;
    }

    public void setSdId(String sdId) {
        this.sdId = sdId;
    }

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

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getClassroomNum() {
        return classroomNum;
    }

    public void setClassroomNum(String classroomNum) {
        this.classroomNum = classroomNum;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SceneDevice{" +
                "sdId='" + sdId + '\'' +
                ", id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", name='" + name + '\'' +
                ", classroomNum='" + classroomNum + '\'' +
                ", deviceType=" + deviceType +
                ", value=" + value +
                ", sceneId='" + sceneId + '\'' +
                ", info=" + info +
                "} " + super.toString();
    }
}
