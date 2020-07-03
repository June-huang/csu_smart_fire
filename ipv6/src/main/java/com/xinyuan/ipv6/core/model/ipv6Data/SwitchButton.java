package com.xinyuan.ipv6.core.model.ipv6Data;

/**
 * Created by pen on 2018/3/16.
 */
public class SwitchButton {
    /**
     * 第几个开关
     */
    private Integer index;
    /**
     * 开关名称
     */
    private String name;
    /**
     * 开关状态
     */
    private Boolean onOff;

    /**
     * 按钮代码。0为关(输入)，1为开（输出），99为其他
     */
    private Integer type;

    /**
     * 命令
     */
    private String cmd;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnOff() {
        return onOff;
    }

    public void setOnOff(Boolean onOff) {
        this.onOff = onOff;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "SwitchButton{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", onOff=" + onOff +
                ", type=" + type +
                '}';
    }
}
