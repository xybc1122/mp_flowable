
package com.mq.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mq.demo.dao.TenantMapper;
import com.mq.demo.dao.UserMapper;
import com.mq.demo.model.User;
import com.mq.demo.sys.dto.TenantDto;
import com.mq.demo.sys.entity.SystemUserUpload;
import com.mq.demo.sys.entity.Tenant;
import com.mq.demo.dao.TenantUserMapper;
import com.mq.demo.sys.entity.TenantUser;
import com.mq.demo.sys.service.ISystemUserUploadService;
import com.mq.demo.utils.ListUtils;
import com.mq.demo.utils.PageUtils;
import com.mq.demo.utils.WrapperUtils;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpTest {
    @Autowired
    private UserMapper uMapper;

    @Autowired
    private ISystemUserUploadService iSystemUserUploadService;


    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private TenantUserMapper tenantUserMapper;
    /**
     * dto 转换工具
     */
    @Autowired
    private MapperFacade mapperFacade;

    @Test
    public void myXml() {
        Page<Tenant> tenantPage = PageUtils.setPage(1, 5);
        QueryWrapper<Tenant> query = WrapperUtils.getQuery();
        //query.likeRight("name", "wang");
        //主表
        IPage<Tenant> tenantIPage = tenantMapper.selectPage(tenantPage, query);

        //System.out.println(ListUtils.listIsNull(tenantIPage.getRecords()));
        if (!ListUtils.listIsNull(tenantIPage.getRecords())) return;

        //转换dto层
        List<TenantDto> tenantDtoList = mapperFacade.mapAsList(tenantIPage.getRecords(), TenantDto.class);


        //获得主表ids
        List ids = ListUtils.selIds(tenantIPage.getRecords());

        //这里循环add 主表ID
        LambdaQueryWrapper<TenantUser> lambdaQuery = WrapperUtils.getLambdaQuery();
        lambdaQuery.in(TenantUser::getTid, ids);

        //获得链表信息
        List<TenantUser> tenantUsers = tenantUserMapper.sleAllTu(lambdaQuery);

        //这里循环set 值返回给前端 dto模型
        for (TenantDto dto : tenantDtoList) {
            //这里一对一设置
            for (TenantUser tu : tenantUsers) {
                if (dto.getTenantId().equals(tu.getTuId())) {
                    dto.settName(tu.getName());
                    break;
                }
            }
        }
        System.out.println(tenantDtoList);
    }

    /**
     * 插入（批量）
     *
     * @param
     */
    @Test
    public void saveBatch() {
        List<SystemUserUpload> c = new ArrayList<>();
        SystemUserUpload s = new SystemUserUpload();
        s.setUrl("a");
        SystemUserUpload b = new SystemUserUpload();
        b.setUrl("b");
        c.add(s);
        c.add(b);
        boolean b1 = iSystemUserUploadService.saveBatch(c);
        System.out.println(b1);
    }


    /**
     * 删除（批量）
     *
     * @param
     */
    @Test
    public void removeByIds() {
        List<Integer> c = new ArrayList<>();
        c.add(3708);
        c.add(3710);
        boolean b1 = iSystemUserUploadService.removeByIds(c);
        System.out.println(b1);
    }

    @Test
    public void mpDel() {
        int id = 1;
        int i = tenantMapper.deleteById(id);
    }


    @Test
    public void mpUp() {
        int id = 1;
        int version = 0;
        User u = new User();
        u.setUid(id);
        u.setVersion(version);
        u.setUserName("aa");
        int i = uMapper.updateById(u);
    }

    @Test
    public void mpSelectByIds() {
        List<Integer> uids = new ArrayList<>();
        uids.add(1);
        uids.add(4);
        List<User> users = uMapper.selectBatchIds(uids);
        System.out.println(users);
    }

    @Test
    public void mpSelectByWrapper1() {
        QueryWrapper<User> query = Wrappers.query();
        query.like("name", "a").le("age", "20");
        List<User> users = uMapper.selectList(query);
        System.out.println(users);

    }

    @Test
    public void mpSelectById() {
        User user = uMapper.selectById(3);
        System.out.println(user);
    }

    @Test
    public void mpSelectListSupper() {
        QueryWrapper<User> query = Wrappers.query();
        query.select("id").like("name", "a").le("age", "20");
        List<User> users = uMapper.selectList(query);
        System.out.println(users);
    }

    @Test
    public void mpSelectListSupper1() {
        QueryWrapper<User> query = Wrappers.query();
        query.select(User.class, p -> !p.getColumn().equals("time")).
                like("name", "a").le("age", "20");
        List<User> users = uMapper.selectList(query);
        System.out.println(users);
    }

    @Test
    public void mpSelectListSupper2() {
        QueryWrapper<User> query = Wrappers.query();
        query.select(User.class, p -> !p.getColumn().equals("time")).
                like("name", "a").le("age", "20");
        List<Map<String, Object>> maps = uMapper.selectMaps(query);
        System.out.println(maps);
    }

    @Test
    public void mpSelectLambda() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getUserName, "a").lt(User::getAge, "20");
        List<User> users = uMapper.selectList(lambdaQuery);
        System.out.println(users);
    }

    @Test
    public void mpSelectMy() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getUserName, "a").lt(User::getAge, "20");
        List<User> users = uMapper.sleAll(lambdaQuery);
        System.out.println(users);
    }

    @Test
    public void mpSelectPage() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        Page<User> userPage = new Page<>(1, 2, false);
        IPage<User> userIPage = uMapper.selectPage(userPage, lambdaQuery);
        System.out.println(userIPage);
    }

    @Test
    public void mpSelectList() {
        List<User> users = uMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void mpInsert() {
        User user = new User();
        user.setUserName("老王");
        user.setSex(1);
        user.setTime(new Date().getTime());
        uMapper.insert(user);
    }
}