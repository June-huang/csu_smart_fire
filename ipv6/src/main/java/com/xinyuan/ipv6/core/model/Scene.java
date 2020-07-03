package com.xinyuan.ipv6.core.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 场景
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
@TableName("IP_SCENE")
public class Scene extends Model<Scene> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
	private String id;
    /**
     * 是否正在运行
     */
	@TableField("ACTIVE")
	private Boolean active;
    /**
     * 场景名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 教室号
     */
	@TableField("CLASSROOM_NUM")
	private String classroomNum;

//	/**
//	 * 场景的触发类型（1：设备触发。2：定时触发。3：手动触发）
//	 */
//	@TableField("TRIGGER_TYPE")
//	private Integer triggerType;
//	/**
//	 * 定时任务的ID
//	 */
//	private String jobId;
//	/**
//	 *
//	 */
//	private String triggerConditionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassroomNum() {
		return classroomNum;
	}

	public void setClassroomNum(String classroomNum) {
		this.classroomNum = classroomNum;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Scene{" +
			", id=" + id +
			", active=" + active +
			", name=" + name +
			", classroomNum=" + classroomNum +
			"}";
	}
}
