package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.common.domain.Area;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IAreaController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IAreaService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.AreaServiceImpl;
import com.maimaizaixian.transactiononline.module.hall.adapter.ConditionAreaAdapter;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class AreaActivity extends ActionBarOneActivity implements IAreaController, OnItemClickListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private IAreaService mAreaService;
    private ConditionAreaAdapter mAreaAdapter;

    @Override
    protected void initSubView() {
        setTitleBarHint("简历");
        setTitleBarHeadline("区域");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.color_white_pure));
        mAreaService = new AreaServiceImpl(this);
        mAreaService.getAreaList();
    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_with_title_bar_two;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onCompleteArea(List<Area> areas) {

        List<ConditionAreaAdapter.LocalArea> localAreaList = new ArrayList<>();
        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            List<Area.AreaChild> children = area.getChildren();
            for (int j = 0; j < children.size(); j++) {
                ConditionAreaAdapter.LocalArea localArea = new ConditionAreaAdapter.LocalArea();
                localArea.setProvince(area.getName());
                localArea.setCity(children.get(j).getName());
                localArea.setPid(area.getId());
                localArea.setCid(children.get(j).getId());
                localAreaList.add(localArea);
            }
        }
        mAreaAdapter = new ConditionAreaAdapter(this, localAreaList);
        mAreaAdapter.addOnItemClickListener(this);
        mRecyclerView.setAdapter(mAreaAdapter);

    }

    @Override
    public void click(View v, int position) {
        Intent intent = new Intent();
        intent.putExtra("object", mAreaAdapter.getItem(position));
        setResult(RESULT_OK, intent);
        finish();
    }
}
