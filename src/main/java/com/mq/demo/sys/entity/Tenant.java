package com.mq.demo.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 陈恩惠
 * @since 2019-06-10
 */

public class Tenant implements Serializable {

    private static final long serialVersionUID = 6770673708347166828L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 服务商ID
     */
    private Long tenantId;

    /**
     * 姓名
     */
    private String name;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
