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

}
