package com.maimaizaixian.transactiononline.module.home.dialog;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.layoutmanager.WarpGridLayoutManager;
import com.maimaizaixian.transactiononline.framework.dialog.BaseDialog;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.home.adapter.MultiCategoryAdapter;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class MultiCategoryDialog extends BaseDialog {

    private RecyclerView mRecyclerView;
    private List<CategoryContent> mCategories;
    private OnItemClickListener mItemClickListener;
    private MultiCategoryAdapter categoryAdapter;

    public MultiCategoryDialog(Activity context, OnItemClickListener itemClickListener) {
        super(context);
        this.mItemClickListener = itemClickListener;
        mCategories = new ArrayList<>();
    }

    public MultiCategoryDialog(Activity context, List<CategoryContent> categoryContents,OnItemClickListener itemClickListener) {
        super(context);
        this.mItemClickListener = itemClickListener;
        this.mCategories = categoryContents;
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_home_multi_category;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        categoryAdapter = new MultiCategoryAdapter(activity, mCategories);
        categoryAdapter.addOnItemClickListener(mItemClickListener);
        mRecyclerView.setLayoutManager(new WarpGridLayoutManager(activity, 3, categoryAdapter.getItemCount(), 20 * 2));
        mRecyclerView.setAdapter(categoryAdapter);
    }

    /**
     * @param categories
     */
    public void setCategories(List<CategoryContent> categories) {
        this.mCategories = categories;
        if (categoryAdapter == null) {
            categoryAdapter = new MultiCategoryAdapter(activity, mCategories);
            categoryAdapter.addOnItemClickListener(mItemClickListener);
            mRecyclerView.setAdapter(categoryAdapter);
        } else {
            categoryAdapter.replaceData(categories);
        }

    }


    /**
     * @param position
     * @return
     */
    public CategoryContent get(int position) {
        return mCategories.get(position);
    }

}
