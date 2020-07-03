package com.xinyuan.ipv6.core.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xinyuan.common.enums.DeviceTypeEnum;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.gateway.http.data.DeviceData;
import com.xinyuan.ipv6.core.mapper.SceneDeviceMapper;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 一个场景里的设备及对应的操作 服务实现类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
@Service
public class SceneDeviceServiceImpl extends ServiceImpl<SceneDeviceMapper, SceneDevice> implements SceneDeviceService {

    @Resource
    private SceneDeviceMapper sceneDeviceMapper;
    @Resource
    private Ipv6DeviceService ipv6DeviceService;

    /**
     * 通过教室获取可以操作的设备
     *
     * @param classroomNum
     * @return
     */
    @Override
    public List<SceneDevice> getOperaDeviceByClassroom(String classroomNum) {
        List<Ipv6Device> ipv6Devices = ipv6DeviceService.listInfoByClassroom(classroomNum);

        List<SceneDevice> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(ipv6Devices)) {
            return list;
        }
        for (Ipv6Device ipv6Device : ipv6Devices) {
            int deviceType = ipv6Device.getDeviceType();
            DeviceTypeEnum type = DeviceTypeEnum.getDeviceTypeEnum(deviceType);
            switch (type) {
                case SWITCH:
                case WINDOW_MOTOR:
                case PROJECTOR:
                case TV:
                case THERMOSTAT:
                case RELAY:
                case COMPUTER:
                    list.add(createDevcie(ipv6Device));
                default:
                    break;
            }
        }
        return list;
    }

    /**
     * 通过教室获取作为触发条件的设备
     *
     * @param classroomNum
     * @return
     */
    @Override
    public List<SceneDevice> getTriggerDeviceByClassroom(String classroomNum) {
        List<Ipv6Device> ipv6Devices = ipv6DeviceService.listInfoByClassroom(classroomNum);
        List<SceneDevice> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(ipv6Devices)) {
            return list;
        }
        for (Ipv6Device ipv6Device : ipv6Devices) {
            int deviceType = ipv6Device.getDeviceType();
            DeviceTypeEnum type = DeviceTypeEnum.getDeviceTypeEnum(deviceType);
            switch (type) {
                case TEMP_HUMI:
                case CO2:
                case LIGHT_LUM:
                case NOISE:
                case PM2D5:
                    list.add(createDevcie(ipv6Device));
                default:
                    break;
            }
        }
        return list;
    }

    /**
     * 删除一个场景下的所有设备
     *
     * @param sceneId
     * @return
     */
    @Transactional
    @Override
    public boolean deleteBySceneId(String sceneId) {
        SceneDevice sceneDevice = new SceneDevice();
        sceneDevice.setSceneId(sceneId);
        return delete(new EntityWrapper<>(sceneDevice));
    }

    @Override
    public List<SceneDevice> getBySceneId(String scenesId) {
        return sceneDeviceMapper.listSceneDevice(scenesId);
    }


    private SceneDevice createDevcie(Ipv6Device ipv6Device) {
        SceneDevice sceneDevice = new SceneDevice();
        sceneDevice.setName(ipv6Device.getName());
        sceneDevice.setDeviceType(ipv6Device.getDeviceType());
        sceneDevice.setIp(ipv6Device.getIp());
        sceneDevice.setPort(ipv6Device.getPort());
        sceneDevice.setId(ipv6Device.getId());
        sceneDevice.setInfo(ipv6Device.getInfo());
        return sceneDevice;
    }
}
