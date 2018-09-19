package com.river.rbac;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 在线人数统计（session监听器）
 * @author River
 * @date 2018年9月19日
 */
public class ShiroSessionListener implements SessionListener{
	 /**
     * 统计在线人数
     * 高并发情况下线程安全自增
     */
    private static final AtomicInteger sessionCount = new AtomicInteger(0);
    
    /**
     * 会话过期时触发
     * @param session
     */
	@Override
	public void onExpiration(Session session) {
		 //会话过期,在线人数减一
        sessionCount.decrementAndGet();		
	}
	
	/**
     * 会话创建时触发
     * @param session
     */
	@Override
	public void onStart(Session session) {
		//会话创建，在线人数加一
        sessionCount.incrementAndGet();	
	}
	/**
     * 退出会话时触发
     * @param session
     */
	@Override
	public void onStop(Session session) {
		//会话退出,在线人数减一
        sessionCount.decrementAndGet();		
	}
	/**
     * 获取在线人数使用
     * @return
     */
    public static Integer getSessionCount() {
    	int index=sessionCount.get();
        return index;
    }
}
