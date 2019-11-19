package com.zeus.service.impl;

import com.alipay.api.domain.Car;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeus.common.Response;
import com.zeus.common.tools.ExcelTranlation;
import com.zeus.common.utils.PhoneUtil;
import com.zeus.common.utils.RegexCheckUtil;
import com.zeus.dao.BillMapper;
import com.zeus.dao.CargoMapper;
import com.zeus.dao.ExcelMapper;
import com.zeus.entity.*;
import com.zeus.dao.ApiMapper;
import com.zeus.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeus.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    ExcelMapper excelMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    CargoMapper cargoMapper;

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
        redis.delete(api.getToken());
        redis.set(token,token);
        redis.expire(token,3*24*3600);
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
        api1.setCreateTime(new Timestamp(System.currentTimeMillis()+8*60*60*1000));
        api1.insert();
        File file=new File("/code/tumu/file/"+api1.getId());
        file.mkdir();

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

    @Override
    public Response addTable(List<Example> example,String token) {
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("token",token));
        Integer userId=api.getId();
        Excel excel=new Excel();
        excel.setUserId(userId);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH:mm ");
        String dateStr = simpleDateFormat.format(date);
        String Name="";
        if (example.get(0).getIsInput() == 1) {
            Name= example.get(0).getCompanyName() + "入库单";
        } else {
            Name= example.get(0).getCompanyName() + "出库单";
        }
        excel.setRecordName(dateStr+Name);



        excel.setCreateTime(new Timestamp(System.currentTimeMillis()+8*60*60*1000));
        excel.insert();
        for(Example example1:example){
            //插入数据
            Bill bill=(Bill)example1;
            bill.setCreateTime(new Timestamp(System.currentTimeMillis()));
            bill.setExcelId(excel.getExcelId());
            bill.insert();
            for(Cargo cargo:example1.getCargoList()) {
                cargo.setBillId(bill.getBillId());
                cargo.setCreateTime(new Timestamp(System.currentTimeMillis()+8*60*60*1000));
                cargo.insert();

            }

        }
//        String fileName=ExcelTranlation.contextLoads(example,userId);
//        excel.setExcelName(fileName);
//        String address="120.27.25.104/file/"+userId+"/"+fileName+".xls";
//        excel.setExcelAddress(address);
//        excel.updateById();

        return  new Response<>(200,"建表成功");
    }

    @Override
    public Response excelList(String token, Integer page, Integer size) {
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("token",token));
        Integer userId=api.getId();
        PageHelper.startPage(page,size);
        List<Excel> excelList=excelMapper.selectList(new QueryWrapper<Excel>().eq("user_id",userId));
        PageInfo<Excel> pageInfo=new PageInfo<>(excelList);
        return new Response(200,pageInfo.getTotal(),"获取列表成功",pageInfo.getList());

    }

    @Override
    public Response delExcel(String token, Integer[] id) {
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("token",token));
        Integer userId=api.getId();
        for(Integer ids:id){
            Excel excel=excelMapper.selectById(ids);
            if(excel==null){
                return new Response(400,"该数据不存在");
            }
            if(excel.getUserId()!=userId){
                return new Response(400,"不能删别人的表单");
            }
        }
        for(Integer ids:id){
            Excel excel=excelMapper.selectById(ids);
            List<Bill> billList=billMapper.selectList(new QueryWrapper<Bill>().eq("excel_id",excel.getExcelId()));
            for(Bill bill:billList){
                List<Cargo> cargos=cargoMapper.selectList(new QueryWrapper<Cargo>().eq("bill_id",bill.getBillId()));
                for(Cargo cargo:cargos){
                    cargo.deleteById();
                }
                bill.deleteById();
            }
            excel.deleteById();
        }
        return new Response(200,"删除成功");
    }

    @Override
    public Response excelDetail(String token, Integer id) {
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("token",token));
        Integer userId=api.getId();

        Excel excel=excelMapper.selectById(id);
        if(excel==null){
            return new Response(400,"该数据不存在");
        }
        if(excel.getUserId()!=userId){
            return new Response(400,"这不是你的表单");
        }

        Example1 example1=new Example1(excel);
        List<Bill> billList=billMapper.selectList(new QueryWrapper<Bill>().eq("excel_id",id));
        List<Example> exampleList=new ArrayList<>();
        for(Bill bill:billList){
            Example example=new Example(bill);
            List<Cargo> cargos=cargoMapper.selectList(new QueryWrapper<Cargo>().eq("bill_id",bill.getBillId()));
            example.setCargoList(cargos);
            exampleList.add(example);
        }
        example1.setExcelList(exampleList);
        return new Response<>(200,"查询详情成功",example1);
    }

    @Override
    public Response alterExcel(String token, Example1 example1) {
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("token",token));
        Integer userId=api.getId();

        Excel excel=(Excel) example1;
        if(excel==null){
            return new Response(400,"该数据不存在");
        }
        if(excel.getUserId()!=userId){
            return new Response(400,"这不是你的表单");
        }
        excel.updateById();


        List<Example> excelList=example1.getExcelList();
        for(Example example:excelList){
            Bill bill=(Bill)example;
            bill.updateById();
            List<Cargo> cargoList=example.getCargoList();
            for(Cargo cargo:cargoList){
                cargo.updateById();
            }
        }


        return new Response(200,"修改成功");
    }

    @Override
    public Response export(String token, Integer id) {
        Api api=apiMapper.selectOne(new QueryWrapper<Api>().eq("token",token));
        Integer userId=api.getId();

        Response response=excelDetail(token,id);
        if(response.getStatus()==400){
            return response;
        }
        Example1 example1= (Example1) response.getData();
        Excel excel=(Excel) example1;
        List<Example>example=example1.getExcelList();
        String fileName=ExcelTranlation.contextLoads(example,userId);
        excel.setExcelName(fileName);
        String address="120.27.25.104/file/"+userId+"/"+fileName+".xls";
        excel.setExcelAddress(address);
        excel.updateById();
        return new Response<>(200,"转换成功",address);
    }
}
