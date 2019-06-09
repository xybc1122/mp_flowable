package com.mq.demo.dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mq.demo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    //@Select("select * from dt_user ${ew.customSqlSegment}")
    List<User> sleAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
