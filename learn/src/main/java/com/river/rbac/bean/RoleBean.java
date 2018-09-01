package com.river.rbac.bean;

import java.util.List;

/**
 * 角色实体类
 * @author River
 * @date 2018年8月31日
 */
public class RoleBean {
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 角色唯一标识
	 */
	private String idCard;
	/**
	 * 角色描述
	 */
	private String desc;
	/**
	 * 角色权限
	 */
	private List<PermissionBean> permisssionBeans;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<PermissionBean> getPermisssionBeans() {
		return permisssionBeans;
	}
	public void setPermisssionBeans(List<PermissionBean> permisssionBeans) {
		this.permisssionBeans = permisssionBeans;
	}
	
}
