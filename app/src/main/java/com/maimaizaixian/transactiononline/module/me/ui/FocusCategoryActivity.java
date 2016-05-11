package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarFourActivity;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.fragment.DynamicFragmentAdapter;
import com.maimaizaixian.transactiononline.framework.fragment.FragmentTag;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.home.adapter.CategoryAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.home.mvc.controller.ICategoryController;
import com.maimaizaixian.transactiononline.module.home.mvc.service.ICategoryService;
import com.maimaizaixian.transactiononline.module.home.mvc.service.impl.CategoryServiceImpl;
import com.maimaizaixian.transactiononline.module.me.listener.OnFragmentFocusCategoryListener;
import com.maimaizaixian.transactiononline.module.me.ui.fragment.CategoryContentFragment;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class FocusCategoryActivity extends ActionBarFourActivity implements OnItemClickListener, ICategoryController {


    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    private ArrayList<FragmentTag> mFragmentContents;
    private DynamicFragmentAdapter mDynamicFragmentAdapter;

    private ICategoryService mCategoryService;

    @Override
    protected void initSubView() {
        setTitleText("关注类目");
        setActionText("确定");
        setCancelText("完善资料");

        mFragmentContents = new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //Grid Layout
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.color_white_pure));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mCategoryService = new CategoryServiceImpl(this);
        mCategoryService.getCategoryList();

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_focus_category;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void click(View v, int position) {
        mCategoryAdapter.setSelectedItem(position);
        mDynamicFragmentAdapter.loadFragment(position);
    }


    @Override
    public void onActionClick(View view) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        ArrayList<CategoryContent> contents = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            OnFragmentFocusCategoryListener focusCategoryListener = (OnFragmentFocusCategoryListener) fragments.get(i);
            contents.addAll(focusCategoryListener.getFocusCategory());
        }

        if (contents.size() == 0) {
            ViewUtil.showSnackbar(getRootView(), "请至少选择一项分类");
            return;
        }

        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("object", contents);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onCategoryData(List<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("object", categories.get(i));
            //mFragmentContents.add(BaseFragment.newInstance(CategoryContentFragment.class, bundle));
            mFragmentContents.add(new FragmentTag(null, CategoryContentFragment.class, null));
        }
        mDynamicFragmentAdapter = new DynamicFragmentAdapter(this,mFragmentContents, R.id.frame_content, getSupportFragmentManager());
        mCategoryAdapter = new CategoryAdapter(this, categories);
        mCategoryAdapter.addOnItemClickListener(this);
        mRecyclerView.setAdapter(mCategoryAdapter);
    }
}
