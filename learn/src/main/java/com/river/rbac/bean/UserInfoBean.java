package com.river.rbac.bean;

/**
 * 用户详情
 * @author River
 * @date 2018年9月12日
 */
public class UserInfoBean {
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 账户
	 */
	private String account;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private Boolean sex;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 用户状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
