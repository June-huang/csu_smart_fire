package com.xinyuan.ipv6.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.system.core.model.smartclass.DeviceControllerKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-14
 */
public interface Ipv6DeviceMapper extends BaseMapper<Ipv6Device> {

    /**
     * 分页查找IPV6设备
     *
     * @param page
     * @param map
     * @return
     */
    List<Ipv6Device> queryPage(Page page, Map<String, Object> map);
    
    /**
     * 分页查找IPV6设备
     *
     * @param page
     * @param map
     * @return
     */
    List<Ipv6Device> queryPage2(Page page, @Param("CLASSROOM_NUM") String CLASSROOM_NUM);

    /**
     * 通过设备ID查找此设备的能耗设备
     *
     * @param deviceId
     * @return
     */
    Ipv6Device queryEnergyDeviceByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 通过ipv6设备的IP查找对应的控制命令（限GC设备）
     * IP是唯一的，所以可以当ID用
     *
     * @param deviceIp
     * @param type     获取什么命令（0是关机命令，1是开机命令,99是其他命令）
     * @return
     */
    List<DeviceControllerKey> queryKeyByDeviceIp(@Param("deviceIp") String deviceIp, @Param("type") int type);
}