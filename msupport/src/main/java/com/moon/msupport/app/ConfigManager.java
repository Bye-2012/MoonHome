package com.moon.msupport.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import java.util.HashMap;

/**
 * Created by Moon
 * Date: 2018/4/25
 * <p>
 * 通用参数配置类
 */

public final class ConfigManager {

    private final HashMap<Object, Object> CONFIGS = new HashMap<>();

    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }

    public ConfigManager with(Object key, Object value) {
        CONFIGS.put(key, value);
        return this;
    }

    ConfigManager withApplication(Application application) {
        return with(ConfigType.APPLICATION, application);
    }

    public ConfigManager withHost(String host) {
        return with(ConfigType.HOST, host);
    }

    public ConfigManager withDebug(boolean isDebug) {
        return with(ConfigType.DEBUG, isDebug);
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfig(Object key) {
        Object value = CONFIGS.get(key);
        if (value == null) {
            throw new RuntimeException("No config with the key");
        }
        return (T) value;
    }

    public Application getApplication() {
        return getConfig(ConfigType.APPLICATION);
    }

    public HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    public void config() {
        Utils.init(m.app());
    }

    private static final class Holder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }
}
