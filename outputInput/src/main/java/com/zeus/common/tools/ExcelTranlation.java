package com.zeus.common.tools;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.zeus.entity.Example;

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
    public static boolean contextLoads(List<Example> example) {
        String  templateFileName="C:\\Users\\NING MEI\\Desktop\\码单样张.xls";

        String fileName = "C:\\Users\\NING MEI\\Desktop\\码单样张1.xls";
        Example example1=example.get(0);

        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
        // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
        // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
        // 如果数据量大 list不是最后一行 参照下一个
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(data(), fillConfig, writeSheet);
        excelWriter.fill(data(), fillConfig, writeSheet);
        Map<String, Object> map = new HashMap<String, Object>();
        if(example1.getIsInput()==1) {
            map.put("name", example1.getCompanyName() + "入库单");
        }else{
            map.put("name", example1.getCompanyName() + "出库单");
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String dateNowStr=sdf.format(d);
        map.put("date", dateNowStr);
        map.put("customerName",example1.getCustomerName());
        map.put("produceName",example1.getProduceName());
        map.put("unit",example1.getUnit());
        map.put("warehourseKeeperName",example1.getWarehouseKeeperName());
        map.put()

//        map.put("total", 1000);
        excelWriter.fill(map, writeSheet);
        excelWriter.finish();
    }
}
