package com.xinyuan.ipv6.core.service;

import com.xinyuan.ipv6.core.model.SceneDevice;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 一个场景里的设备及对应的操作 服务类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
public interface SceneDeviceService extends IService<SceneDevice> {

    /**
     * 通过教室获取可以操作的设备
     * @param classroomNum
     * @return
     */
    List<SceneDevice> getOperaDeviceByClassroom(String classroomNum);

    /**
     * 通过教室获取作为触发条件的设备
     * @param classroomNum
     * @return
     */
    List<SceneDevice> getTriggerDeviceByClassroom(String classroomNum);

    /**
     * 删除一个场景下的所有设备
     * @param sceneId
     * @return
     */
    @Transactional
    boolean deleteBySceneId(String sceneId);

    List<SceneDevice> getBySceneId(String scenesId);
}
