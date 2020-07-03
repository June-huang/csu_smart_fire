package com.xinyuan.ipv6.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.xinyuan.common.constant.ApiResult;
import com.xinyuan.common.util.BeanUtils;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.ipv6.core.dto.SceneDeviceDto;
import com.xinyuan.ipv6.core.dto.SceneDto;
import com.xinyuan.ipv6.core.model.Scene;
import com.xinyuan.ipv6.core.model.SceneDevice;
import com.xinyuan.ipv6.core.service.SceneDeviceService;
import com.xinyuan.ipv6.web.util.Ipv6DeviceUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 一个场景里的设备及对应的操作 前端控制器
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
@RestController
@Api(value = "场景设备管理", tags = "场景设备管理")
public class SceneDeviceController {
    @Resource
    private SceneDeviceService sceneService;

    @ApiOperation("获取一间教室的可以操作的设备")
    @GetMapping(Ipv6DeviceUrl.GET_OPERA_DEVICE)
    public ApiResult getOperaDevice(@PathVariable String classroomNum) {
        return ApiResult.success(sceneService.getOperaDeviceByClassroom(classroomNum));
    }

    @ApiOperation("获取一间教室作为触发条件的设备")
    @GetMapping(Ipv6DeviceUrl.GET_TRIGGER_DEVICE)
    public ApiResult getTriggerDevice(@PathVariable String classroomNum) {
        return ApiResult.success(sceneService.getTriggerDeviceByClassroom(classroomNum));
    }

    @ApiOperation("获取已经加入到某个场景里的设备")
    @GetMapping(Ipv6DeviceUrl.GET_SCENE_DEVICE)
    public ApiResult getOperaDeviceByScene(@PathVariable String scenesId) {
        return ApiResult.success(sceneService.getBySceneId(scenesId));
    }

    //    @ApiOperation("获取一间教室的所有场景")
//    @GetMapping(Ipv6DeviceUrl.GET_SCENE)
//    public ApiResult getDeviceById(@PathVariable String classroomNum) {
//    }
//
    @ApiOperation("添加多个场景设备(会删除原来的)")
    @PostMapping(Ipv6DeviceUrl.ADD_SCENE_DEVICE)
    @Transactional
    public ApiResult add(@PathVariable String scenesId, @RequestBody List<SceneDeviceDto> sceneDeviceDtos) {
        sceneService.deleteBySceneId(scenesId);
        if (CollectionUtils.isNotEmpty(sceneDeviceDtos)) {
            List<SceneDevice> sceneDevices = dtoToDevice(sceneDeviceDtos);
            sceneService.insertBatch(sceneDevices);
        }
        return ApiResult.success("添加成功");
    }

    private List<SceneDevice> dtoToDevice(List<SceneDeviceDto> sceneDeviceDtos) {
        List<SceneDevice> sceneDevices = new ArrayList<>(sceneDeviceDtos.size());
        for (SceneDeviceDto sceneDeviceDto : sceneDeviceDtos) {
            SceneDevice sceneDevice = BeanUtils.map(sceneDeviceDto, SceneDevice.class);
            //设置ip和port为中控的ip
            //sceneDevice.setIp(ip);
            //sceneDevice.setPort(port);
            
            JSONObject info = (JSONObject) sceneDeviceDto.getInfo();
            if (info != null) {
                sceneDevice.setInfo(info.toJSONString());
            }
            sceneDevices.add(sceneDevice);
        }
        return sceneDevices;
    }
//
//    @ApiOperation("修改一个场景")
//    @PutMapping(Ipv6DeviceUrl.UPDATE_SCENE)
//    public ApiResult update(@RequestBody SceneDto sceneDto) {
//    }
//
//    @ApiOperation("删除一个场景")
//    @DeleteMapping(Ipv6DeviceUrl.DELETE_SCENE)
//    public ApiResult delete(@PathVariable String id) {
//    }
}
