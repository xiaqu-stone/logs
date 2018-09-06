package com.stone.log;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created By: sqq
 * Created Time: 9/6/18 4:15 PM.
 * <p>
 * 基于android.util.Log日志输出的封装工具类
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Logs {

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;

    /**
     * json格式化显示的缩进字符数
     */
    private final static int JSON_INDENT = 4;

    /**
     * 应用名标签（用以本应用Log打印的默认TAG）
     */
    private static String TAG = "logs";

    /**
     * log输出等级
     */
    private static int level = DEBUG;

    /**
     * 行分隔符
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 单条log最大输出限制，超出会自动分段处理；
     * <p>
     * android.util.Log中默认最大输出长度会稍微大于这个值
     */
    private static final int MAX_SINGLE_LOG_LENGTH = 3 * 1024;

    private Logs() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 初始化Logs
     *
     * @param level      控制log的输出等级
     * @param defaultTag 默认的全局log TAG
     */
    public static void init(int level, String defaultTag) {
        Logs.level = level;
        Logs.TAG = defaultTag;
    }

    public static void i(Object str) {
        i("", str);
    }

    public static void d(Object str) {
        d("", str);
    }

    public static void v(Object str) {
        v("", str);
    }

    public static void w(Object str) {
        w("", str);
    }

    public static void e(Object str) {
        e("", str);
    }

    public static void i(String tag, Object str) {
        if (level <= INFO) {
            String name = getFunctionName();
            tag = getTag(tag);
            log(INFO, tag, name + " - " + str);
        }

    }

    public static void d(String tag, Object str) {
        if (level <= DEBUG) {
            String name = getFunctionName();
            tag = getTag(tag);
            log(DEBUG, tag, name + " - " + str);
        }
    }

    private static String getTag(String tag) {
        return tag + TAG;
    }

    public static void v(String tag, Object str) {
        if (level <= VERBOSE) {
            String name = getFunctionName();
            tag = getTag(tag);
            log(VERBOSE, tag, name + " - " + str);
        }
    }

    public static void w(String tag, Object str) {
        if (level <= WARN) {
            String name = getFunctionName();
            tag = getTag(tag);
            log(WARN, tag, name + " - " + str);
        }
    }

    public static void e(String tag, Object str) {
        if (level <= ERROR) {
            String name = getFunctionName();
            tag = getTag(tag);
            log(ERROR, tag, name + " - " + str);
        }
    }

    public static void e(Exception ex) {
        if (level <= ERROR) {
            Log.e(TAG, "error", ex);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (level <= ERROR) {
            String name = getFunctionName();
            tag = getTag(tag);
            Log.e(tag, name + " - " + msg, tr);
        }
    }

    public static void json(Object json) {
        json(TAG, json, null);
    }

    public static void json(Object json, String url) {
        json(TAG, json, url);
    }


    private static void log(int level, String tag, Object msg) {
        if (msg == null) showLog(level, tag, "null");
        else {
            int length = msg.toString().length();
            int start = 0;
            int end = MAX_SINGLE_LOG_LENGTH;
            int count = length / MAX_SINGLE_LOG_LENGTH;
            for (int i = 0; i <= count; i++) {
                if (length > end) {
                    showLog(level, tag, msg.toString().substring(start, end));
                    start = end;
                    end += MAX_SINGLE_LOG_LENGTH;
                } else {
                    showLog(level, tag, msg.toString().substring(start, length));
                    break;
                }
            }
        }

    }

    private static void showLog(int level, String tag, Object msg) {
        switch (level) {
            case VERBOSE:
                Log.v(tag, msg.toString());
                break;
            case DEBUG:
                Log.d(tag, msg.toString());
                break;
            case INFO:
                Log.i(tag, msg.toString());
                break;
            case WARN:
                Log.w(tag, msg.toString());
                break;
            case ERROR:
                Log.e(tag, msg.toString());
                break;
        }
    }

    /**
     * 格式化输出json
     */
    public static void json(String tag, Object json, String url) {
        if (level <= DEBUG) {
            String name = getFunctionName();
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
            if (!TextUtils.isEmpty(url))
                Log.i(tag, "║ onResponseSuccess URL：" + url);
            printJson(tag, name, json.toString());
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    private static void printJson(String tag, String name, String json) {
        String message;
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(JSON_INDENT);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }
        message = name + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
    }


    private static String getFunctionName() {
        return getFunctionName(false);
    }

    /**
     * 获取当前线程、类、方法、行
     */
    @SuppressWarnings("SameParameterValue")
    private static String getFunctionName(boolean isThreadBreak) {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) return "";
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(Logs.class.getName()) || st.getClassName().equals(Logs.class.getName() + "$Companion")) {
                continue;
            }
            if (isThreadBreak) {
                return "[ " + Thread.currentThread().getName() + ": \n("
                        + st.getFileName() + ":" + st.getLineNumber() + ") "
                        + st.getMethodName() + " ]";
            } else {
                return "[ " + Thread.currentThread().getName() + ": ("
                        + st.getFileName() + ":" + st.getLineNumber() + ") "
                        + st.getMethodName() + " ]";
            }
        }
        return "";
    }
}
