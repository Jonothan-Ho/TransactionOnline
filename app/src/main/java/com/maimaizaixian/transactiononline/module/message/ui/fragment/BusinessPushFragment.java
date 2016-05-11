package com.maimaizaixian.transactiononline.module.message.ui.fragment;


import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.message.adapter.BusinessMessageAdapter;
import com.maimaizaixian.transactiononline.module.message.domain.BusinessMessage;
import com.maimaizaixian.transactiononline.module.message.domain.impl.BusinessMessageImpl;
import com.maimaizaixian.transactiononline.module.message.ui.DetailsActivity;
import com.maimaizaixian.transactiononline.module.neighborhood.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class BusinessPushFragment extends BaseMenuFragment implements OnItemClickListener, IBusinessController {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;

    private IBusinessService mBusinessService;
    private int pageIndex = 1;
    private BusinessMessageAdapter mMessageAdapter;

    @Override
    public void initSubView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));

        mBusinessService = new BusinessServiceImpl(this);
        getData();
    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_message_business_push;
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
        mBusinessService.getBusinessPushList(pageIndex);
    }

    @Override
    public void click(View v, int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("object", mMessageAdapter.getItem(position));
        startActivity(intent);
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business,Business_Action action) {

    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {
        mSwipeRefreshLayout.stopLoading();
        pageIndex = page + 1;
        if (mMessageAdapter == null) {
            mMessageAdapter = new BusinessMessageAdapter(getActivity(), business);
            mMessageAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mMessageAdapter);
        } else {
            if (page == 1) {
                mMessageAdapter.replaceData(business);
            } else {
                mMessageAdapter.addData(business);
            }
        }
    }

    @Override
    public void onComplete(Business_Type type) {

    }
}
