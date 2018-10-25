package com.river.rbac.bean;

import java.util.Date;
import java.util.List;

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
	 * 父部门名称（冗余）
	 */
	private String pname;
	/**
	 * 是否叶子节点
	 */
	private Boolean doLeaf;
	/**
	 * 逻辑删除标志
	 */
	private Boolean delFlag;
	
	private String createBy;
	private Date createTime;
	private String updateBy;
	private String updateTime;
	
	private List<DepartmentBean> children;
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
	public List<DepartmentBean> getChildren() {
		return children;
	}
	public void setChildren(List<DepartmentBean> children) {
		this.children = children;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Boolean getDoLeaf() {
		return doLeaf;
	}
	public void setDoLeaf(Boolean doLeaf) {
		this.doLeaf = doLeaf;
	}
	public Boolean getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
