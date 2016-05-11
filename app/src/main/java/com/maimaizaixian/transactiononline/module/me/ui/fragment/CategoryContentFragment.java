package com.maimaizaixian.transactiononline.module.me.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseNormalFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.me.adapter.CategoryContentAdapter;
import com.maimaizaixian.transactiononline.module.me.listener.OnFragmentFocusCategoryListener;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CategoryContentFragment extends BaseNormalFragment implements OnItemClickListener, OnFragmentFocusCategoryListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private CategoryContentAdapter mContentAdapter;
    private Category mCategory;


    @Override
    public void initSubView() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        }

        mCategory = (Category) bundle.getSerializable("object");
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); //Grid Layout
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
    }

    @Override
    public List<CategoryContent> getFocusCategory() {
        List<CategoryContent> list = new ArrayList<>();
        SparseArray<Boolean> sparseArray = mContentAdapter.getSparseArray();
        for (int i = 0; i < sparseArray.size(); i++) {
            if (sparseArray.get(i)) {
                list.add(mCategory.getChild().get(i));
            }
        }
        return list;
    }
}
