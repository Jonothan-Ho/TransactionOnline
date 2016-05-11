package com.maimaizaixian.transactiononline.module.hall.ui.fragment;


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
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentRefreshListener;
import com.maimaizaixian.transactiononline.module.hall.adapter.ListModeAdapter;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.hall.domain.MatchResult;
import com.maimaizaixian.transactiononline.module.hall.domain.impl.MatchResultImpl;
import com.maimaizaixian.transactiononline.module.hall.ui.DetailsActivity;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment} subclass.
 */
public class IndexListFragment extends BaseMenuFragment implements OnItemClickListener, OnFragmentArgumentListener<Business> {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private ListModeAdapter mModeAdapter;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;

    private OnFragmentRefreshListener mRefreshListener;

    @Override
    public void initSubView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        mRefreshListener = (OnFragmentRefreshListener) getParentFragment();
    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_style_one;
    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnLoadListener(new AppSwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onPullUp() {
                mRefreshListener.getData();
            }

            @Override
            public void onPullDown() {
                mRefreshListener.setPage(1);
                mRefreshListener.getData();
            }
        });
    }

    @Override
    public void click(View v, int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("object", mModeAdapter.getItem(position));
        getContext().startActivity(intent);
    }

    @Override
    public void onComplete(List<Business> users, int page) {
        mSwipeRefreshLayout.stopLoading();
        if (mModeAdapter == null) {
            mModeAdapter = new ListModeAdapter(getActivity(), users);
            mModeAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mModeAdapter);
        } else {
            if (page == 1) {
                mModeAdapter.replaceData(users);
            } else {
                mModeAdapter.addData(users);
            }
        }
    }
}
