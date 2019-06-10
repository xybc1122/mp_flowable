package com.mq.demo.sys.service.impl;

import com.mq.demo.sys.entity.TenantUser;
import com.mq.demo.dao.TenantUserMapper;
import com.mq.demo.sys.service.ITenantUserService;
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
public class TenantUserServiceImpl extends ServiceImpl<TenantUserMapper, TenantUser> implements ITenantUserService {

}
