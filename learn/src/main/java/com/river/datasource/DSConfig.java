package com.river.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * 数据库配置文件
 * @author River
 * @date 2018年8月25日
 */
@Configuration
@MapperScan(basePackages = { "com.river" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class DSConfig {
	
	private static final Logger log = LoggerFactory.getLogger(DSConfig.class);
	
	@Bean(name = "dynamicDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource springDataSource() {
		log.info("正在请求数据源连接");
		DataSourceBuilder<?> builder = DataSourceBuilder.create();
		//加载动态数据源标识
        builder.type(DynamicDataSource.class);
        return builder.build();
	}
	
	@Primary
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory springSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);

		return factoryBean.getObject();
	}
}
