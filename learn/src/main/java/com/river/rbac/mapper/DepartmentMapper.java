package com.river.rbac.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.river.rbac.bean.DepartmentBean;

public interface DepartmentMapper {

	/**
	 * @Description: 获取所有部门数据
	 * @date 2018年10月8日
	 * @return
	 */
	public List<HashMap<String, Object>> getDeptList();
	/**
	 * @Description:  获取子部门
	 * @date 2018年10月8日
	 * @param parent 父节点ID
	 * @return
	 */
	public List<DepartmentBean> getChildDept(@Param("parent")String parent);
	/**
	 * @Description: 获取部门信息
	 * @date 2018年10月9日
	 * @param id
	 * @return
	 */
	public DepartmentBean getDeptById(@Param("id") String id);
	/**
	 * @Description: 新增部门信息
	 * @date 2018年10月10日
	 * @param bean
	 */
	public void postDept(DepartmentBean bean);
	
	/**
	 * @Description: 修改部门信息
	 * @date 2018年10月10日
	 * @param bean
	 */
	public void putDept(DepartmentBean bean);

}
