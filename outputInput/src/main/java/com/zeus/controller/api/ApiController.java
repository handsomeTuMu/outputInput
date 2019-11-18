package com.zeus.controller.api;

import com.zeus.common.Response;
import com.zeus.entity.Example;
import com.zeus.entity.Example1;
import com.zeus.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 2 * @Author: tumu
 * 3 * @Date: 2019/11/8 11:08
 * 4
 */
@RestController
public class ApiController {
    @Autowired
    ApiService apiService;
     @PostMapping("/api/login")
         public Response login(String phone,String password){

             return  apiService.login(phone,password);
//         return null;
         }
         
    @PostMapping("/api/register")
        public Response register(String phone,String password,String code ){

            return  apiService.register(phone,password,code);
        }
     @PostMapping("/api/getCode")
         public Response getCode(String phone,Integer i){
             return  apiService.getCode(phone,i);
      }
      
  @PostMapping("/api/forgetPassword")
      public Response forgetPassword(String phone,String code,String password){

          return  apiService.forgetPassword(phone,code,password);
      }
      
  @PostMapping("/api/addTable")
      public Response addTable(@RequestBody List<Example> example, HttpServletRequest request){
          String token=request.getHeader("token");
          return apiService.addTable(example,token);
      }

    @PostMapping("/api/excelList")
    public Response export(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "10") Integer size){
        String token=request.getHeader("token");
        return apiService.excelList(token,page,size);
    }

    @PostMapping("/api/delExcel")
    public Response delExcel(HttpServletRequest request,Integer[] id){
        String token=request.getHeader("token");
        return apiService.delExcel(token,id);
    }
    @PostMapping("/api/excelDetail")
    public Response excelDetail(HttpServletRequest request,Integer id){
        String token=request.getHeader("token");
        return apiService.excelDetail(token,id);
    }

    @PostMapping("/api/alterExcel")
    public Response alterExcel(HttpServletRequest request, @RequestBody Example1 example1){
        String token=request.getHeader("token");
        return apiService.alterExcel(token,example1);
    }

    @PostMapping("/api/export")
    public Response export(HttpServletRequest request,Integer id){
        String token=request.getHeader("token");
        return apiService.export(token,id);
    }

    @GetMapping("/error/lackParameter")
    public Response lackParameter(){
        return new Response(400,"缺少token");
    }
    @GetMapping("/error/tokenError")
    public Response tokenError(){
        return new Response(400,"token不存在或已过期");
    }


        
    
}
