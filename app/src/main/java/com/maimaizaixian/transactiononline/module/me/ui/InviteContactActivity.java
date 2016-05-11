package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.me.adapter.InviteContactAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.Contact;
import com.maimaizaixian.transactiononline.module.me.domain.impl.ContactImpl;

import java.util.ArrayList;

public class InviteContactActivity extends ActionBarOneActivity implements OnItemClickListener{


    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private InviteContactAdapter mAdapter;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("邀请通讯录好友");
        setTitleBarHint("我");


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            contacts.add(new ContactImpl());
        }

        mAdapter = new InviteContactAdapter(this, contacts);
        mAdapter.addOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_with_title_bar_one;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void click(View v, int position) {

    }
}
