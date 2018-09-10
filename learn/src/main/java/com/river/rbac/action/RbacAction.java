package com.river.rbac.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.rbac.bean.UserBean;
import com.river.rbac.service.ShiroService;

/**
 * 基于角色的权限控制
 * @author River
 * @date 2018年9月10日
 */
@Controller
@RequestMapping("/rbac")
public class RbacAction {
	@Autowired
	ShiroService shiroService;
	
	/**
	 * @Description: 获取所有用户
	 * @date 2018年9月10日
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBean<?> getAllUser(){
		ResponseBean<List<UserBean>> res = new ResponseBean<>();
		List<UserBean> beans = shiroService.getAllUser();
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(beans);
		res.setDesc("获取所有用户信息成功");
		return res;
	}
	
	/**
	 * @Description: 获取某一用户
	 * @date 2018年9月10日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBean<?> getUserById(@PathVariable String id){
		ResponseBean<UserBean> res = new ResponseBean<>();
		UserBean userBean = shiroService.getUserById(id);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(userBean);
		res.setDesc("获取用户信息成功");
		return res;
	}
	
	/**
	 * @Description: 修改某一用户
	 * @date 2018年9月10日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseBean<?> putUserById(@PathVariable String id){
		ResponseBean<UserBean> res = new ResponseBean<>();
		shiroService.updateUserById(id);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("修改用户信息成功");
		return res;
	}
	/**
	 * @Description: 删除某一用户
	 * @date 2018年9月10日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseBean<?> deleteUserById(@PathVariable String id){
		ResponseBean<UserBean> res = new ResponseBean<>();
		shiroService.deleteUserById(id);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("修改用户信息成功");
		return res;
	}
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseBean<?> postUserById(UserBean userBean){
		ResponseBean<UserBean> res = new ResponseBean<>();
		shiroService.postUser(userBean);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("修改用户信息成功");
		return res;
	}
}
