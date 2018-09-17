package com.river.main;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.river.basic.UserArgumentResolver;

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
	    registry.addInterceptor(new InterceptorConfige()).addPathPatterns("/**").excludePathPatterns("/**/public/**","/*.html","/**/login");
	   super.addInterceptors(registry);
	}	
	/* 
	 * 关闭请求地址后缀匹配
	 */
	 @Override
	 public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	        configurer.favorPathExtension(false);
	 }
	 /*
	  * 注册UserArgumentResolver的参数分解器
	  */
	 @Override
	 protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	    argumentResolvers.add(new UserArgumentResolver());
	    super.addArgumentResolvers(argumentResolvers);
	 }
	 
	/**
	 * @Description: 中文浏览器乱码问题
	 * @date 2018年9月17日
	 * @return
	 */
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(responseBodyConverter());
	}
}
