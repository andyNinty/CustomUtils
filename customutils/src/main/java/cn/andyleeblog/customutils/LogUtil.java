package cn.andyleeblog.customutils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 工具类 用于保存log 方便调试开发和发版
 * Created by andy on 17-1-17.
 */

public class LogUtil {

    private static final int VERBOSE = 0;

    private static final int DEBUG = 1;

    private static final int INFO = 2;

    private static final int WARN = 3;

    private static final int ERROR = 4;

    private static final int NOTHING = 5;

    private static int level;

    private static Context mContext;
    private static File logFile;


    /**
     * 开启log
     */
    public static void init(Context context) {
        mContext = context;
        level = VERBOSE;
    }

    /**
     * 关闭log
     */
    public static void closeLog() {
        level = NOTHING;
    }

    /**
     * 保存log到手机
     * @return file
     */

    private static File SaveLogFile() {
        File dir = new File(getLogFileDir());
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                LogUtil.e("LogUtil", "make dirs failed");
                return null;
            }
        }
        return new File(dir, "log.txt");
    }

    /**
     * 删除log
     */
    public static void delLog() {
        if (logFile != null) {
            logFile.delete();
        }
    }

    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            appendLog("[VERBOSE]:" + tag + ":" + msg);
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            appendLog("[DEBUG]:" + tag + ":" + msg);
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level <= INFO) {
            appendLog("[INFO]:" + tag + ":" + msg);
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level <= WARN) {
            appendLog("[WARNING]:" + tag + ":" + msg);
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            appendLog("[ERROR]:" + tag + ":" + msg);
            Log.e(tag, msg);
        }
    }

    private static void appendLog(String text) {
        logFile = SaveLogFile();
        if (level == NOTHING || logFile == null) {
            return;
        }
        try {
            SimpleDateFormat sdFormatter = new SimpleDateFormat("[MM:dd:HH:mm:ss]", Locale.US);
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(sdFormatter.format(System.currentTimeMillis()));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getLogFileDir() {
        if (mContext.getExternalCacheDir() != null) {
            return mContext.getExternalCacheDir().getPath();
        }
        return Environment.getExternalStorageDirectory().getPath() +
                "/Android/data/" + mContext.getPackageName() + File.separator + "log";
    }

}
