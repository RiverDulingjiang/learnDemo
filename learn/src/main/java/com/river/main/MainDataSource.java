package com.river.main;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.river.basic.Constant;
import com.river.datasource.DBProperties;
import com.river.learn.web.bean.DataBaseBean;
import com.river.learn.web.service.DataBaseService;

/**
 * 项目启动后立即执行该方法：优先级为一
 * @author River
 * @date 2018年9月3日
 */
@Component
@Order(value = 1)
public class MainDataSource implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(MainDataSource.class);
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Autowired
	private DataBaseService dataBaseService;
	/* 
	 * 项目启动后优先执行的方法
	 * --立即注册一个默认的数据库
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//项目启动立即启动主数据库信息
		Properties p = new Properties();
		p.setProperty(DBProperties.URL,url);
		p.setProperty(DBProperties.USER_NAME,username);
		p.setProperty(DBProperties.PASSWORD, password);
		p.setProperty(DBProperties.DRVIER_CLASS_NAME, driverClassName);
		DBProperties.setDBmap(Constant.DATABASIC_MAIN,p);
		log.info("默认数据源<"+Constant.DATABASIC_MAIN+">已启动");
		
		DataBaseBean bean =dataBaseService.getDataBaseByFlag("_rbac");
		dataBaseService.addDataSource(bean);
	}

}
