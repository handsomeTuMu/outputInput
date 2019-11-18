package com.zeus.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeus.common.LayuiResponse;
import com.zeus.entity.Admin;
import com.zeus.service.AdminService;
import com.zeus.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 2 * @Author: tumu
 * 3 * @Date: 2019/11/18 14:34
 * 4
 */
@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    RedisService redisService;
    /**
     * 登陆界面
     * @return
     */
    @GetMapping("/")
    public String loginPage(){

        return  "loginPage";
//         return null;
    }

    /**
     * 登陆
     * @return
     */

    @PostMapping("/admin/login")
    @ResponseBody
    public LayuiResponse login(String name, String password){
         String Md5= DigestUtils.md5DigestAsHex(password.getBytes());

    Admin admin=adminService.getOne(new QueryWrapper<Admin>().eq("username",name));
    if(admin==null){
        return new LayuiResponse(400,"无此用户");
    }
    if(!admin.getPassword().equals(Md5)){
        return new LayuiResponse<>(400,"密码不正确");
    }
        String md5Key = password + System.currentTimeMillis();
        String token = DigestUtils.md5DigestAsHex(md5Key.getBytes()) + System.currentTimeMillis();
        redisService.set(token,admin);
        redisService.expire(token,1800);
        admin.setToken(token);
        return  new LayuiResponse<>(0,"登陆成功",admin);
    }

    @GetMapping("/admin/index")
    public String index(){

        return "index1";
    }

    @GetMapping("/admin/userSelect")
    public String userSelect(){

        return "userSelect";
    }

    @GetMapping("/admin/getUser")
    @ResponseBody
    public LayuiResponse getUser(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer limit){
        PageHelper.startPage(page,limit);

        List<Admin> list=adminService.list(new QueryWrapper<Admin>());
        PageInfo<Admin> pageInfo=new PageInfo<>(list);
        return new LayuiResponse<>(0,pageInfo.getTotal(),pageInfo.getList());
    }

    @PostMapping("/admin/logout")
    @ResponseBody
    public LayuiResponse logout(){

        return new LayuiResponse<>(0,"");
    }

    @GetMapping("/admin/adminInfo")
    public String adminInfo(){

        return "adminInfo";
    }

    @GetMapping("/admin/password")
    public String password(){

        return "password";
    }

    @GetMapping("/admin/timeout")
    public String timeout(){

        return "timeout";
    }



    @PostMapping("/admin/updPwd")
    @ResponseBody
    public LayuiResponse updPwd(String oldPwd, String password, String repassword, HttpServletRequest request){
     if(!password.equals(repassword)){
         return new LayuiResponse(400,"两次密码不一致");
     }
     String token=request.getHeader("token");
     Admin admin= redisService.get(token,Admin.class);
     Admin admin1=adminService.getById(admin.getId());
     String oldpwd=DigestUtils.md5DigestAsHex(oldPwd.getBytes());
     if(!admin1.getPassword().equals(oldpwd)){
         return new LayuiResponse(400,"密码不正确");
     }
     else{
         String md5=DigestUtils.md5DigestAsHex(password.getBytes());
         admin1.setPassword(md5);
         admin1.updateById();
     }


        return new LayuiResponse(0,"修改密码成功");
    }
}
