package com.xinyuan.ipv6.core.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xinyuan.core.base.BaseServiceImpl;
import com.xinyuan.ipv6.core.mapper.Ipv6DeviceTypeMapper;
import com.xinyuan.ipv6.core.model.Ipv6DeviceType;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资产类型 服务实现类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-14
 */
@Service
public class Ipv6DeviceTypeServiceImpl extends BaseServiceImpl<Ipv6DeviceTypeMapper, Ipv6DeviceType> implements Ipv6DeviceTypeService {

    @Override
    public Ipv6DeviceType queryByCode(int code) {
        Ipv6DeviceType ipv6DeviceType = new Ipv6DeviceType();
        ipv6DeviceType.setCode(code);
        return selectOne(new EntityWrapper<>(ipv6DeviceType));
    }
}
