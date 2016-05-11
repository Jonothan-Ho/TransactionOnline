package com.maimaizaixian.transactiononline.utils;

import android.content.Context;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Administrator on 2015/12/17.
 */
public class ShareUtil {

    private static ShareUtil instance;

    private ShareUtil() {

    }

    private ShareUtil(Context context) {
        ShareSDK.initSDK(context);
    }


    public static ShareUtil getInstance(Context context) {
        if (instance == null) {
            instance = new ShareUtil(context);
        }

        return instance;
    }


    /**
     * @param params
     * @param listener
     */
    public void shareQQ(QQ.ShareParams params, PlatformActionListener listener) {
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        platform.setPlatformActionListener(listener);
        platform.share(params);
    }

    /**
     * @param params
     * @param listener
     */
    public void shareWXingChat(Wechat.ShareParams params, PlatformActionListener listener) {
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.setPlatformActionListener(listener);
        platform.share(params);
    }

    /**
     * @param params
     * @param listener
     */
    public void shareWXingChat(WechatMoments.ShareParams params, PlatformActionListener listener) {
        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
        platform.setPlatformActionListener(listener);
        platform.share(params);
    }

    /**
     * @param params
     * @param listener
     */
    public void shareQZone(QZone.ShareParams params, PlatformActionListener listener) {
        Platform platform = ShareSDK.getPlatform(QZone.NAME);
        platform.setPlatformActionListener(listener);
        platform.share(params);
    }

    /**
     * @param params
     * @param listener
     */
    public void shareWeibo(SinaWeibo.ShareParams params, PlatformActionListener listener) {
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        platform.setPlatformActionListener(listener);
        platform.share(params);
    }

}
