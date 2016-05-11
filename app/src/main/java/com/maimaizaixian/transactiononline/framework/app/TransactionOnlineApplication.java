package com.maimaizaixian.transactiononline.framework.app;

import android.app.Application;

import com.easemob.chat.EMChat;
import com.easemob.easeui.controller.EaseUI;

/**
 * Created by Administrator on 2015/12/15.
 */
public class TransactionOnlineApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //init IM
        initIM();
    }


    private void initIM() {
        EMChat.getInstance().init(this);
        //debug
        EMChat.getInstance().setDebugMode(true);
        //off auto login
        EMChat.getInstance().setAutoLogin(false);

        //init UI
        EaseUI.getInstance().init(this);

    }


}
