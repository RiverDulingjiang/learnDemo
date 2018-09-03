package com.river.main;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(value = {"com.river", "com.river.learn" })
public class App extends WebMvcConfigurationSupport{
	
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
    
//    @Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
//		super.addResourceHandlers(registry);
//	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**/*.htm").excludePathPatterns("/**/login.htm","/cas.htm");
//	    super.addInterceptors(registry);
//	}
}
