package com.river.rbac.bean;

/**
 * 部门实体类
 * @author River
 * @date 2018年9月20日
 */
public class DepartmentBean{
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 部门简称
	 */
	private String sname;
	/**
	 * 部门唯一标识
	 */
	private String code;
	/**
	 * 父部门唯一标识
	 */
	private String parent;
	/**
	 * 部门状态
	 */
	private Integer status;
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
