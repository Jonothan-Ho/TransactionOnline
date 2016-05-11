package com.maimaizaixian.transactiononline.module.neighborhood;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.framework.fragment.DynamicFragmentAdapter;
import com.maimaizaixian.transactiononline.framework.fragment.FragmentTag;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentRefreshListener;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.hall.ui.fragment.IndexListFragment;
import com.maimaizaixian.transactiononline.module.hall.ui.fragment.IndexSinglePageFragment;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.MatchResultMapFragment;
import com.maimaizaixian.transactiononline.module.neighborhood.popupwindow.PageModePopupwindow;
import com.maimaizaixian.transactiononline.module.neighborhood.ui.fragment.ListFragment;
import com.maimaizaixian.transactiononline.module.neighborhood.ui.fragment.MapFragment;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class NeighborhoodIndexFragment extends BaseMenuFragment implements OnFragmentRefreshListener<Business>, IBusinessController, View.OnClickListener, OnItemClickListener, BasePopupWindow.OnDismissListener {

    @ViewInject(R.id.edit_query)
    private EditText mEditQuery;
    @ViewInject(R.id.ibtn_mode)
    private ImageButton mBtnMode;
    @ViewInject(R.id.tv_title)
    private TextView mTextTitle;

    private ArrayList<FragmentTag> mFragments;
    private DynamicFragmentAdapter mDynamicFragmentAdapter;

    private PageModePopupwindow mModePopupwindow;
    private IBusinessService mBusinessService;
    private String mTitle;
    private int pageIndex = 1;
    private ArrayList<Business> mDataSource;

    @Override
    public void initSubView() {
        mFragments = new ArrayList<>();
        //mFragments.add(BaseFragment.newInstance(ListFragment.class, null));
        //mFragments.add(BaseFragment.newInstance(MapFragment.class, null));
        mFragments.add(new FragmentTag(null, ListFragment.class, null));
        mFragments.add(new FragmentTag(null, MapFragment.class, null));
        mDynamicFragmentAdapter = new DynamicFragmentAdapter(getActivity(), mFragments, R.id.frame_content, getChildFragmentManager());
        mDynamicFragmentAdapter.loadFragment(0);

        mModePopupwindow = new PageModePopupwindow(getActivity());
        mModePopupwindow.setOnDismissListener(this);
        mModePopupwindow.setItemClickListener(this);

        mDataSource = new ArrayList<>();
        mBusinessService = new BusinessServiceImpl(this);
        getData();
    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_neighborhood_index;
    }

    @Override
    protected void initListener() {
        mBtnMode.setOnClickListener(this);
        mEditQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getActivity()
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    mTitle = mEditQuery.getText().toString().trim();
                    setPage(1);
                    getData();
                }

                return false;
            }
        });
    }


    /**
     * @param position
     */
    private void setBtnMode(int position) {
        switch (position) {
            case 0:
                mBtnMode.setImageResource(R.drawable.selector_btn_list_page_with_arrow_mode);
                break;
            case 1:
                mBtnMode.setImageResource(R.drawable.selector_btn_map_page_with_arrow_mode);
                break;
        }

    }

    @Override
    public void click(View v, int position) {
        switch (v.getId()) {
            case R.id.tv_map:
                mEditQuery.setVisibility(View.GONE);
                mTextTitle.setVisibility(View.VISIBLE);
                loadFragment(position);
                break;
            case R.id.tv_list:
                mEditQuery.setVisibility(View.VISIBLE);
                mTextTitle.setVisibility(View.GONE);
                loadFragment(position);
                break;
        }
    }

    /**
     * @param position
     */
    private void loadFragment(int position) {
        setBtnMode(position);
        mDynamicFragmentAdapter.loadFragment(position);
        mModePopupwindow.closePopupWindow();
    }


    @Override
    public void onDismiss(PopupWindow popupWindow) {
        mBtnMode.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_mode:
                mBtnMode.setSelected(true);
                mModePopupwindow.showAsDropDown(v);
                break;
        }
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business, Business_Action action) {

    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {
        pageIndex = page + 1;
        if (page == 1) {
            mDataSource.clear();
        }

        mDataSource.addAll(business);
        List<android.support.v4.app.Fragment> fragments = getChildFragmentManager().getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            OnFragmentArgumentListener fragment = (OnFragmentArgumentListener) fragments.get(i);
            fragment.onComplete(business, page);
        }
    }

    @Override
    public void onComplete(Business_Type type) {

    }

    @Override
    public void getData() {
        mBusinessService.getBusinessList(mTitle, "0", "0", pageIndex);
    }

    @Override
    public void setPage(int page) {
        pageIndex = page;
    }

    @Override
    public List<Business> getDataSource() {
        return mDataSource;
    }
}
