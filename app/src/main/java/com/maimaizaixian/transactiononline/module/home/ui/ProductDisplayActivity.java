package com.maimaizaixian.transactiononline.module.home.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.adapter.ProductDisplayAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.ProductDisplay;
import com.maimaizaixian.transactiononline.module.home.domain.impl.ProductDisplayImpl;

import java.util.ArrayList;

/**
 * product display activity
 */
public class ProductDisplayActivity extends ActionBarOneActivity {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;


    @Override
    protected void initSubView() {
        mRecyclerView.setPadding(3, 0, 3, 0);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //Grid Layout
        ArrayList<ProductDisplay> beans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            beans.add(new ProductDisplayImpl());
        }

        ProductDisplayAdapter displayAdapter = new ProductDisplayAdapter(this, beans);
        mRecyclerView.setAdapter(displayAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_with_title_bar_one;
    }

    @Override
    protected void initListener() {

    }
}
