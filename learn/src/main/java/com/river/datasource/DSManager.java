package com.river.datasource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
 
import org.apache.tomcat.jdbc.pool.DataSource;
 
/**
 * 动态数据源管理器
 * @author River
 * @date 2018年8月25日
 */
public class DSManager {
 
    /**
     * 管理动态数据源列表<数据源标识，数据源计时器>
     */
    private Map<String,DSTimer> dsMap = new HashMap<String,DSTimer>(); 
    /**
     * 通过定时任务周期性清除不使用的数据源
     */
    private static Timer timer = new Timer();
    
    static {
    	timer.schedule(new DSTimerTask(), 5000, 60 * 1000);
    };
 
    private DSManager() {
 
    }
    
    /**
     * 构造单例数据源管理器
     * @author River
     * @date 2018年8月25日
     */
    private static class DSSingleton {
        private static DSManager instance = new DSManager();
    }
    
    /**
     * @Description: 获取单例对象
     * @date 2018年8月25日
     * @return
     */
    public static DSManager instance() {
        return DSSingleton.instance;
    }
 
    /**
     * @Description: 添加动态数据源
     * @date 2018年8月25日
     * @param identification
     * @param dds
     */
    public synchronized void addDDS(String identification, DataSource ds) {
    	//将数据源加入计时器计时
        DSTimer ddst = new DSTimer(ds);
        //将数据源放入数据源列表
        dsMap.put(identification, ddst);
    }

    /**
     * @Description: 查询动态数据源
     * @date 2018年8月25日
     * @param identification
     * @return
     */
    public synchronized DataSource getDDS(String identification) {
        if (dsMap.containsKey(identification)) {
            DSTimer ddst = dsMap.get(identification);
            ddst.refreshTime();
            return ddst.getDynamicDataSource();
        } 
        return null;
    }
 
    /**
     * @Description: 清除不活跃的数据源
     * @date 2018年8月25日
     */
    public synchronized void clearDDS() {
 
        Iterator<Entry<String, DSTimer>> iter = dsMap.entrySet().iterator();
        for (; iter.hasNext(); ) {
            Entry<String, DSTimer> entry = iter.next();
            //判断所有的数据源是否活跃，不活跃则移除
            if (!entry.getValue().isLively())
            {
                iter.remove();
            }
        }
    }
}
