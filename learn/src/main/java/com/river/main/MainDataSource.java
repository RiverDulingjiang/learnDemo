package com.river.main;

import java.util.Properties;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.river.datasource.DBProperties;

/**
 * 项目启动后立即执行该方法：优先级为一
 * @author River
 * @date 2018年9月3日
 */
@Component
@Order(value = 1)
public class MainDataSource implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Properties p = new Properties();
		p.setProperty(DBProperties.URL, "jdbc:mysql://localhost:3306/db_learn_rbac?useUnicode=true&characterEncoding=utf8");
		p.setProperty(DBProperties.USER_NAME, "root");
		p.setProperty(DBProperties.PASSWORD, "123");
		p.setProperty(DBProperties.DRVIER_CLASS_NAME, "com.mysql.jdbc.Driver");
		DBProperties.setDBmap("_common", p);		
	}

}
