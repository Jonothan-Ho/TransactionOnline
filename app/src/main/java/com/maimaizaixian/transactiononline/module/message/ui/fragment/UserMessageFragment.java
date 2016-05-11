package com.maimaizaixian.transactiononline.module.message.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.message.adapter.UserMessageAdapter;
import com.maimaizaixian.transactiononline.module.message.domain.UserMessage;
import com.maimaizaixian.transactiononline.module.message.domain.impl.UserMessageImpl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMessageFragment extends BaseMenuFragment {


    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;


    @Override
    public void initSubView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setBackgroundColor(getActivity().getResources().getColor(R.color.color_white_pure));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        ArrayList<UserMessage> messages = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            messages.add(new UserMessageImpl());
        }

        UserMessageAdapter adapter = new UserMessageAdapter(getActivity(), messages);
        mRecyclerView.setAdapter(adapter);
        
    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_message_user_message;
    }

    @Override
    protected void initListener() {

    }
}
