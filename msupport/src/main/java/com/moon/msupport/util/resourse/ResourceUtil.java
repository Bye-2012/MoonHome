package com.moon.msupport.util.resourse;

import android.graphics.drawable.Drawable;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.moon.msupport.app.m;

/**
 * Created by Moon
 * Date: 2018/4/27
 * Desc: 资源类
 */

public final class ResourceUtil {

    private ResourceUtil() {
    }

    public static int getColor(@ColorRes int id) {
        return ContextCompat.getColor(m.app(), id);
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(m.app(), id);
    }

    public static String getString(@StringRes int id) {
        return m.app().getResources().getString(id);
    }

    public static Boolean getBoolean(@BoolRes int id) {
        return m.app().getResources().getBoolean(id);
    }
}
