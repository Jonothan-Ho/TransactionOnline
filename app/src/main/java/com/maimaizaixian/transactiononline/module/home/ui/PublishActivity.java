package com.maimaizaixian.transactiononline.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.NoBarActivity;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.adapter.FragmentAdapter;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.BuyFragment;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.RentFragment;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.RentOutFragment;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.SellFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Homepage module publish pages
 * Open this page by HomePage
 *
 * @see com.maimaizaixian.transactiononline.module.home.HomeIndexFragment
 */
public class PublishActivity extends NoBarActivity {

    public static final String TAB_NAME[] = {"买", "卖", "求租", "出租"};
    public static final Class<?> TAB_FRAGMENT[] = {BuyFragment.class, SellFragment.class, RentFragment.class, RentOutFragment.class};

    @ViewInject(R.id.tabLayout)
    private TabLayout mTabLayout;
    @ViewInject(R.id.viewpager_native)
    private ViewPager mViewPager;
    @ViewInject(R.id.tv_back)
    private TextView mTextBack;


    @Override
    protected void initSubView() {

        mViewPager.setOffscreenPageLimit(TAB_FRAGMENT.length);

        Business business = (Business) getIntent().getSerializableExtra("object");
        Bundle bundle = null;
        if (business != null) {
            bundle = new Bundle();
            bundle.putSerializable("object", business);
        }

        ArrayList<FragmentAdapter.FragmentDomain> fragments = new ArrayList<>();
        for (int i = 0; i < TAB_NAME.length; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setText(TAB_NAME[i]);
            mTabLayout.addTab(tab);
            Fragment fragment = (Fragment) BaseFragment.newInstance(TAB_FRAGMENT[i], bundle);
            FragmentAdapter.FragmentDomain domain = new FragmentAdapter.FragmentDomain(TAB_NAME[i], fragment);
            fragments.add(domain);
        }


        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.color_main));
        mTabLayout.setTabTextColors(getResources().getColor(R.color.color_black_dark), getResources().getColor(R.color.color_main));

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        mTabLayout.setupWithViewPager(mViewPager);

        if (business != null) {
            switch (business.getModel_alias()) {
                case IBusinessService.MODE_BUY:
                    mTabLayout.getTabAt(0).select();
                    break;
                case IBusinessService.MODE_SELL:
                    mTabLayout.getTabAt(1).select();
                    break;
                case IBusinessService.MODE_RENT:
                    mTabLayout.getTabAt(2).select();
                    break;
                case IBusinessService.MODE_RENTOUT:
                    mTabLayout.getTabAt(3).select();
                    break;
            }
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (int i = 0; i < fragments.size(); i++) {
                fragments.get(i).onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_home_publish;
    }

    @Override
    protected void initListener() {
        mTextBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
