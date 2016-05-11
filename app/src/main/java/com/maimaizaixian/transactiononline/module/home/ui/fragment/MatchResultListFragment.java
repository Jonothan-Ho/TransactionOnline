package com.maimaizaixian.transactiononline.module.home.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseNormalFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IMerchantService;
import com.maimaizaixian.transactiononline.module.home.adapter.ResultListAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.MatchResult;
import com.maimaizaixian.transactiononline.module.home.domain.impl.MatchResultImpl;
import com.maimaizaixian.transactiononline.module.home.ui.MatchResultActivity;
import com.maimaizaixian.transactiononline.module.home.ui.MerchantsHomeActivity;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MatchResultListFragment extends BaseNormalFragment implements OnItemClickListener, OnFragmentArgumentListener<User> {

    @ViewInject(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;

    private ResultListAdapter mResultListAdapter;

    private List<User> usersSource;

    @Override
    public void initSubView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_home_match_result_list;
    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnLoadListener(new AppSwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onPullUp() {
                ((MatchResultActivity) getActivity()).getData();
            }

            @Override
            public void onPullDown() {
                ((MatchResultActivity) getActivity()).setPage(1);
                ((MatchResultActivity) getActivity()).getData();
            }
        });
    }

    @Override
    public void click(View v, int position) {
        Intent merchantsIntent = new Intent(getActivity(), MerchantsHomeActivity.class);
        merchantsIntent.putExtra("object", usersSource.get(position));
        startActivity(merchantsIntent);
    }

    @Override
    public void onComplete(List<User> users, int page) {
        mSwipeRefreshLayout.stopLoading();

        if (usersSource == null) {
            usersSource = new ArrayList<>();
            usersSource.addAll(users);
            mResultListAdapter = new ResultListAdapter(getActivity(), usersSource);
            mResultListAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mResultListAdapter);
        } else {
            if (page == 1) {
                usersSource.clear();
            }
            usersSource.addAll(users);
            mResultListAdapter.notifyDataSetChanged();
        }
    }
}
