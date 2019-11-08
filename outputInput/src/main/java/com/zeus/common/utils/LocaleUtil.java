package com.zeus.common.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.zeus.common.constant.Constans.LANGUAGE_US;

/**
 * @Description 本地环境处理操作工具类
 * @Author 浮生
 * @Date 22/12/2018 00:22
 * @Version V1.0
 */
public class LocaleUtil {

    /**
     * 中英文切换
     *
     * @param input
     * @param locale
     * @return java.lang.String
     * @methodName getLocaleFormat
     * @author fusheng
     * @date 2019-03-11
     */
    public static String getLocaleFormat(String input, String locale) {
        Locale locale1 = Locale.getDefault();
        if (LANGUAGE_US.equalsIgnoreCase(locale)) {
            locale1 = getUSFormat(input);
        } else {
            locale1 = getZHFormat(input);
        }
        ResourceBundle res = null;
        String outPut = null;
        try {
            res = ResourceBundle.getBundle("message", locale1);
            outPut = res.getString(input);
            return outPut;
        } catch (Exception e) {
            return input;
        }

    }

    public static Locale getUSFormat(String input) {
        return Locale.US;

    }

    public static Locale getZHFormat(String input) {
        return new Locale("zh", "CN");
    }
}
