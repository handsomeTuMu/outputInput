package com.zeus.controller.api;

import com.zeus.common.Response;
import com.zeus.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


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
        
    
}
