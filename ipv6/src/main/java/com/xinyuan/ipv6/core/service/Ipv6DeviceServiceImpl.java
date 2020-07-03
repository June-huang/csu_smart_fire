package com.xinyuan.ipv6.core.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xinyuan.common.ui.BootstrapTable;
import com.xinyuan.common.ui.PageRequest;
import com.xinyuan.common.util.CollectionUtils;
import com.xinyuan.core.base.BaseServiceImpl;
import com.xinyuan.ipv6.core.dto.Ipv6DeviceDto;
import com.xinyuan.ipv6.core.mapper.Ipv6DeviceMapper;
import com.xinyuan.ipv6.core.model.Ipv6Device;
import com.xinyuan.ipv6.core.model.Ipv6DeviceType;
import com.xinyuan.ipv6.core.model.ipv6Data.Ipv6DeviceInfo;
import com.xinyuan.ipv6.core.service.deviceService.BaseIpv6Service;
import com.xinyuan.ipv6.core.service.deviceService.BaseSwicthService;
import com.xinyuan.ipv6.core.util.BaseServiceSwitchUtil;
import com.xinyuan.ipv6.core.util.Ipv6CacheUtil;
import com.xinyuan.system.core.model.smartclass.DeviceTitle;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-14
 */
@Service
public class Ipv6DeviceServiceImpl extends BaseServiceImpl<Ipv6DeviceMapper, Ipv6Device> implements Ipv6DeviceService {
    private final static Logger logger = LoggerFactory.getLogger("ipv6Logger");

    @Resource
    private Ipv6DeviceMapper ipv6DeviceMapper;
    @Resource
    private BaseServiceSwitchUtil baseServiceSwitchUtil;
    @Resource
    private Ipv6DeviceTypeService ipv6DeviceTypeService;


    @Override
    public BootstrapTable getBootstrapTable(PageRequest pageRequest) {
        Page<Ipv6Device> page = new Page<>(pageRequest.getCurrent(), pageRequest.getLimit());
        
        Map<String, Object> conditionMap = pageRequest.getCondition();
        Condition condition = Condition.create();
        String CLASSROOM_NUM = null;
        if (conditionMap != null && !conditionMap.isEmpty()) {
        	JSONObject json =new JSONObject(conditionMap);
        	String nodeType = json.getString("nodeType");
        	if("6".equals(nodeType)){//校区
        		
        	}else if("11".equals(nodeType)){//楼栋
        		
        	}else if("12".equals(nodeType)){//教室
        		condition.eq("CLASSROOM_NUM", json.getString("id"));
        		CLASSROOM_NUM = json.getString("id");
        	}
		}
        BootstrapTable bootstrapTable = BootstrapTable.instance();
        bootstrapTable.setRows(ipv6DeviceMapper.queryPage2(page, CLASSROOM_NUM));
        bootstrapTable.setTotal(page.getTotal());
        bootstrapTable.setRecords(page.getSize());
        return bootstrapTable;
    }
    
    public BootstrapTable getBootstrapTable_back(PageRequest pageRequest) {
    	Page<Ipv6Device> page = new Page<>(pageRequest.getCurrent(), pageRequest.getLimit());
        BootstrapTable bootstrapTable = BootstrapTable.instance();
        bootstrapTable.setRows(ipv6DeviceMapper.queryPage(page, pageRequest.getCondition()));
        bootstrapTable.setTotal(page.getTotal());
        bootstrapTable.setRecords(page.getSize());
        return bootstrapTable;
    }

    @Override
    public boolean insert(Ipv6Device entity) {
        List<DeviceTitle> deviceTitles = entity.getDeviceTitles();
        if (CollectionUtils.isNotEmpty(deviceTitles)) {
            entity.setDeviceTitleId(listToString(deviceTitles));
        }
        entity.setDeviceType(entity.getIpv6DeviceType().getCode());
        entity.setDeviceTypeId(entity.getIpv6DeviceType().getId());
        return super.insert(entity);
    }

    @Override
    public boolean updateById(Ipv6Device entity) {
        List<DeviceTitle> deviceTitles = entity.getDeviceTitles();
        if (CollectionUtils.isNotEmpty(deviceTitles)) {
            entity.setDeviceTitleId(listToString(deviceTitles));
        }
        entity.setDeviceType(entity.getIpv6DeviceType().getCode());
        entity.setDeviceTypeId(entity.getIpv6DeviceType().getId());
        return super.updateById(entity);
    }


