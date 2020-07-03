package com.xinyuan.ipv6.core.mapper;

import com.xinyuan.ipv6.core.model.ControlPanelCmd;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 中控面板返回的命令（用于操作指定场景） Mapper 接口
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-27
 */
public interface ControlPanelCmdMapper extends BaseMapper<ControlPanelCmd> {

    /**
     * 通过中控面板的ID和命令查找对应的场景的ID
     *
     * @param deviceId 中控面板的ID
     * @param code     返回的代码
     * @return
     */
    String querySceneIdByDeviceIdAndCode(@Param("deviceId") String deviceId, @Param("code") String code);

	String querySceneIdByIpAndCode(@Param("ip") String iP, @Param("code") String code);
}