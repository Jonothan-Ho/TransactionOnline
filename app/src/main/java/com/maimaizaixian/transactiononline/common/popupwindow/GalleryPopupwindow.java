package com.maimaizaixian.transactiononline.common.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/10/28.
 */
public class GalleryPopupwindow implements OnDialogLauncherAble, View.OnClickListener {

    private PopupWindow popupWindow;
    private Activity activity;
    private View view;


    public GalleryPopupwindow(Activity activity) {
        this.activity = activity;
        view = View.inflate(activity, R.layout.popup_take_picture, null);
        TextView mTextPhoto = (TextView) view.findViewById(R.id.tv_photo);
        TextView mTextCamera = (TextView) view.findViewById(R.id.tv_camera);
        TextView mTextCancel = (TextView) view.findViewById(R.id.tv_cancel);

        mTextPhoto.setOnClickListener(this);
        mTextCamera.setOnClickListener(this);
        mTextCancel.setOnClickListener(this);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popupWindowAnim);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                ViewUtil.changeActivityAlpha(GalleryPopupwindow.this.activity, 1.0f);
            }
        });

    }

    @Override
    public void startDialog() {
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        ViewUtil.changeActivityAlpha(activity, 0.3f);
    }

    @Override
    public void closeDialog() {
        popupWindow.dismiss();
    }

    @Override
    public boolean isShow() {
        return popupWindow.isShowing();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                return;
        }
    }
}
