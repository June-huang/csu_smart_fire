package com.xinyuan.ipv6.core.service;

import com.xinyuan.core.base.BaseService;
import com.xinyuan.ipv6.core.model.Ipv6DeviceType;

/**
 * <p>
 * 资产类型 服务类
 * </p>
 *
 * @author 杨鹏
 * @since 2018-03-14
 */
public interface Ipv6DeviceTypeService extends BaseService<Ipv6DeviceType> {

    /**
     * 根据code返回一个类型
     * @param code
     * @return
     */
    Ipv6DeviceType queryByCode(int code);

}
