package com.river.datasource;

import java.util.TimerTask;

/**
 * 动态数据源定时任务
 * @author River
 * @date 2018年8月25日
 */
public class DSTimerTask extends TimerTask {
 
    @Override
    public void run() {
        DSManager.instance().clearDDS();
    }
}
