package com.maimaizaixian.transactiononline.module.home.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseNormalFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.home.adapter.CategoryContentAdapter;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.ArrayList;

/**
 */
public class CategoryContentFragment extends BaseNormalFragment implements OnItemClickListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private CategoryContentAdapter mContentAdapter;
    private Category mCategory;


    @Override
    public void initSubView() {
        Bundle bundle = getArguments();
        mCategory = (Category) bundle.getSerializable("object");

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); //Grid Layout
       /* ArrayList<CategoryContent> beans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            beans.add(new CategoryContent());
        }*/

        mContentAdapter = new CategoryContentAdapter(getActivity(), mCategory.getChild());
        mContentAdapter.addOnItemClickListener(this);
        mRecyclerView.setAdapter(mContentAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));

    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_home_category_content;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void click(View v, int position) {
        Intent intent = new Intent();
        ViewUtil.showSnackbar(getRootView(), mCategory.getChild().get(position) + "==");
        intent.putExtra("object", mCategory.getChild().get(position));
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}
