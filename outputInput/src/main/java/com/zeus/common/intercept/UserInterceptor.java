//package com.zeus.common.intercept;
//
//import com.zeus.dao.ApiMapper;
//import com.zeus.service.RedisService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 移动端用户未登录状态时，请求拦截，除了排除的url，移动端所有请求都需要带token凭证
// * @author XiaoLi
// */
//@Component
//public  class UserInterceptor implements HandlerInterceptor {
//	@Autowired
//	ApiMapper api;
//    @Autowired
//	RedisService redis;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		String token = request.getHeader("accessToken");
//		String phone = request.getHeader("accessPhone");
//		String redisToken=redis.getString(phone+"token");
//
//		if(token==null||phone==null) {
//			response.sendRedirect("/error/lackParameter");
//				return false;
//		}else {
//			if(redisToken==null){
//				String sqlToken=api.selectById(phone).getToken();
//				if(sqlToken.equals(token)){
//					return true;
//				}else {
//					response.sendRedirect("/error/tokenError");
//					return false;
//				}
//			}else{
//				if(redisToken.equals(token)){
//					return true;
//				}
//				else{
//					response.sendRedirect("/error/tokenError");
//					return false;
//				}
//			}
//		}
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//	}
//
//}
