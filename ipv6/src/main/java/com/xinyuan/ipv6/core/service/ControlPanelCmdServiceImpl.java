package com.xinyuan.ipv6.core.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xinyuan.ipv6.core.model.ControlPanelCmd;
import com.xinyuan.ipv6.core.mapper.ControlPanelCmdMapper;
import com.xinyuan.ipv6.core.service.ControlPanelCmdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 中控面板返回的命令（用于操作指定场景） 服务实现类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-27
 */
@Service
public class ControlPanelCmdServiceImpl extends ServiceImpl<ControlPanelCmdMapper, ControlPanelCmd> implements ControlPanelCmdService {

    @Override
    public List<ControlPanelCmd> listByDeviceId(String deviceId) {
        ControlPanelCmd controlPanelCmd = new ControlPanelCmd();
        controlPanelCmd.setDeviceId(deviceId);
        return selectList(new EntityWrapper<>(controlPanelCmd));
    }
}
