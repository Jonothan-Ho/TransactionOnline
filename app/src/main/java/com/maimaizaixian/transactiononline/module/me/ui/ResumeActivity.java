package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.hall.adapter.ConditionAreaAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.GroupRecruit;
import com.maimaizaixian.transactiononline.module.me.domain.Resume;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IMoreController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IMoreService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.MoreServiceImpl;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

public class ResumeActivity extends ActionBarOneActivity implements IMoreController {

    @ViewInject(R.id.et_name)
    private EditText mEditName;
    @ViewInject(R.id.et_age)
    private EditText mEditAge;

    @ViewInject(R.id.tv_area)
    private TextView mTextArea;

    @ViewInject(R.id.et_content)
    private EditText mEditContent;
    @ViewInject(R.id.et_phone)
    private EditText mEditPhone;
    @ViewInject(R.id.et_email)
    private EditText mEditEmail;
    @ViewInject(R.id.et_qq)
    private EditText mEditQQ;
    @ViewInject(R.id.et_weixin)
    private EditText mEditWeixin;
    @ViewInject(R.id.et_money)
    private EditText mEditMoney;

    @ViewInject(R.id.btn_submit)
    private Button mBtnSubmit;

    private IMoreService moreService;
    private Resume resume;
    private String addressID;
    private String id;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("简历");
        setTitleBarHint("详情");

        id = getIntent().getStringExtra("id");
        moreService = new MoreServiceImpl(this);
        moreService.getResume();

    }

    private void initData() {
        mEditName.setText(resume.getTitle());
        mEditAge.setText(resume.getClick());
        mEditContent.setText(resume.getContent());
        mEditEmail.setText(resume.getEmail());
        mEditMoney.setText(resume.getPrice());
        mEditPhone.setText(resume.getMobile());
        mEditQQ.setText(resume.getQq());
        mEditWeixin.setText(resume.getAccount());
        mTextArea.setText(resume.getAddress());
        addressID = resume.getCity();
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_resume;
    }

    @Override
    protected void initListener() {
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mEditName.getText().toString().trim();
                String age = mEditAge.getText().toString().trim();
                String content = mEditContent.getText().toString().trim();
                String phone = mEditPhone.getText().toString().trim();
                String mail = mEditEmail.getText().toString().trim();
                String qq = mEditQQ.getText().toString().trim();
                String weixin = mEditWeixin.getText().toString().trim();
                String money = mEditMoney.getText().toString().trim();
                String address = mTextArea.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    ViewUtil.showSnackbar(getRootView(), "姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    ViewUtil.showSnackbar(getRootView(), "年龄不能为空");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    ViewUtil.showSnackbar(getRootView(), "内容不能为空");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ViewUtil.showSnackbar(getRootView(), "电话不能为空");
                    return;
                }
                if (TextUtils.isEmpty(money)) {
                    ViewUtil.showSnackbar(getRootView(), "期望薪资不能为空");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    ViewUtil.showSnackbar(getRootView(), "地址不能为空");
                    return;
                }

                RequestParams params = new RequestParams();
                params.addBodyParameter("model", "resume");
                params.addBodyParameter("title", name);
                params.addBodyParameter("click", age);
                params.addBodyParameter("address", address);
                params.addBodyParameter("content", content);
                params.addBodyParameter("mobile", phone);
                params.addBodyParameter("price", money);
                params.addBodyParameter("email", mail);
                params.addBodyParameter("qq", qq);
                params.addBodyParameter("account", weixin);
                params.addBodyParameter("city", addressID);

                if (resume == null) {
                    moreService.makeResume(params);
                } else {
                    params.addBodyParameter("id", resume.getId());
                    moreService.updateResume(params);
                }

            }
        });

        mTextArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumeActivity.this, AreaActivity.class);
                startActivityForResult(intent, 10);
            }
        });

    }

    @Override
    public void onComplete(More_Action action) {

    }

    @Override
    public void onComplete(List<GroupRecruit> groupRecruits, int page) {

    }

    @Override
    public void onComplete(Resume resume, More_Action action) {
        switch (action) {
            case Resume:
                if (resume != null) {
                    this.resume = resume;
                    initData();
                }
                break;
            case updateResume:
            case makeResume:
                moreService.sendResume(id);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            ConditionAreaAdapter.LocalArea area = (ConditionAreaAdapter.LocalArea) data.getSerializableExtra("object");
            mTextArea.setText(area.getCity());
            addressID = area.getCid()+"";
        }
    }
}
