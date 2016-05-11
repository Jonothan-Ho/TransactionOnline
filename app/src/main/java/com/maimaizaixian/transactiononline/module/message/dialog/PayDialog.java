package com.maimaizaixian.transactiononline.module.message.dialog;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.dialog.BaseDialog;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/11/10.
 */
public class PayDialog extends BaseDialog {

    private TextView mTextPay;
    private TextView mTextBalance;
    private EditText mEditPasswd;
    private EditText mEditPasswdRepeat;
    private Button mBtnCancel;
    private Button mBtnOK;

    private LinearLayout mLayoutPasswdRepeat;
    private View view;

    private boolean isPayed = false;
    private OnPayListener mPayListener;
    private String msgPrice;
    private String mBalance;

    public PayDialog(Activity context, boolean isPayed, String messagePrice) {
        super(context);
        this.isPayed = isPayed;
        this.msgPrice = messagePrice;
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_message_pay;
    }

    @Override
    public void initView() {
        mTextPay = (TextView) findViewById(R.id.tv_pay_money);
        mTextBalance = (TextView) findViewById(R.id.tv_balance_money);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnOK = (Button) findViewById(R.id.btn_action);
        mEditPasswd = (EditText) findViewById(R.id.et_passwd);
        mEditPasswdRepeat = (EditText) findViewById(R.id.et_passwd_repeat);
        mLayoutPasswdRepeat = (LinearLayout) findViewById(R.id.layout_passwd);
        view = findViewById(R.id.view_divider);

        mTextPay.setText(msgPrice);
        mTextBalance.setText(mBalance);

        if (isPayed) {
            mLayoutPasswdRepeat.setVisibility(View.GONE);
            mEditPasswd.setHint("请输入支付密码");
            view.setVisibility(View.GONE);
        }

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }
        });

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPayListener != null) {
                    String passwd = mEditPasswd.getText().toString().trim();
                    String passwdRepeat = mEditPasswdRepeat.getText().toString().trim();

                    if (!isPayed) {
                        if (!TextUtil.isValidPassword(passwd, activity.getWindow().getDecorView())) {
                            return;
                        }

                        if (!passwd.equals(passwdRepeat)) {
                            ViewUtil.showSnackbar(activity.getWindow().getDecorView(), "两次密码输入不一致");
                            return;
                        }
                    } else {
                        if (TextUtils.isEmpty(passwd)) {
                            ViewUtil.showSnackbar(activity.getWindow().getDecorView(), "支付密码不能为空");
                            return;
                        }
                    }

                    mPayListener.onPay(isPayed, passwd, passwdRepeat);

                    mEditPasswd.setText("");
                    mEditPasswdRepeat.setText("");

                }
            }
        });
    }

    public void setPayed() {
        isPayed = true;
        if (isPayed) {
            mLayoutPasswdRepeat.setVisibility(View.GONE);
            mEditPasswd.setHint("请输入支付密码");
            view.setVisibility(View.GONE);
        }
    }


    /**
     * @param listener
     */
    public void setOnPayListener(OnPayListener listener) {
        this.mPayListener = listener;
    }

    public interface OnPayListener {
        void onPay(boolean isPayed, String passwd, String passwdRepeat);
    }

    /**
     * @param price
     */
    public void setMessagePrice(String price) {
        if (mTextPay != null) {
            mTextPay.setText(price);
        } else {
            msgPrice = price;
        }

    }

    /**
     * @param balance
     */
    public void setBalance(String balance) {
        if (mTextBalance != null) {
            mTextBalance.setText(balance);
        } else {
            this.mBalance = balance;
        }

    }


}
