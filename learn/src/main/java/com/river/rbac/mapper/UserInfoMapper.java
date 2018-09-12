package com.river.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.river.rbac.bean.UserInfoBean;

public interface UserInfoMapper {
	/**
	 * @Description: 增加用户信息
	 * @date 2018年8月31日
	 * @param bean
	 */
	public void post(UserInfoBean bean);
	/**
	 * @Description: 查询用户信息
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	public List<UserInfoBean> gets(UserInfoBean bean);
	/**
	 * @Description: 获取某一用户
	 * @date 2018年9月12日
	 * @param id
	 * @return
	 */
	public UserInfoBean get(@Param("id")String id);
	/**
	 * @Description: 修改用户信息
	 * @date 2018年9月10日
	 * @param bean
	 */
	public void put(UserInfoBean bean);
	/**
	 * @Description: 删除用户信息
	 * @date 2018年9月10日
	 * @param id
	 */
	public void delete(@Param("id")String id);
	
}
