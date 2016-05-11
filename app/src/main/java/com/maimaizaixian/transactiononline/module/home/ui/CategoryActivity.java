package com.maimaizaixian.transactiononline.module.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.fragment.DynamicFragmentAdapter;
import com.maimaizaixian.transactiononline.framework.fragment.FragmentTag;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.home.adapter.CategoryAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.mvc.controller.ICategoryController;
import com.maimaizaixian.transactiononline.module.home.mvc.service.ICategoryService;
import com.maimaizaixian.transactiononline.module.home.mvc.service.impl.CategoryServiceImpl;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.CategoryContentFragment;
import com.maimaizaixian.transactiononline.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * the activity for category
 */
public class CategoryActivity extends ActionBarOneActivity implements OnItemClickListener, ICategoryController {

    public static final String ACTION_HINT = "hint";
    public static final String ACTION_TITLE = "title";

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    private ArrayList<FragmentTag> mFragmentContents;
    private DynamicFragmentAdapter mDynamicFragmentAdapter;
    private List<Category> categories;

    private ICategoryService mCategoryService;

    @Override
    protected void initSubView() {
        String hint = getIntent().getStringExtra(ACTION_HINT);
        String title = getIntent().getStringExtra(ACTION_TITLE);

        if (!TextUtils.isEmpty(hint) && !TextUtils.isEmpty(title)) {
            setTitleBarHint(hint);
            setTitleBarHeadline(title);
        } else {
            setTitleBarHint("发布");
            setTitleBarHeadline("类目");
        }

        mCategoryService = new CategoryServiceImpl(this);
        mCategoryService.getCategoryList();
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_home_category;
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
    public void onCategoryData(List<Category> categories) {
        this.categories = categories;
        if (categories.size() > 0) {
            buildAdapter();
        }
    }

    private void buildAdapter() {
        mFragmentContents = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("object", categories.get(i));
            //mFragmentContents.add(BaseFragment.newInstance(CategoryContentFragment.class, bundle));
            mFragmentContents.add(new FragmentTag(null, CategoryContentFragment.class, bundle));
        }
        mDynamicFragmentAdapter = new DynamicFragmentAdapter(this, mFragmentContents, R.id.frame_content, getSupportFragmentManager());
        mDynamicFragmentAdapter.loadFragment(0);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //Grid Layout
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.color_white_pure));
        mCategoryAdapter = new CategoryAdapter(this, categories);
        mCategoryAdapter.addOnItemClickListener(this);
        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
