package com.river.datasource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.river.basic.Constant;
import com.river.basic.exception.DatabaseException;

/**
 * 动态数据源
 * @author River
 * @date 2018年8月29日
 */
public class DynamicDataSource extends DataSource {
	private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
	/* 
	 * 动态连接数据库
	 */
	@Override
	public Connection getConnection() {

		// 1.获取当前线程数据库标识
		String identification = DSIdentification.getIdentification();
		if (identification == null || identification == "") {
			//若没有设置数据库，则默认选择一个数据库
			DSIdentification.setIdentification(Constant.DATABASIC_MAIN);
			identification = DSIdentification.getIdentification();
		}
		DSIdentification.remove();
		// 2.获取数据源
		DataSource ds = DSManager.instance().getDDS(identification);
		
		// 3.如果数据源不存在则创建
		if (ds == null) {
			log.info("该数据源已被关闭，重新建立连接.......："+identification);
			try {
				// 创建新的数据源
				DataSource newDDS = createDS(identification);
				// 将新的数据源放入数据源管理器中
				DSManager.instance().addDDS(identification, newDDS);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}else{
			log.info("该数据源数据活跃线程，正在连接.......："+identification);
		}		
		// 4.获取新的数据源
		ds = DSManager.instance().getDDS(identification);

		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 创建一个新的数据源
	 * @date 2018年8月25日
	 * @param identification 新数据源标识
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private DataSource createDS(String identification) throws IllegalArgumentException, IllegalAccessException {

		DataSource ds = new DataSource();
		PoolProperties property = new PoolProperties();
		// 获取PoolProperties类的字段
		Field[] pfields = PoolProperties.class.getDeclaredFields();
		for (Field f : pfields) {
			f.setAccessible(true);
			// 获取连接池的默认设置
			Object value = f.get(this.getPoolProperties());
			try {
				// 将连接池的默认设置复制给 配置类
				f.set(property, value);
			} catch (Exception e) {
				// 有一些static final的属性不能修改。忽略。
				// log.info("Set value fail. attr name:" + f.getName());
				continue;
			}
		}
		// 将连接池的默认设置放入数据源中
		ds.setPoolProperties(property);

		/*
		 * //获取配置文件中的url String urlFormat = this.getUrl(); //设置数据库名称和IP String
		 * url = String.format(urlFormat,
		 * ProjectDBMgr.instance().getDBIP(identification),
		 * ProjectDBMgr.instance().getDBName(identification)); ds.setUrl(url);
		 */
		if(DBProperties.getDBmap(identification)!=null){
			ds.setUrl(DBProperties.getUrl(identification));
			ds.setUsername(DBProperties.getUsername(identification));
			ds.setPassword(DBProperties.getPassword(identification));
			ds.setDriverClassName(DBProperties.getDriverClassName(identification));
		}else{
			throw new DatabaseException("该数据源未被持久化！无法连接该数据源");
		}
		

		return ds;
	}
}
