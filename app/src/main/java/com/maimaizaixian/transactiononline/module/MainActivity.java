package com.maimaizaixian.transactiononline.module;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.NoBarActivity;
import com.maimaizaixian.transactiononline.framework.fragment.FragmentTabHost;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.account.mvc.controller.IAccountController;
import com.maimaizaixian.transactiononline.module.account.mvc.service.IAccountService;
import com.maimaizaixian.transactiononline.module.account.mvc.service.impl.AccountServiceImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IMService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.IMServiceImpl;
import com.maimaizaixian.transactiononline.module.hall.HallIndexFragment;
import com.maimaizaixian.transactiononline.module.home.HomeIndexFragment;
import com.maimaizaixian.transactiononline.module.me.MeIndexFragment;
import com.maimaizaixian.transactiononline.module.message.MessageIndexFragment;
import com.maimaizaixian.transactiononline.module.neighborhood.NeighborhoodIndexFragment;
import com.maimaizaixian.transactiononline.utils.CommonUtil;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

public class MainActivity extends NoBarActivity implements IAccountController<User>, EMCallBack {

    @ViewInject(android.R.id.tabhost)
    private FragmentTabHost mTabHost;

    public static final String mStrMenu[] = {"首页", "大厅", "附近", "消息", "我"};
    public static final int mIconMenu[] = {R.drawable.selector_menu_home, R.drawable.selector_menu_hall, R.drawable.selector_menu_neighborhood, R.drawable.selector_menu_message, R.drawable.selector_menu_me};
    public static final Class<?> mFragmentMenu[] = {HomeIndexFragment.class, HallIndexFragment.class, NeighborhoodIndexFragment.class, MessageIndexFragment.class, MeIndexFragment.class};

    private IAccountService mAccountService;
    private IMService imService;

    @Override
    protected void initSubView() {
        mAccountService = new AccountServiceImpl(this);
        imService = new IMServiceImpl(this);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.frame_content);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mStrMenu.length; i++) {
            View view = View.inflate(this, R.layout.view_main_menu_tab_item, null);
            TextView textName = (TextView) view.findViewById(R.id.tv_name);
            ImageView imgIcon = (ImageView) view.findViewById(R.id.iv_image);
            textName.setText(mStrMenu[i]);
            imgIcon.setImageResource(mIconMenu[i]);
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mStrMenu[i]);
            tabSpec.setIndicator(view);
            mTabHost.addTab(tabSpec, mFragmentMenu[i], null);
        }

        loginByBg();
    }

    @Override
    protected void onNewIntent(Intent intent) {
       /* User user = PreferencesUtil.getInstance(this).get(User.class);
        if (user != null) {
            imService.login(user.getId(), user.getRing_password(), this);
        }*/
        loginByBg();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (int i = 0; i < mFragmentMenu.length; i++) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(mStrMenu[i]);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {
        mTabHost.setOnTabChangedListener(new FragmentTabHost.OnTabChangeListener() {
            @Override
            public boolean onTabChange(String tabId) {
                if (tabId != mStrMenu[0]) {
                    if (!CommonUtil.isLogged(MainActivity.this)) {
                        //mTabHost.getCurrentTabView().setSelected(false);
                        mTabHost.setCurrentTab(0);
                        //mTabHost.getTabWidget().getChildTabViewAt(0).setSelected(true);
                        // mTabHost.onTabChanged(mStrMenu[0]);
                        return false;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void completeValidCode(String code) {

    }

    @Override
    public void complete(User user) {
        PreferencesUtil.getInstance(this).put(user, User.class);
        imService.login(user.getId(), user.getRing_password(), this);
    }

    @Override
    public void closeActivity() {

    }


    /**
     *
     */
    private void loginByBg() {
        String username = PreferencesUtil.getInstance(this).get(PreferencesUtil.KEY_USERNAME);
        String passwd = PreferencesUtil.getInstance(this).get(PreferencesUtil.KEY_PASSWORD);
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(passwd)) {
            mAccountService.login(username, passwd);
        }
    }


    @Override
    public void onSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();
                ViewUtil.showSnackbar(getRootView(), "登录聊天服务器成功");
            }
        });
    }

    @Override
    public void onError(final int i, final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewUtil.showSnackbar(getRootView(), "登录聊天服务器失败:" + i + "==>" + s);
            }
        });
    }

    @Override
    public void onProgress(int i, String s) {
    }
}
