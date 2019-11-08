package com.zeus.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  获取请求和响应对象
 * @author XiaoLi
 */
public class HttpServletUtil {
	
	//获取request对象
	public static HttpServletRequest getRequest() {
		
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	//获取response对象
	public static HttpServletResponse getResponse() {
		
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	//获取后台管理员登录的token
	public static String getToken() {
		String token = null;
		Cookie[] cookies = getRequest().getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("freedomBoat")) {
					token = cookie.getValue();
					break;
				}
			}
		}
		return token;
	}

	public static String clearToken() {
		String token = null;
		Cookie[] cookies = getRequest().getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if (("freedomBoat").equals(cookie.getName())){
					cookie.setValue(null);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					getResponse().addCookie(cookie);
					break;
				}
			}
		}
		return token;
	}

	
}
