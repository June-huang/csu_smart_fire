package com.xinyuan.ipv6.web.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xinyuan.common.constant.ApiResult;
import com.xinyuan.common.util.BeanUtils;
import com.xinyuan.ipv6.core.dto.SceneDto;
import com.xinyuan.ipv6.core.model.Scene;
import com.xinyuan.ipv6.core.service.SceneService;
import com.xinyuan.ipv6.web.util.Ipv6DeviceUrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 场景 前端控制器
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-15
 */
@RestController
@Api(value = "场景管理", tags = "场景管理")
public class SceneController {

    @Resource
    private SceneService sceneService;

    @ApiOperation("获取一间教室的所有场景")
    @GetMapping(Ipv6DeviceUrl.GET_SCENE)
    public ApiResult getByClassroom(@PathVariable String classroomNum) {
        return ApiResult.success(sceneService.listByClassroom(classroomNum));
    }

    @ApiOperation("添加一个场景")
    @PostMapping(Ipv6DeviceUrl.ADD_SCENE)
    public ApiResult add(@RequestBody SceneDto sceneDto) {
        Scene scene = BeanUtils.map(sceneDto, Scene.class);
        scene.setId("scene"+new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
        sceneService.insert(scene);
        return ApiResult.success(scene);
    }

    @ApiOperation("修改一个场景")
    @PutMapping(Ipv6DeviceUrl.UPDATE_SCENE)
    public ApiResult update(@RequestBody SceneDto sceneDto) {
        Scene scene = BeanUtils.map(sceneDto, Scene.class);
        sceneService.updateById(scene);
        return ApiResult.success(scene);
    }

    @ApiOperation("删除一个场景")
    @DeleteMapping(Ipv6DeviceUrl.DELETE_SCENE)
    public ApiResult delete(@PathVariable String id) {
        sceneService.deleteById(id);
        //删除场景关联的设备和中控命令
        return ApiResult.success("删除成功");
    }

    @ApiOperation("运行一个场景")
    @PutMapping(Ipv6DeviceUrl.RUN_SCENE)
    public ApiResult run(@PathVariable String id) {
        sceneService.runScene(id);
        return ApiResult.success("运行成功");
    }
}
