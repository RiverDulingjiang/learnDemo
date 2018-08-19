package com.river.main;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(value = {"com.river", "com.river.learn" })
public class App {
	
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
}
