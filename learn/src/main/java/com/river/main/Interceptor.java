package com.river.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.river.rbac.LoginInterceptor;

/**
 * 配置拦截器
 * @author River
 * @date 2018年9月4日
 */
@Configuration
@ComponentScan(basePackageClasses = App.class, useDefaultFilters = true)
public class Interceptor extends WebMvcConfigurationSupport{
	/* 
	 * 配置资源拦截器
	 */
	@Override
	protected  void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("static/**")会拦截static下的资源文件？
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
	/*
	 * 配置API拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**/*.htm").excludePathPatterns("/**/login.htm","/cas.htm");
	   super.addInterceptors(registry);
	}
}
