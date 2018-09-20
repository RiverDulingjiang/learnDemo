package com.river.rbac.bean;

/**
 * 权限实体类
 * @author River
 * @date 2018年8月31日
 */
public class PermissionBean {
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限唯一标识
	 */
	private String code;
	/**
	 * 路径
	 */
	private String url;
	/**
	 * 接口名称
	 */
	private String action;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
