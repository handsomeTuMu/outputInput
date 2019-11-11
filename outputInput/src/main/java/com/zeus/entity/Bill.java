package com.zeus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tumu
 * @since 2019-11-11
 */
@TableName("tbl_bill")
public class Bill extends Model<Bill> {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
        @TableId(value = "bill_id",type = IdType.AUTO)
         private Integer billId;

        /**
     * 公司名
     */
         private String companyName;

        /**
     * 是否是入库单
     */
         private Integer isInput;

        /**
     * 仓管名
     */
         private String warehouseKeeperName;

        /**
     * 客户名
     */
         private String customerName;

        /**
     * 产品名
     */
         private String produceName;

        /**
     * 计量单位
     */
         private String unit;

        /**
     * 仓号
     */
         private Integer warehourseNumber;

        /**
     * 缸号
     */
         private Integer batchNumber;

        /**
     * 车号
     */
         private Integer wagonNumber;

        /**
     * 克重
     */
         private Float weight;

        /**
     * 创建时间
     */
         private Timestamp createTime;

        /**
     * 更新时间
     */
         private Timestamp updateTime;


    public Integer getBillId() {
        return billId;
    }

    public Bill setBillId(Integer billId) {
        this.billId = billId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Bill setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Integer getIsInput() {
        return isInput;
    }

    public Bill setIsInput(Integer isInput) {
        this.isInput = isInput;
        return this;
    }

    public String getWarehouseKeeperName() {
        return warehouseKeeperName;
    }

    public Bill setWarehouseKeeperName(String warehouseKeeperName) {
        this.warehouseKeeperName = warehouseKeeperName;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Bill setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getProduceName() {
        return produceName;
    }

    public Bill setProduceName(String produceName) {
        this.produceName = produceName;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Bill setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getWarehourseNumber() {
        return warehourseNumber;
    }

    public Bill setWarehourseNumber(Integer warehourseNumber) {
        this.warehourseNumber = warehourseNumber;
        return this;
    }

    public Integer getBatchNumber() {
        return batchNumber;
    }

    public Bill setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
        return this;
    }

    public Integer getWagonNumber() {
        return wagonNumber;
    }

    public Bill setWagonNumber(Integer wagonNumber) {
        this.wagonNumber = wagonNumber;
        return this;
    }

    public Float getWeight() {
        return weight;
    }

    public Bill setWeight(Float weight) {
        this.weight = weight;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Bill setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public Bill setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.billId;
    }

    @Override
    public String toString() {
        return "Bill{" +
        "billId=" + billId +
        ", companyName=" + companyName +
        ", isInput=" + isInput +
        ", warehouseKeeperName=" + warehouseKeeperName +
        ", customerName=" + customerName +
        ", produceName=" + produceName +
        ", unit=" + unit +
        ", warehourseNumber=" + warehourseNumber +
        ", batchNumber=" + batchNumber +
        ", wagonNumber=" + wagonNumber +
        ", weight=" + weight +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
