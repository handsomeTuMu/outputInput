package com.zeus.common.utils;

import java.sql.Timestamp;

/**
 * 对于该项目的热度统计规范
 * @author:tumu
 * @date:2019/9/19
 * @ver:1.0
 **/
public class HeatUtil {
    //计算初始热度
    public static Float baseHeat(
            //粉丝数
            Integer fansNumber
            //被收藏数
            ,Integer collectNumber
            //获赞数
            ,Integer likeNumber){


        //发布者粉丝数log1.03(x+10)-80
        double fans=(Math.log(fansNumber+10))/(Math.log(1.03))-79;
        //发布者被收藏数log1.05(x+10)-48
        double collected=(Math.log(collectNumber+10))/(Math.log(1.05))-47;
        //发布者点赞数log1.05(x+10)-48
        double like=(Math.log(likeNumber+10))/(Math.log(1.09))-26;
        return (float)(fans+collected+like);
    }

    //互动热度
    public static Float interactHeat(
            //浏览量
            Integer views
            //点赞数
            ,Integer likeNumber
            //评论数
            ,Integer commitNumber
            //喜欢总数
            ,Integer loveNumber
            //收藏
            ,Integer collectNumber
            //分享
            ,Integer shareNumber
            //投票人数
            ,Integer peopleNumber
            //投票数
            ,Integer boatTicketNumber
    ){
        return  (float) (views*1+likeNumber* 5+commitNumber* 12+loveNumber*0.5+collectNumber*  20
                +shareNumber*  20+peopleNumber* 15 +boatTicketNumber* 1);

    }
    //随时间衰减的热度
    public static Float  thwartedHeat(
            //初始热度
            Double baseHeat
            //天数
            ,Integer days
    ){
        int base=1;
        for(int i=0;i<days;i++){
            base*=0.98;
        }
        return (float)(baseHeat*base);

    }
    //时间戳类型转换成相差天数
    public static Integer tranformDays(Timestamp timestamp){
       Long createTime= timestamp.getTime();
       Long DValue=System.currentTimeMillis()-createTime;
       Integer days= Math.toIntExact(DValue / (1000 * 60 * 60 * 24) + 1);
       return days;
    }

    //半小时前的时间忽略不计，半小时后的进一小时,算几天后的时间戳
    public static Timestamp roundingTime(Integer days){
        Long createTime= System.currentTimeMillis();
        if(createTime%(1000*60*60)>(1000*30*60)){
            createTime = (createTime / (1000 * 60*60)+1) * (1000 * 60*60);
        }else {
            createTime = createTime / (1000 * 60*60) * (1000 * 60*60);
        }
        Long DValue=createTime+(1000 * 60 * 60 * 24)*days;
        Timestamp a=new Timestamp(DValue);
        return a;
    }

    //半小时前的时间忽略不计，半小时后的进一小时,算几天后的时间戳
    public static Long roundingTimeLong(Integer days){
        Long createTime= System.currentTimeMillis();
        if(createTime%(1000*60*60)>(1000*30*60)){
            createTime = (createTime / (1000 * 60*60)+1) *  60*60;
        }else {
            createTime = createTime / (1000 * 60*60) * 60*60;
        }
        Long DValue=createTime+(60 * 60 * 24)*days;
        return DValue;
    }


    public static Float baseWeight(
            //粉丝数
            Integer fansNumber
            //被收藏数
            ,Integer collectNumber
            //获赞数
            ,Integer likeNumber
            //诚信金数额
            ,Integer faithMoney){


        //发布者粉丝数log1.01(x+44)-380
        double fans=(Math.log(fansNumber+44))/(Math.log(1.01))-380;
        //发布者被收藏数log1.05(x+10)-48
        double collected=(Math.log(collectNumber+10))/(Math.log(1.05))-47;
        //发布者点赞数log1.05(x+10)-48
        double like=(Math.log(likeNumber+10))/(Math.log(1.09))-26;
        //用户缴纳诚信保证金直接+200+ 诚信保证金数额带入公式
        //log1.01(x-80)-300
        double faith=0;
        if(faithMoney!=null){
            faith=200+(Math.log(faithMoney-80))/(Math.log(1.01))-300;
        }
        return (float)(fans+collected+like+faith);
    }

    //互动权重
    public static Float interactWeight(
            //浏览量
            Integer views
            //点赞数
            ,Integer likeNumber
            //评论数
            ,Integer commitNumber
            //喜欢总数
            ,Integer loveNumber
            //收藏
            ,Integer collectNumber
            //分享
            ,Integer shareNumber
            //投票人数
            ,Integer peopleNumber
            //投票数
            ,Integer boatTicketNumber
    ){
        return  (float) (views*1+likeNumber* 5+commitNumber* 12+loveNumber*0.5+collectNumber*  20
                +shareNumber*  20+peopleNumber* 15 +boatTicketNumber* 1);

    }

    public static String timeTranlate(Timestamp timestamp){
        long a=timestamp.getTime()-System.currentTimeMillis();

       if(a>=0) {
           if (a / (1000 * 60 * 60 * 24) >= 1) {
               return a / (1000 * 60 * 60 * 24) + "天";
           }
           if (a / (1000 * 60 * 60) >= 1) {
               return a / (1000 * 60 * 60) + "小时";
           }
           if (a / (1000 * 60) >= 1) {
               return a / (1000 * 60) + "分钟";
           }
           return a / 1000 + "秒";
       }else{
           a=-a;
           if (a / (1000 * 60 * 60 * 24) >= 1) {
               return a / (1000 * 60 * 60 * 24) + "天前";
           }
           if (a / (1000 * 60 * 60) >= 1) {
               return a / (1000 * 60 * 60) + "小时前";
           }
           if (a / (1000 * 60) >= 1) {
               return a / (1000 * 60) + "分钟前";
           }
           return a / 1000 + "秒前";
       }
    }



    public static void main(String[] args) {
        System.out.println(
        Math.log(10)/(Math.log(1.05))-48
        );
    }
}
