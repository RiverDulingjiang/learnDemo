package com.river.datasource;

/**
 * 数据库实体类
 * @author River
 * @date 2018年9月11日
 */
public class DBBean {
	/**
	 * 数据源唯一标识
	 */
	private String flag;
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
}
