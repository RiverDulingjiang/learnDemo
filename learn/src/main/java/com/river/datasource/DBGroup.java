package com.river.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 数据源信息库
 * @author River
 * @date 2018年9月11日
 */
public class DBGroup {
	
	/**
	 * @Description: 加入数据源
	 * @date 2018年9月11日
	 * @param beans
	 */
	public static void putDataSource(List<DBBean> beans){
		if(beans!=null){
			for(DBBean bean:beans){
				Properties p = new Properties();
				p.setProperty(DBProperties.URL,bean.getUrl());
				p.setProperty(DBProperties.USER_NAME, bean.getUsername());
				p.setProperty(DBProperties.PASSWORD, bean.getPassword());
				p.setProperty(DBProperties.DRVIER_CLASS_NAME, bean.getDriverClassName());
				DBProperties.setDBmap(bean.getFlag(),p);
			}
		}
	}
		
	public static List<DBBean> getDBBeans(){
		List<DBBean> list =new ArrayList<>();
		DBBean bean = new DBBean();
		bean.setFlag("db_rbac");
		bean.setUrl("jdbc:mysql://localhost:3306/db_learn_rbac?useUnicode=true&characterEncoding=utf8");
		bean.setUsername("root");
		bean.setPassword("123");
		bean.setDriverClassName("com.mysql.jdbc.Driver");
		list.add(bean);
		return list;
	}
}
