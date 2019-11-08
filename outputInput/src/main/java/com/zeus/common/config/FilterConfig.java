package com.zeus.common.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author:fusheng
 * @date:2019-04-14
 * @ver:1.0
 **/

@SpringBootConfiguration
public class FilterConfig implements WebMvcConfigurer {
    @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowedOrigins("*")
				.allowCredentials(true);
	}
	private CorsConfiguration buildConfig() {
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
	     corsConfiguration.addAllowedOrigin("*");
    // 允许任何域名使用
	     corsConfiguration.addAllowedHeader("*");
	     // 允许任何头
		corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）

		corsConfiguration.setAllowCredentials(true);
		return corsConfiguration; }

	@Bean
	public CorsFilter corsFilter() {
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", buildConfig());
    	// 对接口配置跨域设置
		return new CorsFilter(source);
    }


}
