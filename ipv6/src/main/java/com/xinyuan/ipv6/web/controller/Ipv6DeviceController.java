package com.xinyuan.ipv6.web.controller;

import com.xinyuan.common.constant.ApiResult;
import com.xinyuan.common.ui.PageRequest;
import com.xinyuan.common.util.BeanUtils;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.service.Ipv6DeviceService;
import com.xinyuan.ipv6.web.util.Ipv6DeviceUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pen on 2018/3/15.
 */
@RestController
@Api(value = "IPv6设备管理", tags = "IPv6设备管理")
public class Ipv6DeviceController {

    @Resource
    private Ipv6DeviceService ipv6DeviceService;

    @PostMapping(Ipv6DeviceUrl.GET_IPV6DEVICE_PAGE)
    @ApiOperation("分页获取IPv6设备")
    public ApiResult getPage(@RequestBody PageRequest pageRequest) {
        return ApiResult.success(ipv6DeviceService.getBootstrapTable(pageRequest));
    }

    @GetMapping(Ipv6DeviceUrl.GET_PLUG)
    @ApiOperation("获取一间教室的计量插座")
    public ApiResult getPlug(@PathVariable String classroomNum) {
        return ApiResult.success(ipv6DeviceService.listPlugByClassroom(classroomNum));
    }

    @PostMapping(Ipv6DeviceUrl.GET_PANEL_PAGE)
    @ApiOperation("分页获取中控面板")
    public ApiResult getPanelPage(@RequestBody PageRequest pageRequest) {
        Map<String, Object> map = pageRequest.getCondition();
        if (map == null) {
            map = new HashMap<>(1);
        }
        map.put("deviceType", 614);
        pageRequest.setCondition(map);
        return ApiResult.success(ipv6DeviceService.getBootstrapTable(pageRequest));
    }

    @PostMapping(Ipv6DeviceUrl.ADD_DEVICE)
    @ApiOperation("添加一个IPv6设备")
    public ApiResult add(@RequestBody Ipv6DeviceDto ipv6DeviceDto) {
    	ipv6DeviceDto.setId("ipdevice"+new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
        ipv6DeviceService.insert(BeanUtils.map(ipv6DeviceDto, Ipv6Device.class));
        return ApiResult.success("添加成功");
    }

    @PutMapping(Ipv6DeviceUrl.UPDATE_DEVICE)
    @ApiOperation("更新一个IPv6设备")
    public ApiResult update(@RequestBody Ipv6DeviceDto ipv6DeviceDto) {
        ipv6DeviceService.updateById(BeanUtils.map(ipv6DeviceDto, Ipv6Device.class));
        return ApiResult.success("更新成功");

    }

    @DeleteMapping(Ipv6DeviceUrl.DELETE_DEVICE)
    @ApiOperation("删除一个IPv6设备")
    public ApiResult delete(@PathVariable String id) {
        ipv6DeviceService.deleteById(id);
        return ApiResult.success("删除成功");
    }
}
