package com.zeus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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
@Data
@TableName("tbl_cargo")
public class Cargo extends Model<Cargo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cargo_id", type = IdType.AUTO)
    private Integer cargoId;

        /**
     * 对应入库单编号
     */
         private Integer billId;


        /**
     * 型号
     */
         private String type;

        /**
     * 颜色
     */
         private String color;


    private Integer value1;

    private Integer value2;

    private Integer value3;

    private Integer value4;

    private Integer value5;

    private Integer value6;

    private Integer value7;

    private Integer value8;

    private Integer value9;

    private Integer value10;

    private Timestamp createTime;

    private Timestamp updateTime;



    public Cargo() {
    }

    public Cargo(Integer billId, String type, String color, Integer value1, Integer value2, Integer value3, Integer value4, Integer value5, Integer value6, Integer value7, Integer value8, Integer value9, Integer value10) {
        this.billId = billId;
        this.type = type;
        this.color = color;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.value7 = value7;
        this.value8 = value8;
        this.value9 = value9;
        this.value10 = value10;
    }
}
