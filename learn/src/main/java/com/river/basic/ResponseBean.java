package com.river.basic;

import com.google.gson.Gson;

/**
 * 响应数据包装实体类
 * @author Administrator
 *
 */
public class ResponseBean<T> {
	/**
	 * 返回状态
	 */
	private Integer code = 0;
	/**
	 * 返回描述
	 */
	private String desc ="success";
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
	public String toJson(){
		return new Gson().toJson(this);
	}
}
