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
import com.river.rbac.bean.UserInfoBean;
import com.river.rbac.service.UserInfoService;

@RestController
@RequestMapping("/api/rbac")
public class UserInfoAction {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping(value = "/userInfo")
	public ResponseBean<?> getUserInfos(UserInfoBean bean){
		DSIdentification.setIdentification("db_rbac");
		ResponseBean<List<UserInfoBean>> res = new ResponseBean<>();
		List<UserInfoBean> beans = userInfoService.getAllUserInfo(bean);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(beans);
		res.setDesc("获取用户信息成功");
		return res;
	}
	@GetMapping(value = "/userInfo/{id}")
	public ResponseBean<?> getUserInfoById(@PathVariable("id") String id){
		DSIdentification.setIdentification("db_rbac");
		ResponseBean<UserInfoBean> res = new ResponseBean<>();
		UserInfoBean userInfoBean = userInfoService.getUserInfoById(id);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(userInfoBean);
		res.setDesc("获取用户信息成功");
		return res;
	}
	@PutMapping(value = "/userInfo/{id}")
	public ResponseBean<?> putUserInfoById(@PathVariable("id") String id,UserInfoBean bean){
		DSIdentification.setIdentification("db_rbac");
		ResponseBean<UserInfoBean> res = new ResponseBean<>();
		userInfoService.putUserInfo(bean);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("修改用户信息成功");
		return res;
	}
	@DeleteMapping(value = "/userInfo/{id}")
	public ResponseBean<?> deleteUserInfoById(@PathVariable("id") String id){
		DSIdentification.setIdentification("db_rbac");
		ResponseBean<UserBean> res = new ResponseBean<>();
		userInfoService.deleteUserInfo(id);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("删除用户信息成功");
		return res;
	}
	@PostMapping(value = "/userInfo")
	public ResponseBean<?> postUserInfoById(@RequestBody UserInfoBean bean){
		DSIdentification.setIdentification("db_rbac");
		ResponseBean<String> res = new ResponseBean<>();
		String id=userInfoService.postUserInfo(bean);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("添加用户信息成功");
		res.setData(id);
		return res;
	}
}
