package com.xinyuan.ipv6.core.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class SceneDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private String id;
    /**
     * 是否正在运行
     */
    private Boolean active;
    /**
     * 场景名称
     */
    private String name;
    /**
     * 教室号
     */
    private String classroomNum;


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
    public String toString() {
        return "Scene{" +
                ", id=" + id +
                ", active=" + active +
                ", name=" + name +
                ", classroomNum=" + classroomNum +
                "}";
    }
}
