
package com.mq.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mq.demo.dao.UserMapper;
import com.mq.demo.model.User;
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
    @Test
    public void mpDel() {
        int id = 2;
        int i = uMapper.deleteById(id);
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
        Page<User> userPage = new Page<>(1, 2,false);
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