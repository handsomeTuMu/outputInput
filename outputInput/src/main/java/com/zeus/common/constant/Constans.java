package com.zeus.common.constant;

/**
 * @author:fusheng
 * @date:2019-03-08
 * @ver:1.0
 **/
public class Constans {
    /**
     * token有效时间
     */
    public static final long TOKEN_EFFECTIVE_TIME = 30 * 60 * 1000;
    /**
     * 短信有效时间
     */
    public static final long SMS_EFFECTIVE_TIME = 15 * 60 * 1000;

    /**
     * token签发者
     */
    public static final String ISSUER = "SHOUWANG";
    /**
     * token签发者
     */
    public static final String WECHAT_TOKEN = "watch";
    /**
     * 英语语言
     */
    public static final String LANGUAGE_US = "US";
    /**
     * 中文语言
     */
    public static final String LANGUAGE_ZH = "ZH";
    /**
     * 验证码为手机类型
     */
    public static final String CODE_TYPE_PHONE = "1";
    /**
     * URL
     */
    public static final String URL = "https://test.hzsw-tech.com.cn/";
/**
     * URL
     */
    public static final String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?";
    /**
     * URL
     */
    public static final String URL_SPLIT = "?";

    /**
     * 文件类型 video
     */
    public static final String FILE_TYPE_VIDEO = "video";

    /**
     * 默认收货地址
     */
    public static final int ADDRESS_DEFAULT = 1;
    /**
     * 不是默认收货地址
     */
    public static final int ADDRESS_NO_DEFAULT = 0;


    /**
     * 文件类型 xls
     */
    public static final String FILE_TYPE_XLS = ".xls";

    /**
     * 文件类型 xlsx
     */
    public static final String FILE_TYPE_XLSX = ".xlsx";
    /**
     * 优惠券状态，0 是未使用
     */
    public static final int COUPON_STATUS_UNUSED = 0;
    /**
     * 优惠券状态，1 是已使用
     */
    public static final int COUPON_STATUS_USED = 1;
    /**
     * 优惠券状态，1 是已使用
     */
    public static final int WX_APP_ID = 1;

    public static final int ERROR_CODE = 10001;

}
