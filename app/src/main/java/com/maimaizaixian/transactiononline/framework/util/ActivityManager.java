package com.maimaizaixian.transactiononline.framework.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Manage all activity
 * <p/>
 * Created by Administrator on 2015/10/9.
 */
public class ActivityManager {

    private static ActivityManager mInstance;
    private HashMap<String, Activity> mCacheActivity;

    private ActivityManager() {
        mCacheActivity = new HashMap<>();
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    public HashMap<String, Activity> getmCacheActivity() {
        return mCacheActivity;
    }


    public void clearAll() {
        Iterator<Map.Entry<String, Activity>> iterator = mCacheActivity.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Activity> entry = iterator.next();
            String key = entry.getKey();
            Activity activity = mCacheActivity.get(key);
            activity.finish();
        }
        mCacheActivity.clear();
    }

    public void finish(String key) {
        if (mCacheActivity.get(key) != null) {
            mCacheActivity.remove(key).finish();
        }
    }

    public void put(String key, Activity activity) {
        mCacheActivity.put(key, activity);
    }

}
