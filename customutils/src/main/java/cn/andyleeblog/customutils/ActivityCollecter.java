package cn.andyleeblog.customutils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityCollecter
 * Created by andy on 17-2-8.
 */

public class ActivityCollecter {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static Activity findActivity() {
        return activityList.get(activityList.size() - 2);
    }

    public static void finishAllActivitys() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
