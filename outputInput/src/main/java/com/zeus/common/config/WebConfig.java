package com.zeus.common.config;

import com.zeus.common.intercept.AdminInterceptor;
import com.zeus.common.intercept.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:fusheng
 * @date:2019-04-14
 * @ver:1.0
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserInterceptor loginInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:///code/tumu/file/user/");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> userExcludes = new LinkedList<>();
        userExcludes.add("/api/login");
        userExcludes.add("/api/register");
        userExcludes.add("/api/getCode");
        userExcludes.add("/api/forgetPassword");
        userExcludes.add("/error/lackParameter");
        userExcludes.add("/error/tokenError");
        userExcludes.add("/file/**");
        userExcludes.add("/");
        userExcludes.add("/static/**");
        userExcludes.add("/admin/**");
        //静态文件
        registry.addInterceptor(loginInterceptor).excludePathPatterns(userExcludes.toArray(new String[userExcludes.size()]))
                .addPathPatterns("/**");
        List<String> adminExcludes = new LinkedList<>();
        adminExcludes.add("/admin/login");
        adminExcludes.add("/admin/index");
        adminExcludes.add("/admin/userSelect");
        adminExcludes.add("/admin/adminInfo");
        adminExcludes.add("/admin/password");
//        adminExcludes.add("/admin/getUser");
        registry.addInterceptor(adminInterceptor).excludePathPatterns(adminExcludes.toArray(new String[adminExcludes.size()]))
                .addPathPatterns("/admin/**");

    }


}
