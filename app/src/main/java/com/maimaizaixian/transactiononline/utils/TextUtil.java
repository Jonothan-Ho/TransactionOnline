package com.maimaizaixian.transactiononline.utils;

import android.text.TextUtils;
import android.view.View;

/**
 * Created by Administrator on 2015/10/23.
 */
public class TextUtil {

    /**
     * verify user's username and password is valid
     *
     * @return
     */
    public static boolean isValidUser(String username, String passwd, View rootView) {

        if (!isValidPhone(username,rootView)) {
            return false;
        } else if (!isValidPassword(passwd,rootView)) {
            return false;
        } else {
            return true;
        }

    }


    /**
     * @param phone
     * @param rootView
     * @return
     */
    public static boolean isValidPhone(String phone, View rootView) {
        if (TextUtils.isEmpty(phone)) {
            ViewUtil.showSnackbar(rootView, "手机号不能为空");
            return false;
        }
        if (!isValidPhone(phone)) {
            ViewUtil.showSnackbar(rootView, "手机号格式错误");
            return false;
        }
        return true;
    }


    /**
     * Verify phone is valid
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhone(String phone) {

        String pattern = "^(13[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
        if (phone.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password, View rootView) {
        if (TextUtils.isEmpty(password)) {
            ViewUtil.showSnackbar(rootView, "密码不能为空");
            return false;
        }
        if (password.length() <= 6 && password.length() >= 32) {
            ViewUtil.showSnackbar(rootView, "密码必须为6-32位");
            return false;
        }
        return true;
    }


}
