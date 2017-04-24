package cn.andyleeblog.customutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 工具类，用于存放一些小的数据
 * Created by andy on 17-1-17.
 */

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance = null;
    private SharedPreferences mSetting = null;
    private SharedPreferences.Editor mEditor = null;

    private SharedPreferencesUtil(Context context) {
        mSetting = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSetting.edit();
        mEditor.apply();
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        synchronized (SharedPreferencesUtil.class) {
            if (instance == null) {
                instance = new SharedPreferencesUtil(context);
            }
        }
        return instance;
    }

    public String getString(final String key, final String defaultValue) {
        return mSetting.getString(key, defaultValue);
    }

    public void setString(final String key, final String value) {
        mSetting.edit().putString(key, value).apply();
    }

    public boolean getBoolean(final String key, final boolean defaultValue) {
        return mSetting.getBoolean(key, defaultValue);
    }

    public boolean hasKey(final String key) {
        return mSetting.contains(key);
    }

    public void setBoolean(final String key, final boolean value) {
        mSetting.edit().putBoolean(key, value).apply();
    }

    public void setInt(final String key, final int value) {
        mSetting.edit().putInt(key, value).apply();
    }

    public int getInt(final String key, final int defaultValue) {

        return mSetting.getInt(key, defaultValue);
    }

    public void setFloat(final String key, final float value) {

        mSetting.edit().putFloat(key, value).apply();
    }

    public float getFloat(final String key, final float defaultValue) {
        return mSetting.getFloat(key, defaultValue);
    }

    public void setLong(final String key, final long value) {
        mSetting.edit().putLong(key, value).apply();
    }

    public long getLong(final String key, final long defaultValue) {
        return mSetting.getLong(key, defaultValue);
    }

    public void clear() {
        mEditor.clear();
    }
}
