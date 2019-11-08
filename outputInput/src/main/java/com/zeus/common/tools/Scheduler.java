package com.zeus.common.tools;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author:tumu
 * @date:2019/8/12
 * @ver:1.0
 **/
@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//
//    //每天0:00执行,衰减热度
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void testTasks() {
//        ApiService apiService = SpringUtils.getBean(ApiService.class);
//             apiService.thwartedHeat();
//        }
////        File file = new File("/code/video_montage/user");
////        File[] files=file.listFiles();
////        for(File f:files){
////            delFile(f);
////        }
//
//
//    //每小时衰减服务权重，检测一小时内要过期的任务是否
//    @Scheduled(cron = "0 0 * * * ?")
//     public void hours() {
//        System.out.println("执行每小时任务");
//        //每小时衰减服务权重
//        //检测一小时内要过期的任务,调用单独的定时器提醒并更改状态
//    ApiService apiService = SpringUtils.getBean(ApiService.class);
//    apiService.thwartedHeat();
//}
//
//
//    //删除文件夹及其子文件
//     public static boolean delFile(File file) {
//        if (!file.exists()) {
//                     return false;
//                  }
//        if (file.isDirectory()) {
//            File[] files = file.listFiles();
//            for (File f : files) {
//                delFile(f);
//               }
//             }
//          return file.delete();
//        }
}