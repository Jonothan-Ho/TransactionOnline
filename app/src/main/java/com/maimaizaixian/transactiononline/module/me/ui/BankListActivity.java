package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.me.adapter.BankListAdapter;
import com.maimaizaixian.transactiononline.module.me.adapter.WithdrawRecordsAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.Bank;
import com.maimaizaixian.transactiononline.module.me.domain.WithdrawRecords;
import com.maimaizaixian.transactiononline.module.me.domain.impl.BankImpl;
import com.maimaizaixian.transactiononline.module.me.domain.impl.WithdrawRecordsImpl;

import java.util.ArrayList;

public class BankListActivity extends ActionBarOneActivity implements OnItemClickListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    @Override
    protected void initSubView() {
        setTitleBarHeadline("银行");
        setTitleBarHint("银行卡绑定");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<Bank> banks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            banks.add(new BankImpl());
        }

        BankListAdapter adapter = new BankListAdapter(this, banks);
        adapter.addOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);

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
