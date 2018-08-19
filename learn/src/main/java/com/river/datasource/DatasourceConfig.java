package com.river.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = { "com.river" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class DatasourceConfig {
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "common.datasource")
	public DataSource springDataSource() {
		DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.type(DynamicDataSource.class);
        return builder.build();
	}

	@Primary
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory springSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);

		return factoryBean.getObject();
	}
}
