package com.river.rbac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.basic.Tools;
import com.river.rbac.bean.PermissionBean;
import com.river.rbac.bean.RoleBean;
import com.river.rbac.bean.UserBean;
import com.river.rbac.mapper.PermissionMapper;
import com.river.rbac.mapper.RoleMapper;
import com.river.rbac.mapper.UserMapper;

/**
 * 权限服务层
 * @author River
 * @date 2018年9月5日
 */
@Service
public class ShiroService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	
	/**
	 * @Description: 查询用户权限
	 * @date 2018年9月5日
	 * @param account
	 * @return
	 */
	public UserBean getUser(String account){
		UserBean bean = new UserBean();
		bean.setAccount(account);
		List<UserBean> beans=userMapper.get(bean);
		if(beans.size()==1){			
			List<RoleBean> roleBeans =roleMapper.getRoles(beans.get(0).getAccount());
			beans.get(0).setRoleBeans(roleBeans);
			for(RoleBean roleBean:roleBeans){
				List<PermissionBean> permissionBeans = permissionMapper.getPermissions(roleBean.getIdCard());
				roleBean.setPermisssionBeans(permissionBeans);
			}
			return beans.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * @Description: 获取所有的用户
	 * @date 2018年9月10日
	 * @return
	 */
	public List<UserBean> getAllUser(){
		UserBean bean = new UserBean();
		List<UserBean> beans=userMapper.get(bean);
		return beans;
	}
	/**
	 * @Description: 根据ID获取用户
	 * @date 2018年9月10日
	 * @param id
	 * @return
	 */
	public UserBean getUserById(String id){
		UserBean bean = new UserBean();
		bean.setId(id);
		List<UserBean> beans=userMapper.get(bean);
		if(beans!=null){
			UserBean userBean = beans.get(0);
			return userBean;
		}else {
			return null;
		}
	}

	/**
	 * @Description: 修改用户信息
	 * @date 2018年9月10日
	 * @param id
	 */
	public void updateUserById(String id) {
		UserBean bean = new UserBean();
		bean.setId(id);
		userMapper.put(bean);
	}

	/**
	 * @Description: 删除用户信息
	 * @date 2018年9月10日
	 * @param id
	 */
	public void deleteUserById(String id) {
		userMapper.delete(id);
	}
	/**
	 * @Description: 添加用户信息
	 * @date 2018年9月10日
	 * @param bean
	 */
	public void postUser(UserBean bean) {
		bean.setId(Tools.randomUUID());
		userMapper.post(bean);
	}
}
