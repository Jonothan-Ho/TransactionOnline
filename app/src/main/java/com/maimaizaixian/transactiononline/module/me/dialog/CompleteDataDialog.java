package com.maimaizaixian.transactiononline.module.me.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.dialog.BaseDialog;

/**
 * Created by Administrator on 2015/11/13.
 */
public class CompleteDataDialog extends BaseDialog {

    private Button mBtnCancel;
    private Button mBtnOk;

    private View.OnClickListener mOnClickListener;

    public CompleteDataDialog(Activity context) {
        super(context);
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_me_complete_data;
    }

    @Override
    public void initView() {
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnOk = (Button) findViewById(R.id.btn_ok);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick(v);
            }
        });


    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

}
