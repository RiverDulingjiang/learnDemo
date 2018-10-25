package com.river.learn.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.basic.UserSession;
import com.river.learn.basic.mapper.DataBaseMapper;
import com.river.learn.web.service.TestService;
import com.river.rbac.bean.UserBean;
import com.river.rbac.service.DepartmentService;

@Controller
@RequestMapping("/api/test")
public class MytestAction {
	
	 @Autowired
	 private DataBaseMapper mapper;
	 @Autowired
	 private DepartmentService DepartmentService;
	
	 @RequestMapping("/home.htm")
	 @ResponseBody
	 @RequiresRoles("leader")
	 public String home(){
		 ResponseBean<String> bean = new ResponseBean<>();
		 bean.setData("Hello World!");
	     return bean.toJson();
	 }
	 
	 @RequestMapping("/activiti.htm")
	 @ResponseBody
	 public String activiti() {
	       return "Hello World!";
	 }
	 
	 @RequestMapping("/rbac.htm")
	 @ResponseBody
	 @RequiresPermissions("")
	 public String rbac() {
		 UserBean bean = new UserBean();
		 bean.setAccount("zhangsan");
		 ResponseBean<UserBean> resultBean = new ResponseBean<>();
		 resultBean.setCode(Constant.RESULT_SUCCESS);
		 resultBean.setData(bean);
	     return new Gson().toJson(resultBean);
	 }
	 
	 @RequestMapping("/exception.htm")
	 @ResponseBody
	 public String exception() {
		 Integer aString = 1/0;
	      return aString.toString();
	 }
	 
	 @RequestMapping("/tiaozhuan.htm")
	 public ModelAndView tiaozhuan() {
		 ModelAndView view = new ModelAndView("/403.html");
	      return view;
	 }
	 @RequestMapping("/tiaozhuan1.htm")
	 @ResponseBody
	 public String tiaozhuan1() {
		
	      return "redirect:/403.html";
	 }
	 @RequestMapping("/zhujie.htm")
	 @ResponseBody
	 public String zhujie(@UserSession(Constant.LOGIN_USER_SESSION)UserBean bean) {
		return new Gson().toJson(bean);
	 }
	 @RequestMapping("/deptId.htm")
	 @ResponseBody
	 public String deptId(String start) {
		String dept = DepartmentService.getAllNodes(start);
		System.out.println(dept);
		return dept;
	 }
	 @RequestMapping("/testService.htm")
	 @ResponseBody
	 public String testService(String startId) {
		 String flag =new DepartmentService().getAllNodes(startId);
		 return flag;
	 }
}
