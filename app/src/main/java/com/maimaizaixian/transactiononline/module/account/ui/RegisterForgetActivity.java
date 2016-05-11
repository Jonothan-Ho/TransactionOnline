package com.maimaizaixian.transactiononline.module.account.ui;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.account.mvc.controller.IAccountController;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.account.mvc.service.IAccountService;
import com.maimaizaixian.transactiononline.module.account.mvc.service.impl.AccountServiceImpl;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;

/**
 * Created by Administrator on 2015/10/23.
 */
public abstract class RegisterForgetActivity extends ActionBarOneActivity implements IAccountController<User>, View.OnClickListener {

    protected ImageView mImgLogo;

    protected EditText mEditUsername;
    protected EditText mEditPasswd;
    protected EditText mEditValidCode;

    protected CheckBox mBoxIsShow;
    protected Button mBtnValid;
    protected Button mBtnAction;
    private TextView mTextPassword;

    protected IAccountService service;


    @Override
    protected void initSubView() {
        mBoxIsShow = (CheckBox) findViewById(R.id.checkbox_passwd_show);
        mBtnAction = (Button) findViewById(R.id.btn_action);
        mBtnValid = (Button) findViewById(R.id.btn_valid);
        mTextPassword = (TextView) findViewById(R.id.tv_passwd);
        mImgLogo = (ImageView) findViewById(R.id.iv_logo);
        mEditUsername = (EditText) findViewById(R.id.et_username);
        mEditPasswd = (EditText) findViewById(R.id.et_passwd);
        mEditValidCode = (EditText) findViewById(R.id.et_valid_code);

        service = new AccountServiceImpl(this);

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_account_regist_forget;
    }

    @Override
    protected void initListener() {
        mBtnAction.setOnClickListener(this);
        mBtnValid.setOnClickListener(this);
        mBoxIsShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEditPasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    mEditPasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_action:
                handle(mEditUsername.getText().toString().trim(), mEditPasswd.getText().toString().trim(), mEditValidCode.getText().toString().trim());
                break;
            case R.id.btn_valid:
                getVaildCode();
                break;
        }
    }

    /**
     * @param hint
     */
    public void setPasswordHint(String hint) {
        mTextPassword.setText(hint);
    }


    /**
     * get valid code
     */
    public void getVaildCode() {
        String phone = mEditUsername.getText().toString();
        service.getValidCode(phone);
    }

    @Override
    public void closeActivity() {
        finish();
    }

    /**
     * be called when mBtnAction is clicked
     *
     * @param username
     * @param passwd
     * @param validCode
     */
    public abstract void handle(String username, String passwd, String validCode);


    @Override
    public void completeValidCode(String code) {
        mEditValidCode.setText(code);
    }


}
