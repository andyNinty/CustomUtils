package cn.andyleeblog.customutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;

/**
 * 工具类,用于获取安装包的一些信息
 * Created by andy on 17-1-17.
 */

public class PackageUtil {
    private static final String TAG = "PackageUtil";

    /**
     * 获取版本当前版本号
     *
     * @param context context
     * @return version code
     */
    public static int getPackageVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            return info.versionCode;
        } else {
            return 1;
        }
    }


    /**
     * 获取版本当前version name
     *
     * @param context context
     * @return version name
     */
    @Nullable
    public static String getPackageVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            return info.versionName;
        } else {
            return null;
        }
    }

//    /**
//     * 检测辅助功能是否已经打开
//     *
//     * @param context context
//     * @return isOpen
//     */
//    public static boolean isAccessibilitySettingsOn(Context context) {
//        int accessibilityEnabled = 0;
//        final String service = context.getPackageName() + "/" + MyAccessibilityService.class.getCanonicalName();
//        try {
//            accessibilityEnabled = Settings.Secure.getInt(
//                    context.getApplicationContext().getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
//            Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
//        } catch (Settings.SettingNotFoundException e) {
//            Log.e(TAG, "没有找到辅助功能设置 " + e.getMessage());
//        }
//        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
//
//        if (accessibilityEnabled == 1) {
//            String settingValue = Settings.Secure.getString(
//                    context.getApplicationContext().getContentResolver(),
//                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
//            if (settingValue != null) {
//                mStringColonSplitter.setString(settingValue);
//                while (mStringColonSplitter.hasNext()) {
//                    String accessibilityService = mStringColonSplitter.next();
//                    if (accessibilityService.equalsIgnoreCase(service)) {
//                        Log.v(TAG, "辅助服务服务已经打开");
//                        return true;
//                    }
//                }
//            }
//        } else {
//            Log.v(TAG, "辅助服务没有打开");
//        }
//        return false;
//    }
}
