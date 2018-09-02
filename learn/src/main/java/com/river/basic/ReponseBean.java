package com.river.basic;

/**
 * 响应数据包装实体类
 * @author Administrator
 *
 */
public class ReponseBean<T> {
	/**
	 * 返回状态
	 */
	private Integer code;
	/**
	 * 返回描述
	 */
	private String desc;
	/**
	 * 返回数据
	 */
	private T data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
