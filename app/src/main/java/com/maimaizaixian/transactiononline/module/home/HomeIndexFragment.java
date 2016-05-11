package com.maimaizaixian.transactiononline.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.common.Common;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.module.account.ui.LoginActivity;
import com.maimaizaixian.transactiononline.module.home.domain.Poster;
import com.maimaizaixian.transactiononline.module.home.mvc.controller.IHomeController;
import com.maimaizaixian.transactiononline.module.home.mvc.service.IHomeService;
import com.maimaizaixian.transactiononline.module.home.mvc.service.impl.HomeServiceImpl;
import com.maimaizaixian.transactiononline.module.home.ui.PublishActivity;
import com.maimaizaixian.transactiononline.utils.CommonUtil;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * The HomePage Module Index Page
 */

public class HomeIndexFragment extends BaseMenuFragment implements View.OnClickListener, AutoScrollViewPager.OnImageViewClickListener, IHomeController {

    @ViewInject(R.id.btn_publish)
    private ImageButton mBtnPublish;
  /*  @ViewInject(R.id.viewpager_auto)
    private AutoScrollViewPager mViewPager;*/

    //private List<Poster> mListPosters;
    private IHomeService mHomeService;


    @Override
    public void initSubView() {
      /*  String[] urls = {"http://pic26.nipic.com/20130105/11664993_134343361131_2.jpg",
                "http://img.sucai.redocn.com/attachments/images/201107/20110705/Redocn_2011062615171120.jpg",
                "http://pic17.nipic.com/20111031/7666075_110546353106_2.jpg",
                "http://picm.photophoto.cn/073/008/055/0080550102.jpg",
                "http://www.dabaoku.com/sucaidatu/gongye/gongyechanpin/0315425.jpg"
        };*/
       // mHomeService = new HomeServiceImpl(this);
        //mHomeService.getAdImages();
        //buildNativeViewPager(urls);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //mViewPager.stopAutoScroll();
        //mViewPager = null;
    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_home_index;
    }

    @Override
    protected void initListener() {
        mBtnPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_publish:
                if (CommonUtil.isLogged(getActivity())) {
                    Intent intent = new Intent(getActivity(), PublishActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }

    @Override
    public void onImageClick(int position, View view) {
        //ViewUtil.showSnackbar(rootView, position + "===>");
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAdImages(List<Poster> posters) {
       // mListPosters = posters;
        String urls[] = new String[posters.size()];
        for (int i = 0; i < posters.size(); i++) {
            urls[i] = posters.get(i).getImgPic();
        }
       // buildNativeViewPager(urls);
    }


    /**
     *
     */
   /* private void buildNativeViewPager(String[] urls) {
        mViewPager.setUrls(urls);
        mViewPager.setOnImageClickListener(this);
        LinearLayout layoutIndictor = new LinearLayout(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutIndictor.setGravity(Gravity.CENTER);
        layoutIndictor.setPadding(0, 10, 0, 10);
        mViewPager.setIndicatorLayout(layoutIndictor, params, 25, R.drawable.selector_shape_oval_style_one);
        mViewPager.startAutoScroll(10);
    }*/


}
