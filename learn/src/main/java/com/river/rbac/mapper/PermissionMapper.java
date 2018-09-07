package com.river.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.river.rbac.bean.PermissionBean;

/**
 * 权限映射表
 * @author River
 * @date 2018年9月5日
 */
public interface PermissionMapper {
	/**
	 * 根据角色获取权限
	 * @Description: TODO
	 * @date 2018年9月5日
	 * @param role
	 * @return
	 */
	public List<PermissionBean> getPermissions(@Param("role")String role);
}
