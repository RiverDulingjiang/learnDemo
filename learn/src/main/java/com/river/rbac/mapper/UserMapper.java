package com.river.rbac.mapper;

import java.util.List;

import com.river.rbac.bean.UserBean;

/**
 * 用户数据层
 * @author River
 * @date 2018年8月31日
 */
public interface UserMapper {
	/**
	 * @Description: 增加用户
	 * @date 2018年8月31日
	 * @param bean
	 */
	public void post(UserBean bean);
	/**
	 * @Description: 查询用户
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	public List<UserBean> get(UserBean bean);
}
