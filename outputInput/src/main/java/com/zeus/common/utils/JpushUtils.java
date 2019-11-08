package com.zeus.common.utils;

/**
 * @author:tumu
 * @date:2019/9/27
 * @ver:1.0
 **/

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import cn.hutool.json.JSONUtil;
import net.sf.json.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.schedule.ScheduleResult;

public class JpushUtils {
    //读取配置中的appkey和masterSecret

    protected static final Logger LOG = LoggerFactory.getLogger(JpushUtils.class);
    public static final String appKey = "41ce7ad565a7bfa2a382a668";
    public static final String masterSecret = "0053cd9797154b72e6ba6cef";

    /**
     *
     * @auth Ren
     * @date 2018年5月2日
     * @decripe 定时推送,利用DeviceSN做别名,点对点发送,同时记录返回的msg_id
     * @param obj 推送对象,deviceSN设备识别码,定时的时间date,MsgType推送的业务类型(APIConstants中定义),
     *            name推送的名称
     */
    public static ScheduleResult sendSchedulePush(Object obj, String deviceSN, Date date, String MsgType, String name) {
        JPushClient jPushClient = new JPushClient(masterSecret, appKey);
        String objStr = ObjectToJson(obj);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        ScheduleResult result = null;
        PushPayload push = PushPayload.newBuilder().setPlatform(Platform.all())
                .setMessage(Message.newBuilder().setMsgContent(objStr)
                        .addExtras(Collections.singletonMap("MsgType", MsgType)).build())
                .setAudience(Audience.alias(deviceSN)).build();
        try {
            result = jPushClient.createSingleSchedule(name, time, push);
            LOG.info("Got result - " + result);
            LOG.info("send objStr - " + objStr);
            System.out.println(result);
            System.out.println(objStr);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
        return result;
    }

    /**
     *
     * @auth Ren
     * @date 2018年5月2日
     * @decripe 定时推送,推送到所有设备,同时记录返回的msg_id
     * @param obj推送对象,定时的时间date,MsgType推送的业务类型(APIConstants中定义),name推送的名称
     */
    public static ScheduleResult sendSchedulePushAll(Object obj, Date date, String MsgType, String name) {
        JPushClient jPushClient = new JPushClient(masterSecret, appKey);
        String objStr = ObjectToJson(obj);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        ScheduleResult result = null;
        PushPayload push = PushPayload.newBuilder().setPlatform(Platform.all())
                .setMessage(Message.newBuilder().setMsgContent(objStr)
                        .addExtras(Collections.singletonMap("MsgType", MsgType)).build())
                .setAudience(Audience.all()).build();
        try {
            result = jPushClient.createSingleSchedule(name, time, push);
            LOG.info("Got result - " + result);
            LOG.info("send objStr - " + objStr);
            System.out.println(result);
            System.out.println(objStr);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
        return result;
    }

    /**
     *
     * @auth Ren
     * @date 2018年5月2日
     * @decripe 删除定时任务
     * @param scheduleId 定时任务的Id
     */
    public static void DeleteSchedule(String scheduleId) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret, appKey);
            jPushClient.deleteSchedule(scheduleId);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     *
     * @auth Ren
     * @date 2018年5月2日
     * @decripe:把obj对象的json串推送到别名为DeviceSN的设备上,同时记录返回的msg_id
     * @param obj 推送对象,deviceSN设备识别码,MsgType推送的业务类型(APIConstants中定义)
     */
    public static PushResult SendPush(Object obj, String DeviceSN, String MsgType) {
        JPushClient jPushClient = new JPushClient(masterSecret, appKey);
        String objStr = ObjectToJson(obj);
        PushPayload push = PushPayload.newBuilder().setPlatform(Platform.all())
                .setMessage(Message.newBuilder().setMsgContent(objStr)
                        .addExtras(Collections.singletonMap("MsgType", MsgType)).build())
                .setAudience(Audience.alias(DeviceSN)).build();
        PushResult result = null;
        try {
            result = jPushClient.sendPush(push);
            LOG.info("Got result - " + result);
            LOG.info("send objStr - " + objStr);
            System.out.println(result);
            System.out.println(objStr);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + push.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + push.getSendno());
        }
        if (result == null) {
            throw new RuntimeException("与设备通话失败，请联系管理员处理！");
        }
        return result;
    }

    /**
     *
     * @auth Ren
     * @date 2018年5月2日
     * @decripe 把obj对象的json串推送到所有设备上
     * @param obj 推送对象,MsgType推送的业务类型(APIConstants中定义)
     */
    public static PushResult SendPushAll(Object obj, String MsgType) {
        JPushClient jPushClient = new JPushClient(masterSecret, appKey);
        String objStr = ObjectToJson(obj);
        PushPayload push = PushPayload.newBuilder().setPlatform(Platform.all())
                .setMessage(Message.newBuilder().setMsgContent(objStr)
                        .addExtras(Collections.singletonMap("MsgType", MsgType)).build())
                .setAudience(Audience.all()).build();
        PushResult result = null;
        try {
            result = jPushClient.sendPush(push);
            LOG.info("Got result - " + result);
            LOG.info("send objStr - " + objStr);
            System.out.println(result);
            System.out.println(objStr);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + push.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + push.getSendno());
        }
        if (result == null) {
            throw new RuntimeException("推送失败,请联系管理员处理！");
        }
        return result;
    }
    public static String ObjectToJson(Object o) {
        String json = JSONUtil.toJsonStr(o);
        return json;
    }
}
