package com.mq.demo.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 *
 * </p>
 *
 * @author 陈恩惠
 * @since 2019-06-10
 */

public class TenantUser implements Serializable {

    private static final long serialVersionUID = 8533647259674051440L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long tuId;

    @TableField(value = "t_id")
    private Integer tid;

    private String name;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTuId() {
        return tuId;
    }

    public void setTuId(Long tuId) {
        this.tuId = tuId;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
