package com.maimaizaixian.transactiononline.module.hall.popupwindow;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;
import com.maimaizaixian.transactiononline.module.hall.adapter.ConditionAreaAdapter;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.hall.domain.Area;
import com.maimaizaixian.transactiononline.module.hall.domain.impl.AreaImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ConditionAreaPopupWindow extends BasePopupWindow {

    private RecyclerView mRecyclerView;
    private ConditionAreaAdapter mAreaAdapter;

    public ConditionAreaPopupWindow(Activity activity) {
        super(activity);
        if (activity instanceof OnItemClickListener) {
            mItemClickListener = (OnItemClickListener) activity;
        }
    }

    @Override
    protected int getCustomView() {
        return R.layout.popupwindow_hall_condition_area;
    }

    @Override
    protected int getWindowWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getWindowHeight() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public int getPopupWindowAnim() {
        return R.style.popupWindowAnimTwo;
    }

    @Override
    protected void initView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        mRecyclerView.setBackgroundColor(mActivity.getResources().getColor(R.color.color_white_pure));
    }

    /**
     * @param itemClickListener
     */
    public void addOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setData(List<ConditionAreaAdapter.LocalArea> areas) {
        mAreaAdapter = new ConditionAreaAdapter(mActivity, areas);
        mAreaAdapter.addOnItemClickListener(mItemClickListener);
        mRecyclerView.setAdapter(mAreaAdapter);
    }


    /**
     * @param postion
     * @return
     */
    public ConditionAreaAdapter.LocalArea getItem(int postion) {
        return mAreaAdapter.getItem(postion);
    }


}
