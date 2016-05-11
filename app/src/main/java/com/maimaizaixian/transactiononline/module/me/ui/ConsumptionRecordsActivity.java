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
import com.maimaizaixian.transactiononline.module.hall.ui.DetailsActivity;
import com.maimaizaixian.transactiononline.module.me.adapter.ConsumptionRecordsAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IConsumptionRecordsController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IConsumptionRecordsService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.ConsumptionRecordsServiceImpl;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.List;

public class ConsumptionRecordsActivity extends ActionBarOneActivity implements OnItemClickListener, IConsumptionRecordsController {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mRefreshLayout;

    private ConsumptionRecordsAdapter mAdapter;
    private IConsumptionRecordsService mConsumptionRecordsService;
    private int pageIndex = 1;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("消费记录");
        setTitleBarHint("我");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        mConsumptionRecordsService = new ConsumptionRecordsServiceImpl(this);
        getData();
    }

    private void getData() {
        mConsumptionRecordsService.getConsumptionRecordsList(pageIndex);
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

    @Override
    public void click(View v, int position) {

        ConsumptionRecords records = mAdapter.getItem(position);
        Intent intent = new Intent();
        intent.setClass(this, DetailsActivity.class);
        intent.putExtra("id", records.getChance_id());
        startActivity(intent);

    }

    @Override
    public void onComplete(List<ConsumptionRecords> recordses, int page) {
        pageIndex = page + 1;
        if (mAdapter == null) {
            mAdapter = new ConsumptionRecordsAdapter(this, recordses);
            mAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            if (page == 1) {
                mAdapter.replaceData(recordses);
            } else {
                mAdapter.addData(recordses);
            }
        }
    }

    @Override
    public void onComplete(Consumption_Action action) {

    }

    @Override
    public void onComplete(Consumption_Action action, String data) {
        
    }
}
