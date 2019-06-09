package com.mq.demo.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

@TableName("dt_user")
public class User extends ParentU implements Serializable {


    private static final long serialVersionUID = -5445672656609041704L;
    @TableField(value = "name")
    private String userName;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer uid;

    private Integer age;

    private Integer sex;

    private Long time;


    @Version
    private Integer version;

    @TableField(exist = false)
    private String remark;



    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", uid=" + uid +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

}
