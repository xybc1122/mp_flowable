package com.mq.demo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mq.demo.sys.entity.Tenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 陈恩惠
 * @since 2019-06-10
 */
public interface TenantMapper extends BaseMapper<Tenant> {


    List<Tenant> sleAllTenant(@Param(Constants.WRAPPER) Wrapper<Tenant> wrapper);

}
