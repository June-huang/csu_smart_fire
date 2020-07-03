package com.xinyuan.ipv6.core.service;

import com.xinyuan.ipv6.core.model.Scene;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 场景 服务类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
public interface SceneService extends IService<Scene> {

    /**
     * 通过教室号获取一个教室的所有场景
     *
     * @param classroomNum
     * @return
     */
    List<Scene> listByClassroom(String classroomNum);


    /**
     * 运行场景
     *
     * @param id
     * @return
     */
    boolean runScene(String id);
}
