package com.river.rbac.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.basic.Constant;
import com.river.basic.Tools;
import com.river.datasource.DSIdentification;
import com.river.rbac.bean.DepartmentBean;
import com.river.rbac.mapper.DepartmentMapper;

/**
 * 部门服务层
 * @author River
 * @date 2018年10月8日
 */
@Service
public class DepartmentService {
	
	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	/**
	 * @Description: 获取该节点以及以下节点的ID
	 * @date 2018年9月20日
	 * @param startId
	 * @return
	 */
	public String getAllNodes(String startId) {
		String childIds = "";
		// 获得所有部门数据
		log.info("正在加载部门数据..........");
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		List<HashMap<String, Object>> deptList = departmentMapper.getDeptList();
		// 递归出所有子节点id并拼接成字符串
		for (HashMap<String, Object> map : deptList) {
			String code = (String)map.get("code");
			if (code.equals(startId)) {
				childIds += (code + ",");
				childIds = getChildId(code, childIds, deptList);
			}
		}
		return childIds;
	}
	/**
	 * @Description: 查询子节点
	 * @date 2018年10月8日
	 * @param code 父节点id
	 * @param childIds 子节点字符串集合
	 * @param deptList 所有数据
	 * @return
	 */
	private String getChildId(String code, String childIds, List<HashMap<String, Object>> deptList) {
		for (HashMap<String, Object> map : deptList) {
			String pcode = (String) map.get("parent");
			String ccode = (String) map.get("code");
			if (code.equals(pcode)) {
				childIds += (ccode + ",");
				childIds = getChildId(ccode, childIds, deptList);
			}
		}
		return childIds;
	}	
	/**
	 * @Description: 加载部门树
	 * @date 2018年10月8日
	 * @param parent
	 * @return
	 */
	public List<HashMap<String, Object>> getTree(String parent){
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		List<HashMap<String, Object>> deptList = departmentMapper.getDeptList();
		HashMap<String, Object> rootNode =null;
		List<HashMap<String, Object>> root =new ArrayList<>();
		List<HashMap<String, Object>> children =new ArrayList<>();
		for (HashMap<String, Object> map : deptList) {
			String code = (String)map.get("code");
			if (code!=null && code.equals(parent)) {
				root.add(map);
				rootNode =map;
				break;
			}
		}
		for (HashMap<String, Object> map : deptList) {
			String code = (String)map.get("code");
			String pcode = (String)map.get("parent");
			if(pcode!=null && pcode.equals(parent)){
				map.put("children", getChildren(code,map,deptList));
				children.add(map);
			}
		}
		rootNode.put("children", children);
		//layui字段，默认展开根节点
		rootNode.put("spread", true);
		return root;
	}
	/**
	 * @Description: 获取树下面的子节点
	 * @date 2018年10月8日
	 * @param parent
	 * @param map
	 * @param deptList
	 * @return
	 */
	private List<?> getChildren(String parent,HashMap<String, Object> map,List<HashMap<String, Object>> deptList){
		List<HashMap<String, Object>> children =new ArrayList<>();
		for (HashMap<String, Object> hashMap: deptList) {
			String code = (String)hashMap.get("code");
			String pcode = (String)hashMap.get("parent");			
			if(pcode!=null && pcode.equals(parent)){
				hashMap.put("children", getChildren(code,map,deptList));
				children.add(hashMap);
			}
		}
		return children;
	}	
	/**
	 * @Description: 组装新的部门实体类
	 * @date 2018年10月8日
	 * @param parent
	 * @return
	 */
	public List<DepartmentBean> getChildNodes(String parent) {
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		List<DepartmentBean> deptList = departmentMapper.getChildDept(parent);
		DepartmentBean bean = new DepartmentBean();
		List<DepartmentBean> list =new ArrayList<DepartmentBean>();
		List<DepartmentBean> list2 =new ArrayList<DepartmentBean>();
		for(DepartmentBean dept:deptList){
			if(dept.getCode().equals(parent)){
				bean = dept;
			}else {
				list.add(dept);
			}			
		}
		bean.setChildren(list);
		list2.add(bean);
		return list2;
	}
	/**
	 * @Description: 通过id查询部门
	 * @date 2018年10月10日
	 * @param id
	 * @return
	 */
	public DepartmentBean getDeptById(String id) {
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		DepartmentBean dept = departmentMapper.getDeptById(id);
		return dept;
	}
	/**
	 * @Description: 新增部门信息
	 * @date 2018年10月10日
	 * @param bean
	 * @return
	 */
	public String postDept(DepartmentBean bean) {
		String id = Tools.randomUUID();
		bean.setId(id);
		bean.setCode(Tools.randomUUID());
		bean.setDelFlag(true);
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		departmentMapper.postDept(bean);
		return id;
	}
	
	public void putDept(DepartmentBean bean) {
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		departmentMapper.putDept(bean);
	}
}
