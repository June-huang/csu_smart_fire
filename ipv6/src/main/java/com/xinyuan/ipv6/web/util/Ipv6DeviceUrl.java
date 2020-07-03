package com.xinyuan.ipv6.web.util;

/**
 * Created by pen on 2018/3/15.
 */
public interface Ipv6DeviceUrl {
    /***分页获取ipv6设备**/
    String GET_IPV6DEVICE_PAGE = "v1/ipv6/devices/page";
    /***分页获取中控面板**/
    String GET_PANEL_PAGE = "v1/ipv6/devices/panel/page";
    /***获取一个教室的所有计量插座**/
    String GET_PLUG = "v1/ipv6/classrooms/{classroomNum}/devices/plug";
    /***添加ipv6设备 **/
    String ADD_DEVICE = "v1/ipv6/devices";

    String UPDATE_DEVICE = "v1/ipv6/devices/{id}";

    String DELETE_DEVICE = "v1/ipv6/devices/{id}";

    //////////////////////////////场景//////////////////////////////////////
    /***获取一间教室的所有场景**/
    String GET_SCENE = "v1/classrooms/{classroomNum}/scenes";
    /***添加一个场景**/
    String ADD_SCENE = "v1/scenes";
    /***.更新一个场景**/
    String UPDATE_SCENE = "v1/scenes/{id}";
    /***.删除一个场景**/
    String DELETE_SCENE = "v1/scenes/{id}";
    /***运行一个场景**/
    String RUN_SCENE = "v1/scenes/runScene/{id}";

    /***场景设备**/
    String GET_OPERA_DEVICE = "v1/classrooms/{classroomNum}/opera/devices";
    String GET_TRIGGER_DEVICE = "v1/classrooms/{classroomNum}/trigger/devices";
    String GET_SCENE_DEVICE = "v1/scenes/{scenesId}/devices";
    String ADD_SCENE_DEVICE = "v1/scenes/{scenesId}/devices";


    String GET_DEVICE_TYPE = "v1/ipv6/devices/type";

    String ADD_CONTROLPANEL_CMD = "v1/panel/cmd";
    String EDIT_CONTROLPANEL_CMD = "v1/panel/cmd/{id}";
    String DELETE_CONTROLPANEL_CMD = "v1/panel/cmd/{id}";
    String GET_CONTROLPANEL_CMD = "v1/panel/cmd/{deviceId}";

}
