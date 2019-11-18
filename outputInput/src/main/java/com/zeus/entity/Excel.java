package com.zeus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tumu
 * @since 2019-11-12
 */
@TableName("tbl_excel")
public class Excel extends Model<Excel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "excel_id", type = IdType.AUTO)
    private Integer excelId;

        /**
     * 用户id
     */
         private Integer userId;
    /**
     * 记录名称
     */
    private String recordName;

    /**
     * excel名称
     */
         private String excelName;

        /**
     * excel地址
     */
         private String excelAddress;

    private Timestamp createTime;

    private Timestamp updateTime;


    public Integer getExcelId() {
        return excelId;
    }

    public Excel setExcelId(Integer excelId) {
        this.excelId = excelId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Excel setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getExcelName() {
        return excelName;
    }

    public Excel setExcelName(String excelName) {
        this.excelName = excelName;
        return this;
    }

    public String getExcelAddress() {
        return excelAddress;
    }

    public Excel setExcelAddress(String excelAddress) {
        this.excelAddress = excelAddress;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Excel setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public Excel setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    @Override
    protected Serializable pkVal() {
        return this.excelId;
    }

    @Override
    public String toString() {
        return "Excel{" +
        "excelId=" + excelId +
        ", userId=" + userId +
        ", excelName=" + excelName +
        ", excelAddress=" + excelAddress +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
