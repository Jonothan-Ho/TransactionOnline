package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.me.adapter.SystemNoticeAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.SystemNotice;
import com.maimaizaixian.transactiononline.module.me.mvc.control.ISystemNoticeController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.ISystemNoticeService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.SystemNoticeServiceImpl;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.List;

public class SystemNoticeActivity extends ActionBarOneActivity implements OnItemClickListener, ISystemNoticeController {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;

    private SystemNoticeAdapter mAdapter;

    private ISystemNoticeService mSystemNoticeService;
    private int pageIndex = 1;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("系统公告");
        setTitleBarHint("我");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        mSystemNoticeService = new SystemNoticeServiceImpl(this);
        getData();
    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_with_title_bar_one;
    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnLoadListener(new AppSwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onPullUp() {
                getData();
            }

            @Override
            public void onPullDown() {
                pageIndex = 1;
                getData();
            }
        });
    }

    private void getData() {
        mSystemNoticeService.getSystemNoticeList(pageIndex);
    }

    @Override
    public void click(View v, int position) {
        Intent intent = new Intent(this, SystemNoticeDetailsActivity.class);
        intent.putExtra("object", mAdapter.getItem(position));
        startActivity(intent);
    }

    @Override
    public void onComplete(List<SystemNotice> systemNotices, int page) {
        mSwipeRefreshLayout.stopLoading();
        pageIndex = page + 1;
        if (mAdapter == null) {
            mAdapter = new SystemNoticeAdapter(this, systemNotices);
            mAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            if (page == 1) {
                mAdapter.replaceData(systemNotices);
            } else {
                mAdapter.addData(systemNotices);
            }
        }
    }
}
