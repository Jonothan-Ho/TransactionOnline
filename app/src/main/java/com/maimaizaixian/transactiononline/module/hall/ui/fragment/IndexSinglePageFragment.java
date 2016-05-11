package com.maimaizaixian.transactiononline.module.hall.ui.fragment;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentRefreshListener;
import com.maimaizaixian.transactiononline.module.hall.adapter.SinglePageModeAdapter;
import com.maimaizaixian.transactiononline.module.home.adapter.MatchResultSinglePageAdapter;
import com.maimaizaixian.transactiononline.module.home.ui.MatchResultActivity;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.view.CardContainer;
import com.maimaizaixian.transactiononline.view.domain.CardModel;

import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class IndexSinglePageFragment extends BaseMenuFragment implements CardContainer.OnDismissListener, OnFragmentArgumentListener<Business> {

    @ViewInject(R.id.card_view)
    private CardContainer mCardContainer;
    private SinglePageModeAdapter modeAdapter;
    private OnFragmentRefreshListener refreshListener;


    @Override
    public void initSubView() {
        refreshListener = (OnFragmentRefreshListener) getParentFragment();
        mCardContainer.setOnDismissListener(this);
        List<Business> businesses = refreshListener.getDataSource();
        modeAdapter = new SinglePageModeAdapter(getActivity());

        if (businesses.size() > 0) {
            modeAdapter = new SinglePageModeAdapter(getActivity());
            modeAdapter.addAll(businesses);
            mCardContainer.setAdapter(modeAdapter);
        }

    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_home_match_result_single_page;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onDismiss(int position) {
        refreshListener.getData();
        //LogUtil.printf("===>" + position);
    }

    @Override
    public void onComplete(List<Business> users, int page) {
        if (modeAdapter == null && users.size() > 0) {
            modeAdapter = new SinglePageModeAdapter(getActivity());
            modeAdapter.addAll(users);
        } else {
            if (page == 1) {
                modeAdapter.replaceAll(users);
            } else {
                modeAdapter.addAll(users);
            }

        }

        mCardContainer.setAdapter(modeAdapter);
    }
}
