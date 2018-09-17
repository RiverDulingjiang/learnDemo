package com.river.main;

import java.nio.charset.Charset;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(value = {"com.river", "com.river.learn" })
public class App{
	
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
   
}
