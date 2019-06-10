package com.mq.demo.sys.dto;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * @ClassName TenantDto
 * Description TODO
 * @Author 陈恩惠
 * @Date 2019/6/10 15:56
 **/

/**
 * 用于给前端表现层
 */
public class TenantDto implements Serializable {

    private static final long serialVersionUID = 1336932838633416942L;


    private Integer id;

    private String tName;

    /**
     * 服务商ID
     */
    private Long tenantId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
