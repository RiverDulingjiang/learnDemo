package com.river.rbac.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.datasource.DSIdentification;
import com.river.rbac.bean.UserBean;
import com.river.rbac.service.ShiroService;

/**
 * 基于角色的权限控制
 * @author River
 * @date 2018年9月10日
 */
@RestController
@RequestMapping("/api/rbac")
public class UserAction {
	@Autowired
	ShiroService shiroService;
	
	/**
	 * @Description: 获取所有用户
	 * @date 2018年9月10日
	 * @return
	 */
	@GetMapping(value = "/user")
	public ResponseBean<?> getAllUser(){
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
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
	@GetMapping(value = "/user/{id}")
	public ResponseBean<?> getUserById(@PathVariable("id") String id){
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
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
	@PutMapping(value = "/user/{id}")
	public ResponseBean<?> putUserById(@PathVariable("id") String id,UserBean bean){
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		ResponseBean<UserBean> res = new ResponseBean<>();
		shiroService.updateUserById(bean);
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
	@DeleteMapping(value = "/user/{id}")
	public ResponseBean<?> deleteUserById(@PathVariable("id") String id){
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		ResponseBean<UserBean> res = new ResponseBean<>();
		shiroService.deleteUserById(id);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("删除用户信息成功");
		return res;
	}
	
	/**
	 * @Description: 添加用户
	 * @date 2018年9月11日
	 * @param userBean
	 * @return
	 */
	@PostMapping(value = "/user")
	public ResponseBean<?> postUserById(@RequestBody UserBean userBean){
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		ResponseBean<UserBean> res = new ResponseBean<>();
		shiroService.postUser(userBean);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("添加用户信息成功");
		return res;
	}
}
