package com.xinyuan.ipv6.web.controller;

import com.xinyuan.common.constant.ApiResult;
import com.xinyuan.ipv6.core.service.Ipv6DeviceTypeService;
import com.xinyuan.ipv6.web.util.Ipv6DeviceUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by pen on 2018/3/16.
 */
@RestController
@Api(value = "ip v6设备类型管理", tags = "ip v6设备类型管理")
public class Ipv6DeviceTypeController {

    @Resource
    private Ipv6DeviceTypeService ipv6DeviceTypeService;

    @ApiOperation("获取所有ipv6设备类型")
    @GetMapping(Ipv6DeviceUrl.GET_DEVICE_TYPE)
    public ApiResult list() {
        return ApiResult.success(ipv6DeviceTypeService.selectList());
    }
}
