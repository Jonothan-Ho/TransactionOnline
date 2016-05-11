package com.maimaizaixian.transactiononline.module.common.ui;


import android.os.Bundle;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.NoBarActivity;

public class ChatActivity extends NoBarActivity {


    @Override
    protected int getCustomView() {
        return R.layout.activity_common_chat;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initSubView() {
        String userId = getIntent().getStringExtra("id");
        //new出EaseChatFragment或其子类的实例
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, userId);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }
}
