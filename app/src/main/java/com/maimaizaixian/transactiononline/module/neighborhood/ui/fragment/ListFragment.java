package com.maimaizaixian.transactiononline.module.neighborhood.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentRefreshListener;
import com.maimaizaixian.transactiononline.module.neighborhood.adapter.ListDataAdapter;
import com.maimaizaixian.transactiononline.module.neighborhood.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.neighborhood.ui.DetailsActivity;
import com.maimaizaixian.transactiononline.module.neighborhood.ui.PublishActivity;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseMenuFragment implements OnItemClickListener, OnFragmentArgumentListener<Business> {

    @ViewInject(R.id.btn_action)
    private Button mBtnPublish;
    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;
    private OnFragmentRefreshListener mFragmentRefreshListener;


    private ListDataAdapter mListAdapter;


    @Override
    protected int getCustomView() {
        return R.layout.fragment_neighborhood_list;
    }

    @Override
    protected void initListener() {
        mBtnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishActivity.class);
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnLoadListener(new AppSwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onPullUp() {
                mFragmentRefreshListener.getData();
            }

            @Override
            public void onPullDown() {
                mFragmentRefreshListener.setPage(1);
                mFragmentRefreshListener.getData();
            }
        });
    }

    @Override
    public void initSubView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        mFragmentRefreshListener = (OnFragmentRefreshListener) getParentFragment();

    }


    @Override
    public void click(View v, int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("object", mListAdapter.getItem(position));
        startActivity(intent);
    }

    @Override
    public void onComplete(List<Business> data, int page) {
        mSwipeRefreshLayout.stopLoading();
        if (mListAdapter == null) {
            mListAdapter = new ListDataAdapter(getActivity(), data);
            mListAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mListAdapter);
        } else {
            if (page == 1) {
                mListAdapter.replaceData(data);
            } else {
                mListAdapter.addData(data);
            }
        }
    }
}
