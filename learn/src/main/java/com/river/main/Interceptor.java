package com.river.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * SpringBoot配置器
 * @author River
 * @date 2018年9月4日
 */
@Configuration
@ComponentScan(basePackageClasses = App.class, useDefaultFilters = true)
public class Interceptor extends WebMvcConfigurationSupport{
	/* 
	 * 配置资源拦截器规则
	 */
	@Override
	protected  void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("static/**")会拦截static下的资源文件？
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
	/*
	 * 配置API拦截器规则
	 * addPathPatterns("/**"):拦截所有
	 * addPathPatterns("/**//*.htm"):拦截.hml请求
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new InterceptorConfige()).addPathPatterns("/**/*.htm").excludePathPatterns("/**/login.htm");
	   super.addInterceptors(registry);
	}	
	/* 
	 * 关闭请求地址后缀匹配
	 */
	 @Override
	 public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	        configurer.favorPathExtension(false);
	 }
}
