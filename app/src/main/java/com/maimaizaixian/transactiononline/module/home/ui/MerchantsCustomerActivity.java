package com.maimaizaixian.transactiononline.module.home.ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.common.domain.Product;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IProductController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IProductService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.ProductServiceImpl;
import com.maimaizaixian.transactiononline.module.home.adapter.MerchantsAdapter;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.Merchants;
import com.maimaizaixian.transactiononline.module.home.domain.impl.MerchantsImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */
public class MerchantsCustomerActivity extends ActionBarOneActivity implements OnItemClickListener,IProductController {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private MerchantsAdapter mMerchantsAdapter;

    private int pageIndex = 1;
    private IProductService mProductService;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("案例展示");
        setTitleBarHint("商户主页");

        mRecyclerView.setPadding(10, 0, 10, 0);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));

        String id = getIntent().getStringExtra("id");

        mProductService = new ProductServiceImpl(this);
        mProductService.getProductList(IProductService.MODEL_CASE, id, pageIndex);

    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_with_title_bar_one;
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void click(View v, int position) {
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra("object", mMerchantsAdapter.getItem(position));
        detailsIntent.putExtra(DetailsActivity.FLAG_HINT, "案例展示");
        detailsIntent.putExtra(DetailsActivity.FLAG_DETAILS, "工业产品介绍");
        startActivity(detailsIntent);
    }

    @Override
    public void onCompleteArray(List<Product> products, int page) {
        pageIndex = page + 1;
        if (mMerchantsAdapter == null) {
            mMerchantsAdapter = new MerchantsAdapter(this, products);
            mMerchantsAdapter.addOnItemClickListener(this);
            mRecyclerView.setAdapter(mMerchantsAdapter);
        } else {
            if (page == 1) {
                mMerchantsAdapter.replaceData(products);
            }else {
                mMerchantsAdapter.addData(products);
            }
        }
    }

    @Override
    public void onComplete(Type type) {

    }

    @Override
    public void onComplete(Product product) {

    }
}
