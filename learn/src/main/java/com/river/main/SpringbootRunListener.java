package com.river.main;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


public class SpringbootRunListener implements SpringApplicationRunListener{

	@Override
	public void starting() {
		System.out.println("Project is running!");
	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void started(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void running(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		// TODO Auto-generated method stub
		
	}

}
