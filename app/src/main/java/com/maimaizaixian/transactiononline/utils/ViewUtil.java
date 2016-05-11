package com.maimaizaixian.transactiononline.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * View Tool
 * Created by Administrator on 2015/10/15.
 */
public class ViewUtil {


    /**
     * Show Text By Snackbar
     *
     * @param rootView
     * @param content
     */
    public static void showSnackbar(View rootView, String content) {
        try {
            if (!TextUtils.isEmpty(content)) {
                Snackbar.make(rootView, content, Snackbar.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }

    }

    /**
     * Show Text By Toast
     *
     * @param context
     * @param content
     */
    public static void showToast(Context context, String content) {
        try {
            if (!TextUtils.isEmpty(content)) {
                Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }
    }

    /**
     * change activity alpha
     *
     * @param activity
     * @param alpha
     */
    public static void changeActivityAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = alpha;
        activity.getWindow().setAttributes(params);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


}
