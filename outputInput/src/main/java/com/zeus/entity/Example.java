package com.zeus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author tumu
 * @since 2019-11-11
 */
@Data
public class Example  extends Bill{

      private List<Cargo> cargoList;

      public Example() {
      }
      public Example(Bill bill) {
            this.setWeight(bill.getWeight());
            this.setWagonNumber(bill.getWagonNumber());
            this.setWarehourseNumber(bill.getWarehourseNumber());
            this.setWarehouseKeeperName(bill.getWarehouseKeeperName());
            this.setBatchNumber(bill.getBatchNumber());
            this.setUnit(bill.getUnit());
            this.setProduceName(bill.getProduceName());
            this.setCustomerName(bill.getCustomerName());
            this.setIsInput(bill.getIsInput());
            this.setCompanyName(bill.getCompanyName());
            this.setCreateTime(bill.getCreateTime());
            this.setExcelId(bill.getExcelId());
            this.setBillId(bill.getBillId());
            this.setUpdateTime(bill.getUpdateTime());
      }
}
