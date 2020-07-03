package com.xinyuan.ipv6.core.util;

import com.xinyuan.common.enums.DeviceTypeEnum;
import com.xinyuan.ipv6.core.service.deviceService.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 通过不同的设备选择不同的service
 * Created by pen on 2018/3/16.
 */
@Component
public class BaseServiceSwitchUtil {

    @Resource
    private Co2Service co2Service;
    @Resource
    private LightLumService lightLumService;
    @Resource
    private NoiseService noiseService;
    @Resource
    private PlugService plugService;
    @Resource
    private Pm2d5Service pm2d5Service;
    @Resource
    private SerialService serialService;
    @Resource
    private SwitchService switchService;
    @Resource
    private TempHumiService tempHumiService;
    @Resource
    private WindowMotorService windowMotorService;
    @Resource
    private ThermostatService thermostatService;
    @Resource
    private RelayService relayService;
    @Resource
    private ComputerService computerService;

    public BaseIpv6Service getBaseIpv6Service(int deviceType) {
        DeviceTypeEnum type = DeviceTypeEnum.getDeviceTypeEnum(deviceType);
        if (type == null) {
            return null;
        }
        BaseIpv6Service baseIpv6Service = null;
        switch (type) {
            case TEMP_HUMI:
                baseIpv6Service = tempHumiService;
                break;
            case CO2:
                baseIpv6Service = co2Service;
                break;
            case LIGHT_LUM:
                baseIpv6Service = lightLumService;
                break;
            case NOISE:
                baseIpv6Service = noiseService;
                break;
            case PLUG:
                baseIpv6Service = plugService;
                break;
            case PM2D5:
                baseIpv6Service = pm2d5Service;
                break;
            case SERIAL:
            case TV:
            case PROJECTOR:
            case MATRIX:
                baseIpv6Service = serialService;
                break;
            case SWITCH:
                baseIpv6Service = switchService;
                break;
            case WINDOW_MOTOR:
                baseIpv6Service = windowMotorService;
                break;
            case THERMOSTAT:
                baseIpv6Service = thermostatService;
                break;
            case RELAY:
                baseIpv6Service = relayService;
                break;
            case COMPUTER:
                baseIpv6Service = computerService;
                break;
            default:
                break;
        }
        return baseIpv6Service;
    }

    /**
     * 获取可操作设备的service
     * @param deviceType
     * @return
     */
    public BaseSwicthService getBaseSwicthService(int deviceType) {
        DeviceTypeEnum type = DeviceTypeEnum.getDeviceTypeEnum(deviceType);
        BaseSwicthService baseSwicthService = null;
        switch (type) {
            case SWITCH:
                baseSwicthService = switchService;
                break;
            case WINDOW_MOTOR:
                baseSwicthService = windowMotorService;
                break;
            case SERIAL:
            case TV:
            case PROJECTOR:
            case MATRIX:
                baseSwicthService = serialService;
                break;
            case THERMOSTAT:
                baseSwicthService = thermostatService;
                break;
            case RELAY:
                baseSwicthService = relayService;
                break;
            case COMPUTER:
                baseSwicthService = computerService;
                break;
            default:
                break;
        }
        return baseSwicthService;
    }
}
