package com.zeus.common;

import lombok.Data;

import static com.zeus.common.constant.StatusConstants.SUCCESS_CODE;
import static com.zeus.common.constant.StatusConstants.SUCCESS_MSG;

/**
 * @Description 返回前端的统一类
 * @Author 元稹
 * @Date 18/12/2018 17:47
 * @Version V1.0
 */
@Data
public class Response<T> {

    /**
     * 返回码
     */
    private int status;

    /**
     * 返回信息
     */
    private String msg;


    /**
     * 返回数据
     */
    private T data;

    public Response() {
        this.status = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
    }

    /**
     * @Description 成功构造器
     * @Author 元稹
     * @Date 18/12/2018 18:23
     * @Param [success, msg, data]
     * @Return
     */
    public Response(String msg, T data) {
        this.status = SUCCESS_CODE;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @Description 失败构造器
     * @Author 元稹
     * @Date 18/12/2018 18:23
     * @Param [success, status, msg]
     * @Return
     */
    public Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Response(int status, T data) {
        this.status = status;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }

    public Response(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 返回成功信息
     *
     * @param data
     * @return
     * @methodName Response
     * @author fusheng
     * @date 2019-03-11
     */
    public Response(T data) {
        this.status = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }
}
