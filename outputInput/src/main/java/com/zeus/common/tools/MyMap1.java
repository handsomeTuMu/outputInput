package com.zeus.common.tools;

import cn.hutool.json.JSONUtil;
import com.google.common.base.CaseFormat;

import java.util.HashMap;

/**
 * @author:tumu
 * @date:2019/9/20
 * @ver:1.0
 **/
public class MyMap1 extends HashMap {
    @Override
    public Object put(Object key, Object value) {
        String key1=key.toString();
        String key2=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,key1);
        return super.put(key2, value);
    }

    public static void main(String[] args) {
        String value="['1','1']";
        value= JSONUtil.parseArray(value).getStr(0);
        System.out.println(value);
//        Integer i=1;
//        System.out.println(i.toString());
    }
}
