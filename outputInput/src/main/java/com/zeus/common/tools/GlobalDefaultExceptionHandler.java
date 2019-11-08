package com.zeus.common.tools;


import com.zeus.common.LayuiResponse;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public LayuiResponse defaultExceptionHandler(HttpServletRequest req, Exception e){


        return new LayuiResponse(400,"你的权限不够了弟弟");
    }
}
