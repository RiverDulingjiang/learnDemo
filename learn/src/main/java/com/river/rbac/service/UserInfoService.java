package com.river.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.basic.Tools;
import com.river.rbac.bean.UserInfoBean;
import com.river.rbac.mapper.UserInfoMapper;

/**
 * 用户详情
 * @author River
 * @date 2018年9月12日
 */
@Service
public class UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/**
	 * @Description: 获取条件用户信息
	 * @date 2018年9月12日
	 * @return
	 */
	public List<UserInfoBean> getAllUserInfo(UserInfoBean bean){
		List<UserInfoBean> beans=userInfoMapper.gets(bean);
		return beans;
	}
	/**
	 * @Description: 获取某一用户
	 * @date 2018年9月12日
	 * @param id
	 * @return
	 */
	public UserInfoBean getUserInfoById(String id){
		return userInfoMapper.get(id);
	}
	/**
	 * @Description: 删除某一用户
	 * @date 2018年9月12日
	 * @param id
	 */
	public void deleteUserInfo(String id){
		userInfoMapper.delete(id);
	}
	/**
	 * @Description: 添加某一用户
	 * @date 2018年9月12日
	 * @param bean
	 */
	public String postUserInfo(UserInfoBean bean){
		String id =Tools.randomUUID();
		bean.setId(id);
		bean.setCreateTime(Tools.dateToString(new Date()));
		bean.setUpdateTime(Tools.dateToString(new Date()));
		userInfoMapper.post(bean);
		return id;
	}
	/**
	 * @Description: 修改某一用户
	 * @date 2018年9月12日
	 * @param bean
	 */
	public void putUserInfo(UserInfoBean bean){
		bean.setUpdateTime(Tools.dateToString(new Date()));
		userInfoMapper.put(bean);
	}
}
