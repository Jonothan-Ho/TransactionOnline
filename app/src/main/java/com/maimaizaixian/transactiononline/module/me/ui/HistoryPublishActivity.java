package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarFourActivity;
import com.maimaizaixian.transactiononline.framework.adapter.FragmentAdapter;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.module.me.ui.fragment.BuyFragment;
import com.maimaizaixian.transactiononline.module.me.ui.fragment.HistoryAllFragment;
import com.maimaizaixian.transactiononline.module.me.ui.fragment.LeaseFragment;
import com.maimaizaixian.transactiononline.module.me.ui.fragment.SellFragment;
import com.maimaizaixian.transactiononline.module.me.ui.fragment.WantedFragment;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.view.NoScrollViewPager;

import java.util.ArrayList;

public class HistoryPublishActivity extends ActionBarFourActivity {

    @ViewInject(R.id.tabLayout)
    private TabLayout mTabLayout;

    @ViewInject(R.id.viewpager_native)
    private NoScrollViewPager mViewPager;

    @ViewInject(R.id.layout_edit)
    private RelativeLayout mLayoutEdit;

    @ViewInject(R.id.btn_delete)
    private Button mBtnDelete;
    @ViewInject(R.id.btn_close)
    private Button mBtnClose;
    @ViewInject(R.id.checkbox)
    private CheckBox mCheckBox;

    private OnFragmentInteractionListener mCurrentFragment;

    public static final String TAB_NAME[] = {"全部", "买", "卖", "出租", "求租"};
    public static final Class<?> TAB_FRAGMENT[] = {HistoryAllFragment.class, BuyFragment.class, SellFragment.class, WantedFragment.class, LeaseFragment.class};

    private FragmentAdapter mFragmentAdapter;

    private boolean isEdit = false;


    @Override
    protected void initSubView() {
        setCancelText("我");
        setTitleText("历史发布");
        setActionText("编辑");

        mViewPager.setOffscreenPageLimit(TAB_FRAGMENT.length);

        ArrayList<FragmentAdapter.FragmentDomain> fragments = new ArrayList<>();
        for (int i = 0; i < TAB_NAME.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(TAB_NAME[i]));
            Fragment fragment = (Fragment) BaseFragment.newInstance(TAB_FRAGMENT[i], null);
            FragmentAdapter.FragmentDomain domain = new FragmentAdapter.FragmentDomain(TAB_NAME[i], fragment);
            fragments.add(domain);
        }


        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.color_main));
        mTabLayout.setTabTextColors(getResources().getColor(R.color.color_black_dark), getResources().getColor(R.color.color_main));

        mViewPager.setAdapter(mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments));
        mTabLayout.setupWithViewPager(mViewPager);

        mCurrentFragment = (OnFragmentInteractionListener) mFragmentAdapter.getItem(0);

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_history_publish;
    }

    @Override
    protected void initListener() {

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                OnFragmentInteractionListener currentFragment = (OnFragmentInteractionListener) mFragmentAdapter.getItem(position);
                if (currentFragment != mCurrentFragment) {
                    isEdit = false;
                    mCurrentFragment.onFragmentInteraction(isEdit);
                }

                mViewPager.setCurrentItem(position);
                mCurrentFragment = currentFragment;

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCurrentFragment.onAllChecked(isChecked);
            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentFragment.closeMessage();
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentFragment.deleteMessage();
            }
        });


    }

    @Override
    public void onActionClick(View view) {
        isEdit = !isEdit;
        if (isEdit) {
            mLayoutEdit.setVisibility(View.VISIBLE);
            mTextMore.setText("完成");
        } else {
            mLayoutEdit.setVisibility(View.GONE);
            mTextMore.setText("编辑");
        }

        LogUtil.printf("==>" + mCurrentFragment);
        mCurrentFragment.onFragmentInteraction(isEdit);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(boolean isEdit);

        void onAllChecked(boolean isChecked);

        void deleteMessage();

        void closeMessage();

    }

}
