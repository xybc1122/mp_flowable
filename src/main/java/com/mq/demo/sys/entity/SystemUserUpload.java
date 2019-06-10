package com.mq.demo.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mq.demo.model.ParentU;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 陈恩惠
 * @since 2019-06-10
 */
@TableName("system_user_upload")
public class SystemUserUpload extends ParentU implements Serializable {

    private static final long serialVersionUID = 429523156559174335L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long syUid;
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 文件唯一名称
     */
    private String uuidName;

    /**
     * 上传文件名字
     */
    private String name;

    /**
     * 上传时间
     */
    private Long createDate;

    /**
     * 删除时间
     */
    private Long delDate;

    /**
     * 删除用户ID
     */
    private Long delDateId;

    /**
     * 上传备注
     */
    private String remark;

    /**
     * 上传服务器路径
     */
    private String filePath;

    /**
     * 写入到服务器路径
     */
    private String writeFilePath;

    /**
     * 上传状态 0代表成功,1代表失败,2代表上传成功后 有些没有skuId的没上传
     */
    private Integer status;

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 洲ID
     */
    private Integer areaId;

    /**
     * 菜单ID
     */
    private Integer mId;

    /**
     * 财务上传类型ID
     */
    private Integer payId;

    /**
     * 删除标示符/1代表删除 0代表正常 4代表上传时就失败
     */
    private Integer delMark;

    /**
     * 记录需要手动输入时间信息 /比如业务报告
     */
    @TableField("businessTime")
    private Long businessTime;

    /**
     * 版本标识
     */
    private Integer version;

    /**
     * 是否删除: 0表式正常;1表式删除
     */
    private Boolean delOrNot;

    /**
     * 关账时间
     */
    private String closingDate;

    /**
     * ftp图片url
     */
    private String url;


    public Long getSyUid() {
        return syUid;
    }

    public void setSyUid(Long syUid) {
        this.syUid = syUid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUuidName() {
        return uuidName;
    }

    public void setUuidName(String uuidName) {
        this.uuidName = uuidName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getDelDate() {
        return delDate;
    }

    public void setDelDate(Long delDate) {
        this.delDate = delDate;
    }

    public Long getDelDateId() {
        return delDateId;
    }

    public void setDelDateId(Long delDateId) {
        this.delDateId = delDateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getWriteFilePath() {
        return writeFilePath;
    }

    public void setWriteFilePath(String writeFilePath) {
        this.writeFilePath = writeFilePath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public Long getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(Long businessTime) {
        this.businessTime = businessTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getDelOrNot() {
        return delOrNot;
    }

    public void setDelOrNot(Boolean delOrNot) {
        this.delOrNot = delOrNot;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
