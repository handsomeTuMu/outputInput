package com.zeus.service;

import com.zeus.common.Response;
import com.zeus.entity.Api;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zeus.entity.Example;
import com.zeus.entity.Example1;

import java.util.List;

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

    /**
     * 建表
     * @param example
     * @return
     */
    Response addTable(List<Example> example,String token);

    /**
     * 历史单据
     * @param token
     * @param page
     * @param size
     * @return
     */
    Response excelList(String token, Integer page, Integer size);

    /**
     * 删除表单
     * @param token
     * @param id
     * @return
     */
    Response delExcel(String token, Integer[] id);

    /**
     * 查看内容详情
     * @param token
     * @param id
     * @return
     */
    Response excelDetail(String token, Integer id);

    /**
     * 修改数据
     * @param token
     * @param example1
     * @return
     */
    Response alterExcel(String token, Example1 example1);

    /**
     * 将数据库里的数据转换成excel
     * @param token
     * @param id
     * @return
     */
    Response export(String token, Integer id);
}
