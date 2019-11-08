package com.zeus.service;

import com.zeus.common.Response;
import com.zeus.entity.Api;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tumu
 * @since 2019-11-08
 */
public interface ApiService extends IService<Api> {

    /**
     * 登陆
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    Response login(String phone, String password);

    /**
     * 注册
     * @param phone 手机号
     * @param password 密码
     * @param code 验证码
     * @return
     */
    Response register(String phone, String password,String code);

    /**
     * 获取验证码
     * @param phone 手机号
     * @param i
     * @return
     */
    Response getCode(String phone, Integer i);

    /**
     * 忘记密码
     *
     * @param phone 手机号
     * @param code 验证码
     * @param password 更改后的密码
     * @return
     */
    Response forgetPassword( String phone, String code,String password);
}
