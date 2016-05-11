package com.maimaizaixian.transactiononline.module.home.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.common.domain.Product;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

public class DetailsActivity extends ActionBarOneActivity {

    public static final String FLAG_HINT = "hint";
    public static final String FLAG_DETAILS = "details";

    @ViewInject(R.id.iv_image)
    private ImageView mImgView;
    @ViewInject(R.id.tv_title_details)
    private TextView mTextDetails;
    @ViewInject(R.id.tv_content)
    private TextView mTextContent;

    @Override
    protected int getCustomView() {
        return R.layout.activity_home_merchants_details;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initSubView() {
        String details = getIntent().getStringExtra(FLAG_DETAILS);
        String hint = getIntent().getStringExtra(FLAG_HINT);
        setTitleBarHint(hint);
        setTitleBarHeadline("详情");
        mTextDetails.setText(details);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ViewGroup.LayoutParams params = mImgView.getLayoutParams();
        params.height = metrics.widthPixels;
        mImgView.setLayoutParams(params);

        Product product = (Product) getIntent().getSerializableExtra("object");
        if (product == null) {
            ViewUtil.showToast(this, "商品对象丢失，请重试");
            finish();
            return;
        }


        if (product.getImgpiclist_link() != null && product.getImgpiclist_link().length > 0) {
            BitmapUtil.getInstance(this).displayImage(mImgView, product.getImgpiclist_link()[0]);
        }

        mTextDetails.setText(product.getName());
        mTextContent.setText(product.getDescription());

    }
}
