package com.mq.demo.sys.service.impl;

import com.mq.demo.sys.entity.Tenant;
import com.mq.demo.dao.TenantMapper;
import com.mq.demo.sys.service.ITenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈恩惠
 * @since 2019-06-10
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

}
