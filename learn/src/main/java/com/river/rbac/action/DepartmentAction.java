package com.river.rbac.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.rbac.service.DepartmentService;

@RestController
@RequestMapping("/api/shiro")
public class DepartmentAction {
	
	@Autowired
	private DepartmentService departmentService;

	@GetMapping(value = "/dept/children/{idCard}")
	public ResponseBean<?> getChildren(String idCard){
		ResponseBean<String> res = new ResponseBean<>();
		String idCards = departmentService.getChildPointId(idCard);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(idCards);
		res.setDesc("获取子节点Code成功");
		return res;
	}
}
