package com.moon.msupport;

import android.app.Application;
import android.content.Context;

import java.lang.reflect.Method;

/**
 * Created by Moon on 2017/3/16.
 * Desc: 总管理类
 */

public final class m {

    public static Application app() {
        if (Mth.app == null) {
            try {
                // 在IDE进行布局预览时使用
                Class<?> renderActionClass = Class.forName("com.android.layoutlib.bridge.impl.RenderAction");
                Method method = renderActionClass.getDeclaredMethod("getCurrentContext");
                Context context = (Context) method.invoke(null);
                Mth.app = new MockApplication(context);
            } catch (Throwable ignored) {
                throw new RuntimeException("please invoke m.Mth.init(app) on Application#onCreate()");
            }
        }
        return Mth.app;
    }

    public static class Mth {
        private static Application app;

        public static void init(Application application) {
            if (Mth.app == null) {
                Mth.app = application;
            }
        }
    }

    private static class MockApplication extends Application {
        public MockApplication(Context baseContext) {
            this.attachBaseContext(baseContext);
        }
    }
}
