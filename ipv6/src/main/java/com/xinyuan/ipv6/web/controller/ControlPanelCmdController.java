package com.xinyuan.ipv6.web.controller;


import com.xinyuan.common.constant.ApiResult;
import com.xinyuan.ipv6.core.model.ControlPanelCmd;
import com.xinyuan.ipv6.core.service.ControlPanelCmdService;
import com.xinyuan.ipv6.web.util.Ipv6DeviceUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

/**
 * <p>
 * 中控面板返回的命令（用于操作指定场景） 前端控制器
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-27
 */
@RestController
@Api(value = "中控面板命令管理", tags = "中控面板命令管理")
public class ControlPanelCmdController {
    @Resource
    private ControlPanelCmdService controlPanelCmdService;

    @ApiOperation("添加")
    @PostMapping(Ipv6DeviceUrl.ADD_CONTROLPANEL_CMD)
    public ApiResult add(@RequestBody ControlPanelCmd controlPanelCmd) {
    	controlPanelCmd.setId("icpc" + new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
        controlPanelCmdService.insert(controlPanelCmd);
        return ApiResult.success("成功");
    }

    @ApiOperation("编辑")
    @PutMapping(Ipv6DeviceUrl.EDIT_CONTROLPANEL_CMD)
    public ApiResult update(@RequestBody ControlPanelCmd controlPanelCmd) {
        controlPanelCmdService.updateById(controlPanelCmd);
        return ApiResult.success("成功");
    }

    @ApiOperation("删除")
    @DeleteMapping(Ipv6DeviceUrl.DELETE_CONTROLPANEL_CMD)
    public ApiResult delete(@PathVariable String id) {
        controlPanelCmdService.deleteById(id);
        return ApiResult.success("成功");
    }

    @ApiOperation("根据中控面板的ID来获取")
    @GetMapping(Ipv6DeviceUrl.GET_CONTROLPANEL_CMD)
    public ApiResult getByDeviceId(@PathVariable String deviceId) {
        return ApiResult.success(controlPanelCmdService.listByDeviceId(deviceId));
    }
}
