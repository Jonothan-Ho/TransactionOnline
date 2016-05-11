package com.maimaizaixian.transactiononline.module.hall.popupwindow;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;
import com.maimaizaixian.transactiononline.view.AutoScrollViewPager;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ImagesPopupWindow extends BasePopupWindow {

    private AutoScrollViewPager mScrollViewPager;
    private ImageButton mBtnLeft;
    private ImageButton mBtnRight;

    public ImagesPopupWindow(Activity activity) {
        super(activity);
    }

    @Override
    protected int getCustomView() {
        return R.layout.popupwindow_hall_images_gallery;
    }

    @Override
    protected int getWindowWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getWindowHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    @Override
    protected void initView(View rootView) {
        mScrollViewPager = (AutoScrollViewPager) rootView.findViewById(R.id.viewpager_auto);
        mBtnLeft = (ImageButton) rootView.findViewById(R.id.btn_left);
        mBtnRight = (ImageButton) rootView.findViewById(R.id.btn_right);

        String urls[] = {"http://c.hiphotos.baidu.com/image/pic/item/6f061d950a7b020841b6ecf960d9f2d3572cc8af.jpg", "http://g.hiphotos.baidu.com/image/pic/item/0b55b319ebc4b745558761eccbfc1e178a82153c.jpg", "http://f.hiphotos.baidu.com/image/pic/item/bd3eb13533fa828b23ee8e98f81f4134960a5aff.jpg", "http://g.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c2db1ff4500bf41bd5ad6e3988.jpg", "http://e.hiphotos.baidu.com/image/pic/item/c995d143ad4bd1134fde2d3f58afa40f4bfb05af.jpg"};
        mScrollViewPager.setUrls(urls);

        mBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollViewPager.lastCurrentItem();
            }
        });

        mBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollViewPager.nextCurrentItem();
            }
        });


    }


    /**
     * @param urls
     */
    public void setUrls(View view,String[] urls) {
        if (mScrollViewPager.isInitialized()) {
            mScrollViewPager.setUrls(urls);
        } else {
            mScrollViewPager.replaceData(urls);
        }

        showAsLocation(view, Gravity.CENTER);

    }

}
