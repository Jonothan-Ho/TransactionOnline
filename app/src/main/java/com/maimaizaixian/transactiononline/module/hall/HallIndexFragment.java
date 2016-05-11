package com.maimaizaixian.transactiononline.module.hall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.framework.fragment.DynamicFragmentAdapter;
import com.maimaizaixian.transactiononline.framework.fragment.FragmentTag;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;
import com.maimaizaixian.transactiononline.module.common.domain.Area;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentRefreshListener;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IAreaController;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IAreaService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.AreaServiceImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.hall.adapter.ConditionAreaAdapter;
import com.maimaizaixian.transactiononline.module.hall.popupwindow.ConditionAreaPopupWindow;
import com.maimaizaixian.transactiononline.module.hall.popupwindow.ConditionTypePopupWindow;
import com.maimaizaixian.transactiononline.module.hall.popupwindow.PageModePopupwindow;
import com.maimaizaixian.transactiononline.module.hall.ui.fragment.IndexListFragment;
import com.maimaizaixian.transactiononline.module.hall.ui.fragment.IndexSinglePageFragment;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.home.ui.CategoryActivity;
import com.maimaizaixian.transactiononline.module.me.ui.HistoryPublishActivity;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/9.
 */
public class HallIndexFragment extends BaseMenuFragment implements IBusinessController, OnFragmentRefreshListener<Business>, OnItemClickListener, BasePopupWindow.OnDismissListener, View.OnClickListener, IAreaController {

    public static final int REQUEST_CATEGORY = 100;

