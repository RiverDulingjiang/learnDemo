package com.river.rbac.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.basic.Constant;
import com.river.datasource.DSIdentification;
import com.river.rbac.mapper.DepartmentMapper;

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
	public String getChildPointId(String startId) {
		String childIds = "";
		// 获得所有部门数据
		log.info("正在请求数据库数据..........");
		DSIdentification.setIdentification(Constant.DATABASIC_RBAC);
		List<HashMap<String, Object>> deptList = departmentMapper.selectDeptList();
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
}
