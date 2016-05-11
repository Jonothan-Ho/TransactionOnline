package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.me.adapter.WithdrawRecordsAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;
import com.maimaizaixian.transactiononline.module.me.domain.WithdrawRecords;
import com.maimaizaixian.transactiononline.module.me.domain.impl.WithdrawRecordsImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IConsumptionRecordsController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IConsumptionRecordsService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.ConsumptionRecordsServiceImpl;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WithdrawRecordsActivity extends ActionBarOneActivity implements IConsumptionRecordsController {

    @ViewInject(R.id.tv_sum)
    private TextView mTextSum;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    private WithdrawRecordsAdapter adapter;
    private IConsumptionRecordsService mService;
    private int pageIndex = 1;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("提现记录");
        setTitleBarHint("代理人设置");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        mService = new ConsumptionRecordsServiceImpl(this);
        mService.getSumWithdrawal();
        getData();
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_withdraw_records;
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

    /**
     *
     */
    private void getData() {
        mService.getWithDrawalRecordsList(pageIndex);
    }

    @Override
    public void onComplete(List<ConsumptionRecords> recordses, int page) {
        if (adapter == null) {
            adapter = new WithdrawRecordsAdapter(this, recordses);
            mRecyclerView.setAdapter(adapter);
        } else {
            if (page == 1) {
                adapter.replaceData(recordses);
            } else {
                adapter.addData(recordses);
            }
        }
    }

    @Override
    public void onComplete(Consumption_Action action) {

    }

    @Override
    public void onComplete(Consumption_Action action, String data) {
        String amount = "";
        try {
            JSONArray array = new JSONArray(data);
            String str = array.getString(0);
            JSONObject object = new JSONObject(str);
            amount = object.getString("amount");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTextSum.setText(amount);
    }
}
