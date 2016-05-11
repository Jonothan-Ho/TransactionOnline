package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarFourActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.listener.OnItemDeleteListener;
import com.maimaizaixian.transactiononline.module.common.domain.Product;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IProductController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IProductService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.ProductServiceImpl;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.ProductDisplay;
import com.maimaizaixian.transactiononline.module.home.domain.impl.ProductDisplayImpl;
import com.maimaizaixian.transactiononline.module.home.ui.DetailsActivity;
import com.maimaizaixian.transactiononline.module.me.adapter.ProductDisplayAdapter;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDisplayActivity extends ActionBarFourActivity implements IProductController, OnItemClickListener, OnItemDeleteListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;
    private ProductDisplayAdapter displayAdapter;

    private IProductService mProductService;
    private int pageIndex = 1;
    private int deletePosition;

    @Override
    protected void initSubView() {
        setCancelText("完善资料");
        setActionText("添加");
        setTitleText("我们的产品");

        mRecyclerView.setPadding(3, 0, 3, 0);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //Grid Layout
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        mProductService = new ProductServiceImpl(this);
        getData();


    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_with_title_bar_four;
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

    @Override
    public void onActionClick(View view) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    private void getData() {
        mProductService.getProductList(IProductService.MODEL_PRODUCT, "", pageIndex);
    }

    @Override
    public void onCompleteArray(List<Product> products, int page) {
        mSwipeRefreshLayout.stopLoading();
        pageIndex = page + 1;
        if (displayAdapter == null) {
            displayAdapter = new ProductDisplayAdapter(this, products);
            displayAdapter.addOnItemClickListener(this);
            displayAdapter.addOnItemDeleteListener(this);
            mRecyclerView.setAdapter(displayAdapter);
        } else {
            if (page == 1) {
                displayAdapter.replaceData(products);
            } else {
                displayAdapter.addData(products);
            }
        }
    }

    @Override
    public void onComplete(Type type) {
        displayAdapter.removeData(deletePosition);
    }

    @Override
    public void onComplete(Product product) {

    }


    @Override
    public void click(View v, int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("object", displayAdapter.getItem(position));
        startActivity(intent);
    }

    @Override
    public void delete(View v, int position) {
        deletePosition = position;
        mProductService.deleteProduct(displayAdapter.getItem(position).getId());
    }
}
