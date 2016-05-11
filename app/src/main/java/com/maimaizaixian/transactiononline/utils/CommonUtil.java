package com.maimaizaixian.transactiononline.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.maimaizaixian.transactiononline.framework.dialog.AppProgressDialog;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.account.ui.LoginActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2015/11/10.
 */
public class CommonUtil {


    /**
     * call telephone view from system
     *
     * @param telphone
     */
    public static void sendTelephoneNumber(Activity activity, String telphone) {

        if (TextUtils.isEmpty(telphone)) {
            ViewUtil.showSnackbar(activity.getWindow().getDecorView(), "Telephone Number cannot be empty");
        } else if (!TextUtil.isValidPhone(telphone)) {
            ViewUtil.showSnackbar(activity.getWindow().getDecorView(), "Telephone Number format error");
        } else {
            Uri uri = Uri.parse("tel:" + telphone);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(uri);
            activity.startActivity(intent);
        }
    }


    /**
     * @param context
     * @return
     */
    public static OnDialogLauncherAble getProgressDialog(Context context) {
        AppProgressDialog dialog = new AppProgressDialog(context);
        return dialog;
    }


    /**
     * @param msg
     * @return
     */
    public static String getHttpExceptionMessage(String msg) {
        String message = null;
        try {
            message = URLDecoder.decode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }


    /**
     * @param context
     * @return
     */
    public static boolean isLogged(Context context) {
        PreferencesUtil util = PreferencesUtil.getInstance(context);
        User user = util.get(User.class);
        if (user == null) {
            context.startActivity(new Intent(context, LoginActivity.class));
            return false;
        }
        return true;
    }


}
