package com.maimaizaixian.transactiononline.framework.activity;

import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.util.ActivityManager;

/**
 * The base class for all Activity
 * <p/>
 * Created by Administrator on 2015/10/9.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Manage Activity
     */
    private ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mActivityManager = ActivityManager.getInstance();
        mActivityManager.put(this.getClass().getSimpleName(), this);
        setContentView(getCustomView());
        initView();
        initListener();
    }


    /**
     * get content view's id
     *
     * @return
     */
    protected abstract int getCustomView();

    /**
     * initialized view
     */
    protected abstract void initView();

    /**
     * initialized listener
     */
    protected abstract void initListener();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityManager.finish(this.getClass().getSimpleName());
    }

}
