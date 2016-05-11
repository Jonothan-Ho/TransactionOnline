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
import com.maimaizaixian.transactiononline.module.me.adapter.GroupRecruitAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.GroupRecruit;
import com.maimaizaixian.transactiononline.module.me.domain.Resume;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IMoreController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IMoreService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.MoreServiceImpl;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.List;

public class GroupRecruitActivity extends ActionBarOneActivity implements OnItemClickListener, IMoreController {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mRefreshLayout;

    private GroupRecruitAdapter mAdapter;
    private int pageIndex = 1;
    private IMoreService moreService;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("团队招募");
        setTitleBarHint("更多");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        moreService = new MoreServiceImpl(this);
        getData();

    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_with_title_bar_one;
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnLoadListener(new AppSwipeRefreshLayout.OnLoadListener() {
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
        moreService.getRecruitList(pageIndex);
    }

    @Override
    public void click(View v, int position) {
        Intent intent = new Intent(this, GroupRecruitDetailsActivity.class);
        intent.putExtra("object", mAdapter.getItem(position));
        startActivity(intent);
    }

    @Override
    public void onComplete(More_Action action) {

    }

    @Override
    public void onComplete(List<GroupRecruit> groupRecruits, int page) {
        mRefreshLayout.stopLoading();
        pageIndex = page + 1;
        if (mAdapter == null) {
            mAdapter = new GroupRecruitAdapter(this, groupRecruits);
            mAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            if (page == 1) {
                mAdapter.replaceData(groupRecruits);
            } else {
                mAdapter.addData(groupRecruits);
            }
        }
    }

    @Override
    public void onComplete(Resume resume, More_Action action) {
        
    }
}
