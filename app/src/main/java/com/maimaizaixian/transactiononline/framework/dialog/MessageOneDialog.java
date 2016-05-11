package com.maimaizaixian.transactiononline.framework.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;

/**
 * Created by Administrator on 2015/11/19.
 */
public class MessageOneDialog extends BaseDialog {

    private Button mBtnCancel;
    private Button mBtnOk;
    private TextView mTextContent;

    private View.OnClickListener mOnClickListener;
    private String mTextMessage;

    public MessageOneDialog(Activity context) {
        super(context);
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_framework_message_one;
    }

    @Override
    public void initView() {
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mTextContent = (TextView) findViewById(R.id.tv_content);
        mTextContent.setText(mTextMessage);

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

    /**
     * @param text
     */
    public void setContent(String text) {
        this.mTextMessage = text;
    }

    /**
     * @param listener
     */
    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

}
