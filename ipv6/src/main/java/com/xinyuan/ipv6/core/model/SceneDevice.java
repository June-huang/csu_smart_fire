package com.xinyuan.ipv6.core.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 一个场景里的设备及对应的操作
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
@TableName("IP_SCENE_DEVICE")
public class SceneDevice extends Model<SceneDevice> {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @TableId("SD_ID")
    private String sdId;
    /**
     * 设备的id(）
     */
    @TableId("ID")
    private String id;
    /**
     * IP
     */
    //@TableField(exist = false)
    @TableId("IP")
    private String ip;
    /**
     * 端口
     */
    //@TableField(exist = false)
    @TableId("PORT")
    private Integer port;
    /**
     * 名称
     */
    //@TableField(exist = false)
    @TableId("NAME")
    private String name;

    /**
     * 教室号
     */
    //@TableField(exist = false)
    @TableId("CLASSROOM_NUM")
    private String classroomNum;

    /**
     * 设备的类型
     */
    //@TableField(exist = false)
    @TableId("DEVICE_TYPE")
    private Integer deviceType;
    /**
     * 状态值
     */
    @TableField("VALUE")
    private Object value;
    /**
     * 场景的ID
     */
    @TableField("SCENE_ID")
    private String sceneId;

    @TableField("INFO")
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
    protected Serializable pkVal() {
        return this.sdId;
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
