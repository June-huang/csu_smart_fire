package com.xinyuan.ipv6.core.service;

import com.xinyuan.ipv6.core.model.ControlPanelCmd;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 中控面板返回的命令（用于操作指定场景） 服务类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-27
 */
public interface ControlPanelCmdService extends IService<ControlPanelCmd> {

    List<ControlPanelCmd> listByDeviceId(String deviceId);

}
