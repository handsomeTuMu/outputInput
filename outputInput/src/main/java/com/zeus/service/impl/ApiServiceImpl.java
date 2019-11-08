package com.zeus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeus.common.Response;
import com.zeus.common.utils.PhoneUtil;
import com.zeus.common.utils.RegexCheckUtil;
import com.zeus.entity.Api;
import com.zeus.dao.ApiMapper;
import com.zeus.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeus.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tumu
 * @since 2019-11-08
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {
    @Autowired
    RedisService redis;
    @Autowired
    ApiMapper apiMapper;

    @Override
    public Response login(String phone,String password) {
        if(phone==null||"".equals(phone)){
            return new Response(400,"请填写手机号");
        }
        if(!RegexCheckUtil.checkPhone(phone)){
         return new Response(400,"手机号格式不正确");
        }
        if(password==null||"".equals(password)){
            return new Response(400,"请填写密码");
        }
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("phone",phone));
        String password1=DigestUtils.md5DigestAsHex(password.getBytes());
        if(api==null){
            return new Response(400,"您还未注册");
        }
        if(!password1.equals(api.getPassword())){
            return new Response(400,"密码不正确");
        }

        String md5Key = phone + System.currentTimeMillis();
        String token = DigestUtils.md5DigestAsHex(md5Key.getBytes()) + System.currentTimeMillis();
        redis.set(phone+"token",token);
        redis.expire(phone+"token",7*24*3600);
        api.setToken(token);
        api.updateById();
        api.setPassword(null);
        api.setCreateTime(null);
        api.setUpdateTime(null);
        return new Response<>(200,"登陆成功",api);
    }

    @Override
    public Response register(String phone, String password, String code) {
        if(phone==null||"".equals(phone)){
            return new Response(400,"请填写手机号");
        }
        if(!RegexCheckUtil.checkPhone(phone)){
            return new Response(400,"手机号格式不正确");
        }
        if(password==null||"".equals(password)){
            return new Response(400,"请填写密码");
        }
        if(!RegexCheckUtil.checkPassword(password)){
            return new Response(400,"密码格式只能字母或数字或两者组合6-14位");
        }
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("phone",phone));
        if(api!=null){
            return new Response(400,"您的手机号已被注册");
        }
        String password1=DigestUtils.md5DigestAsHex(password.getBytes());
        String code1=redis.getString("register"+phone);
        if(code==null||code1==null||!(code1.equals(code))){
            return new Response(400,"验证码不正确");
        }
        Api api1=new Api(phone,password1);
        api1.insert();





        return new Response(200,"注册成功");
    }

    @Override
    public Response getCode(String phone, Integer i) {
        if(phone==null||"".equals(phone)){
            return new Response(400,"请填写手机号");
        }
        if(!RegexCheckUtil.checkPhone(phone)){
            return new Response(400,"手机号格式不正确");
        }
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("phone",phone));
        String realCode = PhoneUtil.getPhoneCode(phone, PhoneUtil.templateRegisterCode);
        if(i==0) {
            if (api != null) {
                return new Response(400, "您的手机号已被注册");
            }

            redis.set("register"+phone, realCode);
            redis.expire(phone, 3 * 60);
        }else if(i==1){
            if (api == null) {
                return new Response(400, "您的手机号还未注册");
            }
            redis.set("forget"+phone, realCode);
            redis.expire(phone, 3 * 60);
        }else{
            return new Response(400,"缺少参数");
        }


        return new Response<>(200,"获取验证码成功",realCode);
    }

    @Override
    public Response forgetPassword(String phone, String code,String password) {
        if(phone==null||"".equals(phone)){
            return new Response(400,"请填写手机号");
        }
        if(!RegexCheckUtil.checkPhone(phone)){
            return new Response(400,"手机号格式不正确");
        }
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("phone",phone));
        if (api == null) {
            return new Response(400, "您的手机号还未注册");
        }
        String code1=redis.getString("forget"+phone);
        if(code==null||code1==null||!(code1.equals(code))){
            return new Response(400,"验证码不正确");
        }
        String password1=DigestUtils.md5DigestAsHex(password.getBytes());
        api.setPassword(password1);

        return new Response(200,"修改密码成功");
    }
}
