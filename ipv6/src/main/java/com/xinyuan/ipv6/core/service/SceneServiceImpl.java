package com.xinyuan.ipv6.core.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.ipv6.core.mapper.SceneDeviceMapper;
import com.xinyuan.ipv6.core.mapper.SceneMapper;
import com.xinyuan.ipv6.core.model.Scene;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.service.deviceService.BaseSwicthService;
import com.xinyuan.ipv6.core.util.BaseServiceSwitchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 场景 服务实现类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {
    Logger logger = LoggerFactory.getLogger("ipv6Logger");
    @Resource
    private SceneDeviceMapper sceneDeviceMapper;
    @Resource
    private BaseServiceSwitchUtil baseServiceSwitchUtil;

    @Override
    public List<Scene> listByClassroom(String classroomNum) {
        Scene scene = new Scene();
        scene.setClassroomNum(classroomNum);
        return selectList(new EntityWrapper<>(scene));
    }

    /**
     * 3.根据场景id找到场景设备，执行设备
     */
    @Override
    public boolean runScene(String id) {
        logger.debug("执行场景：" + id);
        List<SceneDevice> sceneDevices = sceneDeviceMapper.listSceneDevice(id);
        if (CollectionUtils.isEmpty(sceneDevices)) {
            return true;
        }
        for (SceneDevice sceneDevice : sceneDevices) {
            runOne(sceneDevice);
        }
        return true;
    }

    /**
     * 执行一个设备的操作
     * @param sceneDevice
     * @return
     */
    private boolean runOne(SceneDevice sceneDevice) {
        int deviceType = sceneDevice.getDeviceType();
        BaseSwicthService baseSwicthService = baseServiceSwitchUtil.getBaseSwicthService(deviceType);
        if (baseSwicthService != null) {
            if (baseSwicthService.runScene(sceneDevice)) {
            	//强制刷新数据
                //baseSwicthService.updateInfo(sceneDevice.getId());
                return true;
            }
            return false;
        }
        return false;
    }
}
