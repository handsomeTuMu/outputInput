package com.zeus.common.tools;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.zeus.entity.Cargo;
import com.zeus.entity.Example;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.smtp.SmtpRequests.data;

/**
 * 2 * @Author: tumu
 * 3 * @Date: 2019/11/11 17:49
 * 4
 */
public class ExcelTranlation {
    public static String  contextLoads(List<Example> example,Integer userId) {
        String  templateFileName="file/base/"+example.size()+".xls";

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
        String dateStr = simpleDateFormat.format(date);
        String Name="";
        if (example.get(0).getIsInput() == 1) {
           Name= example.get(0).getCompanyName() + "入库单"+dateStr;
        } else {
            Name= example.get(0).getCompanyName() + "出库单"+dateStr;
        }
        String fileName= "file/user/"+userId+"/"+Name+".xls";
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        for(int k=0;k<example.size();k++) {
            Example example1 = example.get(k);
            WriteSheet writeSheet = EasyExcel.writerSheet(k/2).build();
            if (k % 2 == 0) {
                // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
                // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
                // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
                // 如果数据量大 list不是最后一行 参照下一个
                FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
                excelWriter.fill(data(), fillConfig, writeSheet);
                excelWriter.fill(data(), fillConfig, writeSheet);
                Map<String, Object> map = new HashMap<String, Object>();
                if (example1.getIsInput() == 1) {
                    map.put("name", example1.getCompanyName() + "入库单");
                    map.put("man", "经办人");
                } else {
                    map.put("name", example1.getCompanyName() + "出库单");
                    map.put("man", "收货人");
                }
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                String dateNowStr = sdf.format(d);
                map.put("date", dateNowStr);
                map.put("customerName", example1.getCustomerName());
                map.put("produceName", example1.getProduceName());
                map.put("unit", example1.getUnit());
                map.put("warehourseKeeperName", example1.getWarehouseKeeperName());
                //仓号
                map.put("warehourseNumber", example1.getWarehourseNumber());
                //缸号
                map.put("batchNumber", example1.getBatchNumber());
                //车重
                map.put("wagonNumber", example1.getWagonNumber());
                //克重
                map.put("weight", example1.getWeight());
                List<Cargo> cargos = example1.getCargoList();
                Integer value = 0;
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i < cargos.size()) {
                            Cargo cargo = cargos.get(i);
                            map.put("color" + i, cargo.getColor());
                            map.put("type" + i, cargo.getType());
                            Class cargoClass = cargo.getClass();
                            for (int j = 1; j < 11; j++) {
                                String b = "getValue" + j;
                                Method method = cargoClass.getMethod(b, null);
                                if ((Integer) method.invoke(cargo, new Object[]{}) != null) {
                                    value += 1;
                                }
                                map.put("value" + i + (j - 1), (Integer) method.invoke(cargo, new Object[]{}));
                            }
                        } else {
                            map.put("color" + i, null);
                            map.put("type" + i, null);
                            for (int j = 0; j < 10; j++) {
                                map.put("value" + i + j, null);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                map.put("number", value);
                excelWriter.fill(map, writeSheet);
            }else{
                FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
                excelWriter.fill(data(), fillConfig, writeSheet);
                excelWriter.fill(data(), fillConfig, writeSheet);
                Map<String, Object> map = new HashMap<String, Object>();
                if (example1.getIsInput() == 1) {
                    map.put("1name", example1.getCompanyName() + "入库单");
                    map.put("1man", "经办人");
                } else {
                    map.put("1name", example1.getCompanyName() + "出库单");
                    map.put("1man", "收货人");
                }
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                String dateNowStr = sdf.format(d);
                map.put("1date", dateNowStr);
                map.put("1customerName", example1.getCustomerName());
                map.put("1produceName", example1.getProduceName());
                map.put("1unit", example1.getUnit());
                map.put("1warehourseKeeperName", example1.getWarehouseKeeperName());
                //仓号
                map.put("1warehourseNumber", example1.getWarehourseNumber());
                //缸号
                map.put("1batchNumber", example1.getBatchNumber());
                //车重
                map.put("1wagonNumber", example1.getWagonNumber());
                //克重
                map.put("1weight", example1.getWeight());
                List<Cargo> cargos = example1.getCargoList();
                Integer value = 0;
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i < cargos.size()) {
                            Cargo cargo = cargos.get(i);
                            map.put("1color" + i, cargo.getColor());
                            map.put("1type" + i, cargo.getType());
                            Class cargoClass = cargo.getClass();
                            for (int j = 1; j < 11; j++) {
                                String b = "getValue" + j;
                                Method method = cargoClass.getMethod(b, null);
                                if ((Integer) method.invoke(cargo, new Object[]{}) != null) {
                                    value += 1;
                                }
                                map.put("1value" + i + (j - 1), (Integer) method.invoke(cargo, new Object[]{}));
                            }
                        } else {
                            map.put("1color" + i, null);
                            map.put("1type" + i, null);
                            for (int j = 0; j < 10; j++) {
                                map.put("1value" + i + j, null);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                map.put("1number", value);
                excelWriter.fill(map, writeSheet);
            }
        }
        excelWriter.finish();
        return Name;
    }
}
