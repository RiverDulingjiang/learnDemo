package com.river.rbac.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.rbac.ShiroSessionListener;

@Controller
@RequestMapping("/api/shiro")
public class ShiroAction {
	
	/**
	 * @Description: 统计在线人数
	 * @date 2018年10月8日
	 * @return
	 */
	@RequestMapping("/onlineCount")
	@ResponseBody
	public ResponseBean<?> onlineCount(){
		ResponseBean<Integer> res = new ResponseBean<>();
		int index = ShiroSessionListener.getSessionCount();
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("统计在线人数");
		res.setData(index);
		return res;		
	}
}
