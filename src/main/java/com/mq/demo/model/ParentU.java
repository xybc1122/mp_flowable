package com.mq.demo.model;

import com.baomidou.mybatisplus.annotation.TableLogic;

public class ParentU {

    @TableLogic
    private Integer deleted;


    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
