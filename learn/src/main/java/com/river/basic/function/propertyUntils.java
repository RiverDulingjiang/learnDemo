package com.river.basic.function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 获取配置文件内容类
 * @author River
 * @date 2018年9月17日
 */
@Configuration  
@ConfigurationProperties(prefix = "config")  
@PropertySource("classpath:config.properties")
public class propertyUntils {
	/**
	 * 环境：0测试环境；1生产环境
	 */
	private static int environment;

	public static int getEnvironment() {
		return environment;
	}
}
