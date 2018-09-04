package com.river.rbac.bean;

import java.util.List;

/**
 * 用户实体类
 * @author River
 * @date 2018年8月31日
 */
public class UserBean {
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 用户名
	 */
	private String account;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户状态
	 */
	private Integer status;
	/**
	 * 记住我
	 */
	private Boolean doRememberMe;
	/**
	 * 用户角色
	 */
	private List<RoleBean> roleBeans;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Boolean getDoRememberMe() {
		return doRememberMe;
	}
	public void setDoRememberMe(Boolean doRememberMe) {
		this.doRememberMe = doRememberMe;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleBean> getRoleBeans() {
		return roleBeans;
	}
	public void setRoleBeans(List<RoleBean> roleBeans) {
		this.roleBeans = roleBeans;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
