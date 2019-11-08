package com.zeus.common.tools;

import cn.hutool.json.JSONUtil;
import com.zeus.common.Response;
import org.apache.poi.ss.formula.functions.T;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author:tumu
 * @date:2019/8/13
 * @ver:1.0
 **/
public class MyEncoder implements Encoder.Text<Response> {
    @Override
    public String encode(Response response) throws EncodeException {
        try{
            return JSONUtil.toJsonStr(response);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
