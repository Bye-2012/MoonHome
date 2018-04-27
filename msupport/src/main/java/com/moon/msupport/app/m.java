package com.moon.msupport.app;

import android.app.Application;

/**
 * Created by Moon on 2017/3/16.
 * Desc: 总管理类
 */

public final class m {

    public static ConfigManager init(Application application) {
        return ConfigManager.getInstance().withApplication(application);
    }

    private static <T> T getConfig(Object key) {
        return ConfigManager.getInstance().getConfig(key);
    }

    public static Application app() {
        return getConfig(ConfigType.APPLICATION);
    }
}
