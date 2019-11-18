package com.zeus.entity;

import lombok.Data;

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
public class Example1 extends Excel{

      private List<Example> excelList;

      public Example1() {
      }

      public Example1(Excel excel) {
            this.setUserId(excel.getUserId());
            this.setCreateTime(excel.getCreateTime());
            this.setUpdateTime(excel.getUpdateTime());
            this.setExcelAddress(excel.getExcelAddress());
            this.setExcelName(excel.getExcelName());
            this.setExcelId(excel.getExcelId());

      }
}
