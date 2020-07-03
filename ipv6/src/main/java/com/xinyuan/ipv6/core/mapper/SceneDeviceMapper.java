package com.xinyuan.ipv6.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xinyuan.ipv6.core.model.SceneDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 一个场景里的设备及对应的操作 Mapper 接口
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
public interface SceneDeviceMapper extends BaseMapper<SceneDevice> {

    /**
     * 根据场景ID列出场景设备
     * @param sceneId
     * @return
     */
    List<SceneDevice> listSceneDevice(@Param("sceneId") String sceneId);
}