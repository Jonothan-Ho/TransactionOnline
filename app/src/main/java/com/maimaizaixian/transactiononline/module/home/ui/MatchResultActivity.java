package com.maimaizaixian.transactiononline.module.home.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarFourActivity;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.fragment.DynamicFragmentAdapter;
import com.maimaizaixian.transactiononline.framework.fragment.FragmentTag;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IMerchantController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IMerchantService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.MerchantServiceImpl;
import com.maimaizaixian.transactiononline.module.home.popupwindow.PageModePopupwindow;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.MatchResultListFragment;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.MatchResultMapFragment;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.MatchResultSinglePageFragment;
import com.maimaizaixian.transactiononline.module.me.ui.HistoryPublishActivity;
import com.maimaizaixian.transactiononline.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MatchResultActivity extends ActionBarFourActivity implements IMerchantController, BasePopupWindow.OnDismissListener, OnItemClickListener {

    @ViewInject(R.id.radio_group)
    private RadioGroup mRadioGroup;

    @ViewInject(R.id.ibtn_mode)
    private ImageButton mBtnMode;

    @ViewInject(R.id.radio_button1)
    private RadioButton mBtnTime;

    @ViewInject(R.id.radio_button3)
    private RadioButton mBtnScale;
    private boolean isDesc = false; //desc scale
    private int mIconRes;

    private ArrayList<FragmentTag> mFragments;
    private DynamicFragmentAdapter mDynamicFragmentAdapter;

    private PageModePopupwindow mModePopupwindow;

    private Business mBusiness;
    private IMerchantService mMerchantService;

    public int pageIndex = 1;
    private List<User> usersSource;

    private String order_name = IMerchantService.ORDER_SCALE;
    private String order_type = IMerchantService.ORDER_DESC;


    @Override
    protected void initSubView() {
        setTitleText("匹配结果");
        setCancelText("首页");
        setBackground(R.color.color_white_pure);

        mBusiness = (Business) getIntent().getSerializableExtra("object");
        mMerchantService = new MerchantServiceImpl(this);

        mBtnTime.setVisibility(View.GONE);
        mModePopupwindow = new PageModePopupwindow(this);
        mModePopupwindow.setOnDismissListener(this);
        mModePopupwindow.setItemClickListener(this);

        mFragments = new ArrayList<>();
        //mFragments.add(BaseFragment.newInstance(MatchResultListFragment.class, null));
        //mFragments.add(BaseFragment.newInstance(MatchResultMapFragment.class, null));
        //mFragments.add(BaseFragment.newInstance(MatchResultSinglePageFragment.class, null));


        mFragments.add(new FragmentTag(null, MatchResultListFragment.class, null));
        mFragments.add(new FragmentTag("map", MatchResultMapFragment.class, null));
        mFragments.add(new FragmentTag(null, MatchResultSinglePageFragment.class, null));

        mDynamicFragmentAdapter = new DynamicFragmentAdapter(this, mFragments, R.id.frame_content, getSupportFragmentManager());
        mDynamicFragmentAdapter.loadFragment(0);

        usersSource = new ArrayList<>();

        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            fragments.get(i).onResume();
        }
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_home_match_result;
    }

    @Override
    protected void initListener() {
        mBtnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnMode.setSelected(true);
                mModePopupwindow.showAsDropDown(v);
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != R.id.radio_button3) {
                    setScaleIcon(R.mipmap.icon_order_normal);
                    if (isDesc) {
                        isDesc = false;
                    }
                }

                switch (checkedId) {
                    case R.id.radio_button1:
                        pageIndex = 1;
                        order_name = IMerchantService.ORDER_TIME;
                        getData();
                        break;
                    case R.id.radio_button2:
                        pageIndex = 1;
                        order_name = IMerchantService.ORDER_DISTANCE;
                        getData();
                        break;
                    case R.id.radio_button3:
                        break;
                    case R.id.radio_button4:
                        break;
                    case R.id.radio_button5:
                        break;
                }


            }
        });

        mBtnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDesc = !isDesc;
                order_name = IMerchantService.ORDER_SCALE;
                if (isDesc) {
                    setScaleIcon(R.mipmap.icon_order_desc);
                    order_type = IMerchantService.ORDER_DESC;
                } else {
                    setScaleIcon(R.mipmap.icon_order_asc);
                    order_type = IMerchantService.ORDER_ASC;
                }
                pageIndex = 1;
                getData();
            }
        });
    }

    /**
     * @param page
     */
    public void setPage(int page) {
        pageIndex = page;
    }


    /**
     * @param resource
     */
    private void setScaleIcon(int resource) {
        if (mIconRes == resource) {
            return;
        }
        mIconRes = resource;
        Drawable drawable = getResources().getDrawable(mIconRes);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mBtnScale.setCompoundDrawables(null, null, drawable, null);
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
                mBtnMode.setImageResource(R.drawable.selector_btn_single_page_with_arrow_mode);
                break;
            case 2:
                mBtnMode.setImageResource(R.drawable.selector_btn_map_page_with_arrow_mode);
                break;
        }


    }

    @Override
    public void onDismiss(PopupWindow popupWindow) {
        mBtnMode.setSelected(false);
    }

    @Override
    public void click(View v, int position) {
        if (position == 0) {
            mBtnTime.setVisibility(View.GONE);
        } else {
            if (!mBtnTime.isShown()) {
                mBtnTime.setVisibility(View.VISIBLE);
            }
        }
        setBtnMode(position);
        mDynamicFragmentAdapter.loadFragment(position);
        mModePopupwindow.closePopupWindow();
    }

    @Override
    public void onActionClick(View view) {
        Intent intent = new Intent(this, HistoryPublishActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCompleteMerchants(List<User> users, int page) {

        if (page == 1) {
            usersSource.clear();
        }
        usersSource.addAll(users);

        this.pageIndex = page + 1;
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            OnFragmentArgumentListener fragment = (OnFragmentArgumentListener) fragments.get(i);
            fragment.onComplete(users, page);
        }
    }

    @Override
    public void onCompleteMerchantInfo() {

    }


    /**
     * @return
     */
    public List<User> getDataSource() {
        return usersSource;
    }


    /**
     *
     */
    public void getData() {
        //mMerchantService.getMerchantList(IMerchantService.TYPE_SCALE, mBusiness.getCid(), mBusiness.getRecommend_num(),1);
        LogUtil.printf("ordername-------------------" + order_name);
        mMerchantService.getMerchantList(IMerchantService.TYPE_SCALE, "", 0, pageIndex, order_name, order_type);

    }


}
