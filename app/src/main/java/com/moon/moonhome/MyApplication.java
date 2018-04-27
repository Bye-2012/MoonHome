package com.moon.moonhome;

import android.support.multidex.MultiDexApplication;

import com.moon.msupport.app.m;

/**
 * Created by Moon on 2017/3/16.
 * Desc: Application
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        m.init(this)
                .withHost("http://test-edu-api.hejun.com")
                .config();


    }
}
