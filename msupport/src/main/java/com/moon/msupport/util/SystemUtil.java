package com.moon.msupport.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.moon.msupport.m;

import java.util.List;

/**
 * Created by Moon on 2017/3/16.
 * Desc: 系统工具
 */

public class SystemUtil {

    /**
     * 获取软件版本名称
     *
     * @return 软件版本名称
     */
    public static String getVersionName() {
        PackageManager packageManager = m.app().getPackageManager();
        try {
            return packageManager.getPackageInfo(m.app().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得软件版本号
     *
     * @return 软件版本号
     */
    public static int getVersionCode() {
        PackageManager packageManager = m.app().getPackageManager();
        try {
            return packageManager.getPackageInfo(m.app().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取软件包名
     */
    public static String getPackageName() {
        return m.app().getPackageName();
    }

    /**
     * 获取进程名字
     */
    public static String getProcessName() {
        ActivityManager am = (ActivityManager) m.app().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * 当前进程是否是app进程
     */
    public static boolean isRightProcess() {
       return TextUtils.equals(getProcessName(), getPackageName());
    }

    /**
     * 获取手机序列号
     *
     * @return 手机序列号
     */
    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) m.app().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = null;
        try {
            deviceId = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deviceId == null) {
            try {
                deviceId = Settings.Secure.getString(m.app().getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (deviceId == null) {
            deviceId = "sorry! i have no imei!!!";
        }
        return deviceId;
    }
}
