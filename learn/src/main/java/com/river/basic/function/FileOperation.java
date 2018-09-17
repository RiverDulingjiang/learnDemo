package com.river.basic.function;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;

/**
 * 文件操作类
 * @author River
 * @date 2018年9月17日
 */
@Controller
@RequestMapping("/function")
public class FileOperation {
	
	/**
	 * @Description: 上传文件
	 * @date 2018年9月14日
	 * @param file 参数名与前端参数名一致
	 * @param session
	 * @param param 参数名与前端参数名一致
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile[] file ,HttpSession session,String param) {
		ResponseBean<String> res = new ResponseBean<>();
		String path =null;
		path = session.getServletContext().getRealPath("/files");
		if(param!=null){
			path = path+param; //Linux地址 或者项目地址
		}
		//上传附件
		for(MultipartFile item : file){
			if(item.getSize()>0){
				String fileName =item.getOriginalFilename();
				String pre = "."+getPrefix(fileName);
				String date =new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				String random = RandomStringUtils.random(8, true, false);
				File efile =new File(path,date+random+pre);
				path = path+"/"+date+random+pre;
				if(!efile.exists()){
					efile.mkdirs();
				}
				try {
					item.transferTo(efile);
				}catch (Exception e) {
					e.printStackTrace();			
				}
				//上传附件，保存到从表中
			}
		}
		res.setCode(Constant.RESULT_SUCCESS);
		res.setDesc("上传成功");
		res.setData(path);
		return res.toJson();
	}
	/**
	 * 下载附件
	 * @date 2018年8月24日 
	 * @param request
	 * @param name
	 * @return
	 */
	@RequestMapping("/downLoad")
	@ResponseBody
	public ResponseEntity<byte[]> downLoad(HttpServletRequest request,String name){
		HttpHeaders headers = new HttpHeaders();
		String path ="";//下载路径
		File file = new File(path + File.separator + name);
		try {
			name = new String(name.getBytes("UTF-8"),"iso-8859-1");
			headers.setContentDispositionFormData("attachment", name);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取后缀名
	 * @date 2018年8月24日 
	 * @param fileName
	 * @return
	 */
	private String getPrefix(String fileName){
		File f =new File(fileName);      
		String name=f.getName();
		String prefix=name.substring(name.lastIndexOf(".")+1);      
		return prefix;
	}
}
