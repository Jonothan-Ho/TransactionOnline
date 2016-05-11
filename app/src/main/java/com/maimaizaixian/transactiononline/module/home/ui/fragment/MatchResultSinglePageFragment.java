package com.maimaizaixian.transactiononline.module.home.ui.fragment;


import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseNormalFragment;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.home.adapter.MatchResultSinglePageAdapter;
import com.maimaizaixian.transactiononline.module.home.ui.MatchResultActivity;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.view.CardContainer;
import com.maimaizaixian.transactiononline.view.domain.CardModel;

import java.util.List;

/**
 */
public class MatchResultSinglePageFragment extends BaseNormalFragment implements OnFragmentArgumentListener<User> {

    @ViewInject(R.id.card_view)
    private CardContainer mCardContainer;
    private MatchResultSinglePageAdapter adapter;


    @Override
    public void initSubView() {

        List<User> list = ((MatchResultActivity) getActivity()).getDataSource();
        if (list.size() > 0) {
            adapter = new MatchResultSinglePageAdapter(getActivity());
            adapter.addAll(list);
            mCardContainer.setAdapter(adapter);
        }


    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_home_match_result_single_page;
    }

    @Override
    protected void initListener() {
        mCardContainer.setOnDismissListener(new CardContainer.OnDismissListener() {
            @Override
            public void onDismiss(int position) {
                ((MatchResultActivity) getActivity()).getData();
            }
        });
    }

    @Override
    public void onComplete(List<User> users, int page) {

        if (adapter == null && users.size() > 0) {
            adapter = new MatchResultSinglePageAdapter(getActivity());
            adapter.addAll(users);
        } else {
            if (page == 1) {
                adapter.replaceAll(users);
            } else {
                adapter.addAll(users);
            }

        }

        mCardContainer.setAdapter(adapter);


    }
}
