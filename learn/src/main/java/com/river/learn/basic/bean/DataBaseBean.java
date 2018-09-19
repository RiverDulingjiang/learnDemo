package com.river.learn.basic.bean;

/**
 * 数据源实体管理类
 * @author River
 * @date 2018年9月17日
 */
public class DataBaseBean extends BaseBean{
	/**
	 * 数据源别名
	 */
	private String name;
	/**
	 * 数据库标识
	 */
	private String flag;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 数据源url地址
	 */
	private String url;
	/**
	 * 数据源账户
	 */
	private String username;
	/**
	 * 数据源密码
	 */
	private String password;
	/**
	 * 数据源驱动
	 */
	private String driverClassName;
	/**
	 * 数据源状态
	 */
	private Integer status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
