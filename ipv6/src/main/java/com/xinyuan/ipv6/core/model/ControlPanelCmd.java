package com.xinyuan.ipv6.core.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 中控面板返回的命令（用于操作指定场景）
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-27
 */
@TableName("ip_control_panel_cmd")
public class ControlPanelCmd extends Model<ControlPanelCmd> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
	private String id;
    /**
     * 按钮的名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 按钮对应的代码
     */
	@TableField("CODE")
	private String code;
    /**
     * 场景ID
     */
	@TableField("SCENE_ID")
	private String sceneId;
    /**
     * 中控面板ID
     */
	@TableField("DEVICE_ID")
	private String deviceId;


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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ControlPanelCmd{" +
			", id=" + id +
			", name=" + name +
			", code=" + code +
			", sceneId=" + sceneId +
			", deviceId=" + deviceId +
			"}";
	}
}
