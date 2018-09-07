package com.river.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.river.rbac.bean.RoleBean;

/**
 * 角色映射表
 * @author River
 * @date 2018年9月5日
 */
public interface RoleMapper {
	/**
	 * @Description: 根据用户获取角色
	 * @date 2018年9月5日
	 * @param user
	 * @return
	 */
	public List<RoleBean> getRoles(@Param("user")String user);
}