    @ViewInject(R.id.ibtn_category)
    private ImageButton mBtnCategory;
    @ViewInject(R.id.edit_query)
    private EditText mEditQuery;
    @ViewInject(R.id.radio_group)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.ibtn_mode)
    private ImageButton mBtnMode;
    @ViewInject(R.id.radio_button4)
    private RadioButton mBtnType;
    @ViewInject(R.id.radio_button5)
    private RadioButton mBtnArea;
    @ViewInject(R.id.radio_button3)
    private RadioButton mBtnScale;
    private boolean isDesc = false; //desc scale
    private int mIconRes;

    private ArrayList<FragmentTag> mFragments;
    private DynamicFragmentAdapter mDynamicFragmentAdapter;

    private PageModePopupwindow mModePopupwindow;
    private ConditionTypePopupWindow mTypePopupWindow;
    private ConditionAreaPopupWindow mAreaPopupWindow;

    private IAreaService mAreaService;
    private IBusinessService mBusinessService;


    private ConditionAreaAdapter.LocalArea mCheckedArea;
    private int cityID = 0;
    private String model = IBusinessService.MODE_BUY;
    private String mCategoryID;
    private int pageIndex = 1;
    private ArrayList<Business> mDataSource;
    private String mOrderName = IBusinessService.ORDER_TIME;
    private String mOrderType = IBusinessService.ORDER_DESC;

    @Override
    public void initSubView() {
        mModePopupwindow = new PageModePopupwindow(getActivity());
        mModePopupwindow.setOnDismissListener(this);
        mModePopupwindow.setItemClickListener(this);

        mTypePopupWindow = new ConditionTypePopupWindow(getActivity());
        mTypePopupWindow.setItemClickListener(this);
        mTypePopupWindow.setOnDismissListener(this);

        mAreaPopupWindow = new ConditionAreaPopupWindow(getActivity());
        mAreaPopupWindow.setOnDismissListener(this);


        mFragments = new ArrayList<>();
        mFragments.add(new FragmentTag(null, IndexListFragment.class, null));
        mFragments.add(new FragmentTag(null, IndexSinglePageFragment.class, null));
        //mFragments.add(BaseFragment.newInstance(IndexListFragment.class, null));
        //mFragments.add(BaseFragment.newInstance(IndexSinglePageFragment.class, null));
        mDynamicFragmentAdapter = new DynamicFragmentAdapter(getActivity(),mFragments, R.id.frame_content, getChildFragmentManager());
        mDynamicFragmentAdapter.loadFragment(0);

        mDataSource = new ArrayList<>();
        mAreaService = new AreaServiceImpl(this);
        mBusinessService = new BusinessServiceImpl(this);
        mAreaService.getAreaList();
        getData();
    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_hall_index;
    }

    @Override
    protected void initListener() {
        mBtnType.setOnClickListener(this);
        mBtnArea.setOnClickListener(this);

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
                }

                switch (checkedId) {
                    case R.id.radio_button1:
                        mOrderName = IBusinessService.ORDER_TIME;
                        setPage(1);
                        getData();
                        break;
                    case R.id.radio_button2:
                        mOrderName = IBusinessService.ORDER_DISTANCE;
                        setPage(1);
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
                mOrderName = IBusinessService.ORDER_SCALE;
                isDesc = !isDesc;
                if (isDesc) {
                    mOrderType = IBusinessService.ORDER_DESC;
                    setScaleIcon(R.mipmap.icon_order_desc);
                } else {
                    mOrderType = IBusinessService.ORDER_ASC;
                    setScaleIcon(R.mipmap.icon_order_asc);
                }

                setPage(1);
                getData();

            }
        });

        mBtnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra(CategoryActivity.ACTION_TITLE, "分类查找");
                intent.putExtra(CategoryActivity.ACTION_HINT, "大厅");
                startActivityForResult(intent, REQUEST_CATEGORY);
            }
        });

        mEditQuery.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mEditQuery.setCompoundDrawables(null, null, null, null);
                    mEditQuery.setPadding(20, 0, 0, 0);
                } else {
                    if (TextUtils.isEmpty(mEditQuery.getText().toString().trim())) {
                        Drawable leftDrawable = getResources().getDrawable(R.mipmap.icon_search);
                        leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable.getIntrinsicHeight());
                        mEditQuery.setCompoundDrawables(leftDrawable, null, null, null);
                        mEditQuery.setPadding((int) getResources().getDimension(R.dimen.size_50dp), 0, 0, 0);
                    }
                }

            }
        });


        mAreaPopupWindow.addOnItemClickListener(new OnItemClickListener() {
            @Override
            public void click(View v, int position) {
                mCheckedArea = mAreaPopupWindow.getItem(position);
                cityID = mCheckedArea.getCid();
                HallIndexFragment.this.mBtnArea.setText(mCheckedArea.getCity());
                mAreaPopupWindow.closePopupWindow();
                setPage(1);
                getData();
            }
        });


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
        if (popupWindow == mModePopupwindow.getPopupWindow()) {
            mBtnMode.setSelected(false);
        } else if (popupWindow == mTypePopupWindow.getPopupWindow() || popupWindow == mAreaPopupWindow.getPopupWindow()) {
            mRadioGroup.clearCheck();
        }

    }

    @Override
    public void click(View v, int position) {

        switch (v.getId()) {
            case R.id.tv_single:
            case R.id.tv_list:
                setBtnMode(position);
                mDynamicFragmentAdapter.loadFragment(position);
                mModePopupwindow.closePopupWindow();
                return;
            case R.id.tv_buy:
                mBtnType.setText("买");
                model = IBusinessService.MODE_BUY;
                break;
            case R.id.tv_sale:
                mBtnType.setText("卖");
                model = IBusinessService.MODE_SELL;
                break;
            case R.id.tv_lease:
                mBtnType.setText("出租");
                model = IBusinessService.MODE_RENTOUT;
                break;
            case R.id.tv_hire:
                mBtnType.setText("求租");
                model = IBusinessService.MODE_RENT;
                break;
        }

        setPage(1);
        getData();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_button4:
                if (!mTypePopupWindow.isShowing()) {
                    mTypePopupWindow.showAsDropDown(v);
                }
                break;
            case R.id.radio_button5:
                if (!mAreaPopupWindow.isShowing()) {
                    mAreaPopupWindow.showAsDropDown(v);
                }
                break;

        }
    }

    @Override
    public void onCompleteArea(List<Area> areas) {
        List<ConditionAreaAdapter.LocalArea> localAreaList = new ArrayList<>();
        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            List<Area.AreaChild> children = area.getChildren();
            for (int j = 0; j < children.size(); j++) {
                ConditionAreaAdapter.LocalArea localArea = new ConditionAreaAdapter.LocalArea();
                localArea.setProvince(area.getName());
                localArea.setCity(children.get(j).getName());
                localArea.setPid(area.getId());
                localArea.setCid(children.get(j).getId());
                localAreaList.add(localArea);
            }
        }

        mAreaPopupWindow.setData(localAreaList);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CATEGORY:
                    Category category = (Category) data.getSerializableExtra("object");
                    mCategoryID = category.getId() + "";
                    break;
            }
        }
    }

    @Override
    public void getData() {
        mBusinessService.getBusinessList(model, IBusinessService.TYPE_HALL, mCategoryID, pageIndex,mOrderName,mOrderType,cityID);
    }

    @Override
    public void setPage(int page) {
        pageIndex = page;
    }

    @Override
    public List<Business> getDataSource() {
        return mDataSource;
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business,Business_Action action) {

    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {

        pageIndex = page + 1;
        if (page == 1) {
            mDataSource.clear();
        }

        mDataSource.addAll(business);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            OnFragmentArgumentListener fragment = (OnFragmentArgumentListener) fragments.get(i);
            fragment.onComplete(business, page);
        }

    }

    @Override
    public void onComplete(Business_Type type) {

    }

}
