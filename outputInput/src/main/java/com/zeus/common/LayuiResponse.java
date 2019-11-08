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
public class LayuiResponse<T> {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;


    /**
     * 返回数据
     */
    private T data;

    public LayuiResponse() {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
    }

    /**
     * @Description 成功构造器
     * @Author 元稹
     * @Date 18/12/2018 18:23
     * @Param [success, msg, data]
     * @Return
     */
    public LayuiResponse(String msg, T data) {
        this.code = SUCCESS_CODE;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @Description 失败构造器
     * @Author 元稹
     * @Date 18/12/2018 18:23
     * @Param [success, code, msg]
     * @Return
     */
    public LayuiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayuiResponse(int code, T data) {
        this.code = code;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }

    public LayuiResponse(int code, String msg, T data) {
        this.code = code;
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
    public LayuiResponse(T data) {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }
}