    /**
     * 1
     * 根据教室号，获取所有设备,并加入设备状态
     */
    @Override
    public List<Ipv6Device> listInfoByClassroom(String classroomNum) {
    	//根据教室号，查找一间教室的所有IPV6设备
    	Ipv6Device ipv6Device = new Ipv6Device();
        ipv6Device.setClassroomNum(classroomNum);
        List<Ipv6Device> ipv6DeviceList = selectList(new EntityWrapper<>(ipv6Device));
        
        for (Ipv6Device device : ipv6DeviceList) {
        	//设置ipv6的状态，开关状态Buttons
            device.setInfo(getInfo(device));
        }
        return ipv6DeviceList;
    }
    
    /**
     * 2
     * 从设备中，获取一个设备的状态(去除缓存)
     * @param ipv6Device
     * @return
     */
    @Override
    public Ipv6DeviceInfo getInfo(Ipv6Device ipv6Device) {
        //先从缓存里拿数据
        Ipv6DeviceInfo infoOld = Ipv6CacheUtil.get(ipv6Device.getId());
        //判断设备类型是否为空
        Integer deviceType = ipv6Device.getDeviceType();
        if (deviceType == null) {
            return null;
        }
        
        //根据设备类型,返回一个类型ip_device_type  类型名，一次数据（缓存时间）
        Ipv6DeviceType ipv6DeviceType = ipv6DeviceTypeService.queryByCode(deviceType);
        //如果缓存里有数据，并且距离上次更新的时间没有超出指定的时间
        if (infoOld != null && !isTimeOut(infoOld.getUpdateTime(), ipv6DeviceType.getIntervalTime())) {
            return infoOld;
        }

        //通过不同的设备选择不同的service
        BaseIpv6Service baseIpv6Service = baseServiceSwitchUtil.getBaseIpv6Service(deviceType);
        if (baseIpv6Service == null) {
            return null;
        }
        Ipv6DeviceInfo infoNew = baseIpv6Service.getInfo(ipv6Device);
        //如果没连接上，没拿到数据
        if (!infoNew.getConnectState()) {
            //如果缓存里没有，就用设备返回来的；如果缓存有，就用缓存里的，修改连接状态
        	infoOld = infoOld == null ? infoNew : infoOld;
        	infoOld.setConnectState(false);
        } else {
        	infoOld = infoNew;
        }
        infoOld.setUpdateTime(System.currentTimeMillis());
        Ipv6CacheUtil.set(ipv6Device.getId(), infoOld);
        return infoOld;
    }
    
    /**
     * 查找一间教室的所有IPV6设备
     * @param classroomNum 教室号
     * @return
     */
    @Override
    public List<Ipv6Device> queryByClassroom(String classroomNum) {
        Ipv6Device ipv6Device = new Ipv6Device();
        ipv6Device.setClassroomNum(classroomNum);
        return selectList(new EntityWrapper<>(ipv6Device));
    }

    /**
     * 操作一个设备
     * @param ipv6DeviceDto
     */
    @Override
    public boolean operateDevice(Ipv6DeviceDto ipv6DeviceDto) {
        BaseSwicthService baseSwicthService = baseServiceSwitchUtil.getBaseSwicthService(ipv6DeviceDto.getDeviceType());
        boolean result = false;
        if (baseSwicthService != null) {
            result = baseSwicthService.operate(ipv6DeviceDto);
            if (result) {
                baseSwicthService.updateInfo(ipv6DeviceDto.getId());
            }
        }
        return result;
    }

    /**
     * 是否超时
     * @param lastTime 上次时间
     * @param seconds  时间间隔（秒）
     * @return
     */
    private boolean isTimeOut(Long lastTime, int seconds) {
        if (lastTime == null) {
            return true;
        }
        return System.currentTimeMillis() - lastTime > seconds * 1000;
    }

    @Override
    public List<Ipv6Device> listControlPanel() {
        Ipv6Device ipv6Device = new Ipv6Device();
        ipv6Device.setDeviceType(614);
        return selectList(new EntityWrapper<>(ipv6Device));
    }

    @Override
    public List<Ipv6Device> listPlugByClassroom(String classroomNum) {
        Ipv6Device ipv6Device = new Ipv6Device();
        ipv6Device.setClassroomNum(classroomNum);
        ipv6Device.setDeviceType(605);
        return selectList(new EntityWrapper<>(ipv6Device));
    }


    /**
     * 将列表中的ID取出来。组成逗号隔开的字符串
     *
     * @param deviceTitles
     * @return
     */
    private String listToString(List<DeviceTitle> deviceTitles) {
        if (deviceTitles.size() == 1) {
            return deviceTitles.get(0).getId();
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < deviceTitles.size(); i++) {
                DeviceTitle deviceTitle = deviceTitles.get(i);
                if (i > 0) {
                    stringBuffer.append(",");
                }
                stringBuffer.append(deviceTitle.getId());
            }
            return stringBuffer.toString();
        }
    }
}
