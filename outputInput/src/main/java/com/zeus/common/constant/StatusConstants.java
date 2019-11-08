package com.zeus.common.constant;

/**
 * @Description 常量类
 * @Author 元稹
 * @Date 18/12/2018 14:55
 * @Version V1.0
 */
public class StatusConstants {
    /**
     * 成功返回默认码
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 成功返回默认信息
     */
    public static final String SUCCESS_MSG = "SUCCESS";
    /**
     * 失败返回默认码
     */
    public static final int COMMON_ERROR_CODE = 10000;
    /**
     * 成功返回默认信息
     */
    public static final String COMMON_ERROR_MSG = "未知错误，请联系技术人员";
    /**
     * 参数不完整返回码
     */
    public static final int PARAM_ERROR_CODE = 10001;
    /**
     * 参数不完整返回信息
     */
    public static final String PARAM_ERROR_MSG = "信息不完整";
    /**
     * 参数不完整返回码
     */
    public static final int CODE_ERROR_CODE = 10002;
    /**
     * 参数不完整返回信息
     */
    public static final String CODE_ERROR_MSG = "验证码错误或失效";
    /**
     * 手机格式不正确
     */
    public static final int PHONE_ERROR_CODE = 10101;
    /**
     * 手机格式不正确信息
     */
    public static final String PHONE_ERROR_MSG = "手机格式不正确";
    /**
     * 两次输入密码一致
     */
    public static final int PASSWORD_ERROR_CODE = 10102;
    /**
     * 两次输入密码一致
     */
    public static final String PASSWORD_ERROR_MSG = "两次输入密码不一致";
    /**
     * 登陆信息输入不完整
     */
    public static final int LOGIN_INCOMPLETE_CODE = 10103;
    /**
     * 登陆信息输入不完整
     */
    public static final String LOGIN_INCOMPLETE_MSG = "登陆信息输入不完整";
    /**
     * 用户名或密码输入错误
     */
    public static final int LOGIN_ERROR_CODE = 10104;
    /**
     * 用户名或密码输入错误
     */
    public static final String LOGIN_ERROR_MSG = "用户名或密码输入错误";
    /**
     * 用户已经注册
     */
    public static final int USER_REGISTER_CODE = 10105;
    /**
     * 用户已经注册
     */
    public static final String USER_REGISTER_MSG = "用户名或密码输入错误";
    /**
     * 该用户未注册
     */
    public static final int USER_NO_REGISTER_CODE = 10106;
    /**
     * 该用户未注册
     */
    public static final String USER_NO_REGISTER_MSG = "该用户未注册";

    /**
     * 未找到该用户信息
     */
    public static final int USER_NO_FOUND_CODE = 10107;
    /**
     * 未找到该用户信息
     */
    public static final String USER_NO_FOUND_MSG = "未找到该用户信息";


    /**
     * 题目类别
     */
    public static final int QUESTION_ONE = 1;
    /**
     * 未找到该用户信息
     */
    public static final int QUESTION_TOW = 2;
    /**
     * 未找到该用户信息
     */
    public static final int QUESTION_THREE = 3;
    /**
     * 未找到该用户信息
     */
    public static final int QUESTION_FOUR = 4;
    /**
     * 未找到该用户信息
     */
    public static final int QUESTION_FIVE = 5;
}
