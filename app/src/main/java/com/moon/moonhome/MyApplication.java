package com.moon.moonhome;

import android.support.multidex.MultiDexApplication;

import com.moon.msupport.m;
import com.moon.msupport.util.SystemUtil;

/**
 * Created by Moon on 2017/3/16.
 * Desc: Application
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // 判断当前进程是app的进程
        if (SystemUtil.isRightProcess()) {
            // 初始化框架
            m.Mth.init(this);
        }
    }
}
