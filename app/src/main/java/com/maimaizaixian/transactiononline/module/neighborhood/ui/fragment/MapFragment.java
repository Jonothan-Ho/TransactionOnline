package com.maimaizaixian.transactiononline.module.neighborhood.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseNormalFragment;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentArgumentListener;
import com.maimaizaixian.transactiononline.module.common.listener.OnFragmentRefreshListener;
import com.maimaizaixian.transactiononline.module.home.ui.MatchResultActivity;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseNormalFragment implements OnFragmentArgumentListener<Business>, View.OnClickListener {


    @ViewInject(R.id.map)
    private MapView mMapView;
    private AMap mAmap;
    @ViewInject(R.id.iv_portrait)
    private CircleImageView mImgPortrait;

    @ViewInject(R.id.tv_name)
    private TextView mTextName;
    @ViewInject(R.id.tv_content)
    private TextView mTextContent;
    @ViewInject(R.id.tv_distance)
    private TextView mTextDistance;
    @ViewInject(R.id.tv_last)
    private TextView mTextLast;
    @ViewInject(R.id.tv_next)
    private TextView mTextNext;

    private List<Business> list;
    private List<MarkerOptions> optionses;
    private int mCurrentIndex = 0;
    private OnFragmentRefreshListener mFragmentRefreshListener;

    @Override
    public void initSubView() {
        mMapView.onCreate(mSavedInstanceState);
        mAmap = mMapView.getMap();
        optionses = new ArrayList<>();
        list = new ArrayList<>();
        mFragmentRefreshListener = (OnFragmentRefreshListener) getParentFragment();
        list.addAll(mFragmentRefreshListener.getDataSource());
        initMarker();
        if (list.size() > 0) {
            initData();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_home_match_result_map;
    }

    @Override
    protected void initListener() {
        mTextLast.setOnClickListener(this);
        mTextNext.setOnClickListener(this);
    }

    @Override
    public void onComplete(List<Business> users, int page) {
        list.clear();
        list.addAll(users);
        mCurrentIndex = 0;
        initMarker();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_last:
                if (mCurrentIndex == 0) {
                    ViewUtil.showSnackbar(getRootView(), "已经是这页的第一条了");
                    return;
                }
                mCurrentIndex--;
                initData();
                break;
            case R.id.tv_next:
                mCurrentIndex++;
                if (mCurrentIndex >= list.size()) {
                    mFragmentRefreshListener.getData();
                } else {
                    initData();
                }
                break;
        }
    }

    /**
     *
     */
    private void initMarker() {
        optionses.clear();
        for (int i = 0; i < list.size(); i++) {
            Business business = list.get(i);
            MarkerOptions options = new MarkerOptions();
            LatLng latLng = new LatLng(business.getLatitude(), business.getLongitude());
            options.position(latLng);
            View view = View.inflate(getActivity(), R.layout.view_map_marker, null);
            ((TextView) view).setText(i + "");
            options.icon(BitmapDescriptorFactory.fromView(view));
            optionses.add(options);
            mAmap.addMarker(options);

            if (i == 0) {
                mAmap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mAmap.moveCamera(CameraUpdateFactory.zoomTo(16));
            }

        }

        //mAmap.addMarkers(optionses, true);

    }

    private void initData() {
        Business business = list.get(mCurrentIndex);
        mTextName.setText(business.getReal_name());
        mTextContent.setText(business.getContent());
        mTextDistance.setText(business.getDistance());
        BitmapUtil.getInstance(getActivity()).displayImage(mImgPortrait, business.getHead_link());
    }

}
