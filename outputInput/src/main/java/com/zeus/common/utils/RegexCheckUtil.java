package com.zeus.common.utils;

import org.springframework.util.StringUtils;

/**
 * 各种正则验证工具类，如手机号、邮箱、账号、密码、用户名
 * @author tumu
 */
public class RegexCheckUtil {

    /**
     * 手机号验证
     * @param phone
     * @return true格式正确，false格式错误
     */
    public static boolean checkPhone(String phone) {

        return (phone.matches("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$"));
    }

    /**
     * 邮箱验证码
     * @param email
     * @return true格式正确，false格式错误
     */
    public static boolean checkEmail(String email) {

        return email.matches("^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$");
    }

    /**
     * 密码验证，只能字母或数字或两者组合6-14位
     * @param password
     * @return true格式正确，false格式错误
     */
    public static boolean checkPassword(String password) {

        return password.matches("^[\\p{Punct}a-zA-Z0-9]{6,14}$");
    }

    /**
     * 用户名（昵称）检验
     * @param nickname
     * @return
     */
    public static void checkNickname(String nickname) {
        if(StringUtils.isEmpty(nickname))
            throw new RuntimeException("用户名不能为空");
        if(nickname.length()>12)
            throw new RuntimeException("用户名过长，限制12位");
        if(nickname.matches(".*[\\\\/\\^#\\$%&|{}<>\\.].*"))
            throw new RuntimeException("用户名含非法字符");
    }

    /**
     * 支付密码验证，只能数字6位
     * @param password
     * @return true格式正确，false格式错误
     */
    public static boolean checkPayPassword(String password) {

        return password.matches("^[0-9]{6}$");
    }






}
