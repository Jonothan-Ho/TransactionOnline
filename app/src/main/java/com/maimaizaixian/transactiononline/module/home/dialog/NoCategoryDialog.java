package com.maimaizaixian.transactiononline.module.home.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.dialog.BaseDialog;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.ui.CategoryActivity;
import com.maimaizaixian.transactiononline.module.home.ui.fragment.BuyFragment;

/**
 * Created by Administrator on 2015/10/29.
 */
public class NoCategoryDialog extends BaseDialog {

    private Button mBtnSelect;

    public NoCategoryDialog(Activity context) {
        super(context);
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_home_no_category;
    }

    @Override
    public void initView() {
        mBtnSelect = (Button) findViewById(R.id.btn_select);
        mBtnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CategoryActivity.class);
                activity.startActivityForResult(intent, BuyFragment.REQUEST_CATEGORY);
                closeDialog();
            }
        });
    }
}
