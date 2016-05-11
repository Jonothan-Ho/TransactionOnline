package com.maimaizaixian.transactiononline.utils;

import android.text.TextUtils;
import android.util.Log;

import com.maimaizaixian.transactiononline.common.Common;

/**
 * Created by Administrator on 2015/10/16.
 */
public class LogUtil {

    public static void printf(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.d(Common.LOG_TAG_NAME, msg);
        }
    }

}
