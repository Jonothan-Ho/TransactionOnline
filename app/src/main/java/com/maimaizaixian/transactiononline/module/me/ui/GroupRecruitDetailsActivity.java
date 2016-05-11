package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.me.domain.GroupRecruit;
import com.maimaizaixian.transactiononline.utils.CommonUtil;

public class GroupRecruitDetailsActivity extends ActionBarOneActivity {

    @ViewInject(R.id.tv_name)
    private TextView mTextName;
    @ViewInject(R.id.tv_date)
    private TextView mTextDate;
    @ViewInject(R.id.tv_content)
    private TextView mTextContent;

    @ViewInject(R.id.btn_submit)
    private Button mBtnSubmit;
    @ViewInject(R.id.btn_call)
    private Button mBtnCall;

    private GroupRecruit recruit;

    @Override
    protected void initSubView() {
        setTitleBarHeadline("详情");
        setTitleBarHint("团队招募");

        recruit = (GroupRecruit) getIntent().getSerializableExtra("object");
        if (recruit == null) {
            finish();
            return;
        }

        mTextContent.setText(recruit.getContent());
        mTextDate.setText(recruit.getCreate_time());
        mTextName.setText(recruit.getTitle());

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_group_recruit_details;
    }

    @Override
    protected void initListener() {
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.sendTelephoneNumber(GroupRecruitDetailsActivity.this, recruit.getMobile());
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupRecruitDetailsActivity.this, ResumeActivity.class);
                intent.putExtra("id", recruit.getId());
                startActivity(intent);
            }
        });
    }
}
