package com.maimaizaixian.transactiononline.module.message;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.easemob.chat.EMConversation;
import com.easemob.easeui.ui.EaseConversationListFragment;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.FragmentAdapter;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.module.common.ui.ChatActivity;
import com.maimaizaixian.transactiononline.module.message.ui.fragment.BusinessPushFragment;

import java.util.ArrayList;

/**
 */
public class MessageIndexFragment extends BaseMenuFragment implements EaseConversationListFragment.EaseConversationListItemClickListener{

    @ViewInject(R.id.tabLayout)
    private TabLayout mTabLayout;
    @ViewInject(R.id.viewpager_native)
    private ViewPager mViewPager;

    public static final String TAB_NAME[] = {"用户消息", "商机推送"};
    public static final Class<?> TAB_FRAGMENT[] = {EaseConversationListFragment.class, BusinessPushFragment.class};

    @Override
    protected int getCustomView() {
        return R.layout.fragment_message_index;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initSubView() {
        mViewPager.setOffscreenPageLimit(TAB_FRAGMENT.length);

        ArrayList<FragmentAdapter.FragmentDomain> fragments = new ArrayList<>();
        for (int i = 0; i < TAB_NAME.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(TAB_NAME[i]));
            android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) BaseFragment.newInstance(TAB_FRAGMENT[i], null);
            if (fragment instanceof EaseConversationListFragment) {
                ((EaseConversationListFragment) fragment).setConversationListItemClickListener(this);
            }
            FragmentAdapter.FragmentDomain domain = new FragmentAdapter.FragmentDomain(TAB_NAME[i], fragment);
            fragments.add(domain);
        }




        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.color_main));
        mTabLayout.setTabTextColors(getResources().getColor(R.color.color_black_dark), getResources().getColor(R.color.color_main));

        mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void onListItemClicked(EMConversation conversation) {
        Intent talkIntent = new Intent(getActivity(), ChatActivity.class);
        //talkIntent.putExtra("id", business.getUid());
        talkIntent.putExtra("id", conversation.getUserName());
        startActivity(talkIntent);
    }
}
