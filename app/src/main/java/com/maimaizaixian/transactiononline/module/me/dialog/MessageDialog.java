package com.maimaizaixian.transactiononline.module.me.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.dialog.BaseDialog;

/**
 * Created by Administrator on 2015/11/18.
 */
public class MessageDialog extends BaseDialog {

    private TextView mTextMessage;
    private Button mBtnOk;

    public MessageDialog(Activity context) {
        super(context);
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_me_history_message;
    }

    @Override
    public void initView() {
        mTextMessage = (TextView) findViewById(R.id.tv_message);
        mBtnOk = (Button) findViewById(R.id.btn_ok);

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }
        });

    }

    public void setMessage(String message) {
        mTextMessage.setText(message);
    }

}
