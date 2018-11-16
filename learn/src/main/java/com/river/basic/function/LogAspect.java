package com.river.basic.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.river.rbac.bean.UserBean;
/**
 * Log信息记录
 * @author River
 * @date 2018年8月9日
 */
@Aspect
@Component
public class LogAspect {
	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
	
	/**
	 * 拦截所有注解含有RequestMapping的方法
	 * @date 2018年10月17日 
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerLog() {
		
    }
 
    @Before("controllerLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String ip = getIpAddr(request);
		String url = request.getRequestURL() + "";
		String type = request.getMethod();
		String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		// 记录下请求内容
		log.info("请求IP : " + ip);
		log.info("请求URL : " + url);
		log.info("请求类型 : " + type);
		log.info("请求方法 : " + method);
		// 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
		if (joinPoint.getArgs().length > 0) {
			HashMap<String, Object> map = new HashMap<>();
			Integer i = 0;
			for (Object o : joinPoint.getArgs()) {
				i++;
				if (o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof UserBean) {
					continue;
				}
				map.put("param"+i, new Gson().toJson(o));
			}
			log.info("请求参数 : " + new Gson().toJson(map));
		}
	}
 
    @AfterReturning(returning = "ret", pointcut = "controllerLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("返回 : " + new Gson().toJson(ret));
    }
    
    /**
     * 获取客户端IP地址
     * @date 2018年10月17日 
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
       /* if (ip.equals("0:0:0:0:0:0:0:1")) {
        	ip = " 192.168.23.232";
        }*/
		//getMac(ip);
		System.out.println(getMacAddress(ip));
        return ip;
    }
    private static String getMac(String ip){
    	StringBuffer sb = new StringBuffer("");
    	try {
    		//InetAddress address = InetAddress.getLocalHost();
        	InetAddress address =InetAddress.getByName("192.168.23.230"); 
        	NetworkInterface ne=NetworkInterface.getByInetAddress(address);
        	byte[]mac=ne.getHardwareAddress();
        	for(int i=0; i<mac.length; i++) {
        		if(i!=0) {
        			sb.append("-");
        		}
        		//字节转换为整数
        		int temp = mac[i]&0xff;
        		String str = Integer.toHexString(temp);
        		System.out.println("每8位:"+str);
        		if(str.length()==1) {
        			sb.append("0"+str);
        		}else {
        			sb.append(str);
        		}
        	}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}  	   	
    	return sb.toString().toUpperCase();
    }
    
    /**
    * 获取MAC地址
    * @author
    * 2011-12
    */
       public static String callCmd(String[] cmd) { 
         String result = ""; 
         String line = ""; 
         try { 
           Process proc = Runtime.getRuntime().exec(cmd); 
           InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
           BufferedReader br = new BufferedReader (is); 
           while ((line = br.readLine ()) != null) { 
           result += line; 
           } 
         } 
         catch(Exception e) { 
           e.printStackTrace(); 
         } 
         return result; 
       }
        
       /** 
       * 
       * @param cmd 第一个命令 
       * @param another 第二个命令 
       * @return  第二个命令的执行结果 
       */
       public static String callCmd(String[] cmd,String[] another) { 
         String result = ""; 
         String line = ""; 
         try { 
           Runtime rt = Runtime.getRuntime(); 
           Process proc = rt.exec(cmd); 
           proc.waitFor(); //已经执行完第一个命令，准备执行第二个命令 
           proc = rt.exec(another); 
           InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
           BufferedReader br = new BufferedReader (is); 
           while ((line = br.readLine ()) != null) { 
             result += line; 
           } 
         } 
         catch(Exception e) { 
           e.printStackTrace(); 
         } 
         return result; 
       }
        
        
        
       /** 
       * 
       * @param ip 目标ip,一般在局域网内 
       * @param sourceString 命令处理的结果字符串 
       * @param macSeparator mac分隔符号 
       * @return mac地址，用上面的分隔符号表示 
       */
       public static String filterMacAddress(final String ip, final String sourceString,final String macSeparator) { 
         String result = ""; 
         String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})"; 
         Pattern pattern = Pattern.compile(regExp); 
         Matcher matcher = pattern.matcher(sourceString); 
         while(matcher.find()){ 
           result = matcher.group(1); 
           if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) { 
             break; //如果有多个IP,只匹配本IP对应的Mac. 
           } 
         }
       
         return result; 
       }
        
        
        
       /** 
       * 
       * @param ip 目标ip 
       * @return  Mac Address 
       * 
       */
       public static String getMacInWindows(final String ip){ 
         String result = ""; 
         String[] cmd = { 
             "cmd", 
             "/c", 
             "ping " + ip 
             }; 
         String[] another = { 
             "cmd", 
             "/c", 
             "arp -a"
             }; 
       
         String cmdResult = callCmd(cmd,another); 
         result = filterMacAddress(ip,cmdResult,"-"); 
       
         return result; 
       } 
     
       /** 
       * @param ip 目标ip 
       * @return  Mac Address 
       * 
       */
       public static String getMacInLinux(final String ip){ 
         String result = ""; 
         String[] cmd = { 
             "/bin/sh", 
             "-c", 
             "ping " + ip + " -c 2 && arp -a"
             }; 
         String cmdResult = callCmd(cmd); 
         result = filterMacAddress(ip,cmdResult,":"); 
       
         return result; 
       } 
        
       /**
       * 获取MAC地址 
       * @return 返回MAC地址
       */
       public static String getMacAddress(String ip){
         String macAddress = "";
         macAddress = getMacInWindows(ip).trim();
         if(macAddress==null||"".equals(macAddress)){
           macAddress = getMacInLinux(ip).trim();
         }
         return macAddress;
       }
}

