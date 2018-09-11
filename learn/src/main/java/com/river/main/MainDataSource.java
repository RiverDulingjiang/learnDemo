package com.river.main;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.river.datasource.DBBean;
import com.river.datasource.DBGroup;
import com.river.datasource.DBProperties;
import com.river.rbac.MyShiroRealm;

/**
 * 项目启动后立即执行该方法：优先级为一
 * @author River
 * @date 2018年9月3日
 */
@Component
@Order(value = 1)
public class MainDataSource implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(MainDataSource.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//项目启动立即启动数据库信息
		List<DBBean> list =DBGroup.getDBBeans();
		DBGroup.putDataSource(list);
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<list.size();i++){
			builder.append(list.get(i).getFlag());
		}
		log.info("数据源"+builder+"已启动");		
	}

}
