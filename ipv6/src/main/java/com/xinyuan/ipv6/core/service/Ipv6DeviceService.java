package com.xinyuan.ipv6.core.service;

import com.xinyuan.core.base.BaseService;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-14
 */
public interface Ipv6DeviceService extends BaseService<Ipv6Device> {

    List<Ipv6Device> listInfoByClassroom(String classroomNum);

    /**
     * 获取一个设备的信息
     * @param ipv6Device
     * @return
     */
    Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device);

    /**
     * 获取所有的中控面板
     *
     * @return
     */
    List<Ipv6Device> listControlPanel();

    /**
     * 获取一间教室的计量插座
     *
     * @param classroomNum
     * @return
     */
    List<Ipv6Device> listPlugByClassroom(String classroomNum);

    /**
     * 操作一个设备
     *
     * @param ipv6DeviceDto
     */
    boolean operateDevice(Ipv6DeviceDto ipv6DeviceDto);

    /**
     * 获取一间教室的IPV6设备
     *
     * @param classroomNum
     * @return
     */
    List<Ipv6Device> queryByClassroom(String classroomNum);
}
