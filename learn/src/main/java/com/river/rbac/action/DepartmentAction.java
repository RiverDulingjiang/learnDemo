package com.river.rbac.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.basic.UserSession;
import com.river.rbac.bean.DepartmentBean;
import com.river.rbac.bean.UserBean;
import com.river.rbac.service.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentAction {
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * @Description: 获取部门树
	 * @date 2018年10月10日
	 * @return
	 */
	@GetMapping(value = "/dept/tree")
	public ResponseBean<?> getTree(){
		ResponseBean<List<?>> res = new ResponseBean<>();
		List<HashMap<String, Object>> tree= departmentService.getTree("root");
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(tree);
		res.setDesc("获取子节点唯一标识符成功");
		return res;
	}

	/**
	 * @Description: 获取部门节点以及所有子节点标识
	 * @date 2018年10月8日
	 * @param code 节点标识
	 * @return
	 */
	@GetMapping(value = "/dept/childNodes/{code}")
	public ResponseBean<?> getChildren(@PathVariable("code") String code){
		ResponseBean<String> res = new ResponseBean<>();
		String idCards = departmentService.getAllNodes(code);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(idCards);
		res.setDesc("获取子节点唯一标识符成功");
		return res;
	}
	/**
	 * @Description: 获取父节点以及子节点内容
	 * @date 2018年10月8日
	 * @param parent
	 * @return
	 */
	@GetMapping(value = "/dept/children/{parent}")
	public ResponseBean<?> getChildNodes(@PathVariable("parent") String parent){
		ResponseBean<List<DepartmentBean>> res = new ResponseBean<>();
		List<DepartmentBean> bean = departmentService.getChildNodes(parent);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(bean);
		res.setDesc("获取子节点内容成功");
		return res;
	}
	
	/**
	 * @Description: 根据ID获取部门
	 * @date 2018年10月10日
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/dept/{id}")
	public ResponseBean<?> getDeptById(@PathVariable("id") String id){
		ResponseBean<DepartmentBean> res = new ResponseBean<>();
		DepartmentBean bean = departmentService.getDeptById(id);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(bean);
		res.setDesc("获取部门信息");
		return res;
	}
	@PostMapping(value = "/dept")
	public ResponseBean<?> postDept(@UserSession(Constant.LOGIN_USER_SESSION)UserBean user,@RequestBody DepartmentBean bean){
		bean.setCreateBy(user.getId());
		ResponseBean<String> res = new ResponseBean<>();
		String id = departmentService.postDept(bean);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setData(id);
		res.setDesc("新增部门信息");
		return res;
	}
	/**
	 * @Description: 修改部门信息
	 * @date 2018年10月10日
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/dept")
	public ResponseBean<?> putDept(@UserSession(Constant.LOGIN_USER_SESSION)UserBean user,@RequestBody DepartmentBean bean){
		bean.setUpdateBy(user.getId());
		ResponseBean<String> res = new ResponseBean<>();
		departmentService.putDept(bean);
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("修改部门信息");
		return res;
	}
}
