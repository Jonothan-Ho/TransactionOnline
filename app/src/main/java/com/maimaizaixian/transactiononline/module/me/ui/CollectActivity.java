package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarFourActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.listener.OnItemDeleteListener;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.hall.ui.DetailsActivity;
import com.maimaizaixian.transactiononline.module.me.adapter.CollectAdapter;
import com.maimaizaixian.transactiononline.module.me.dialog.UnfavoriteDialog;
import com.maimaizaixian.transactiononline.module.me.domain.Collect;
import com.maimaizaixian.transactiononline.module.me.mvc.control.ICollectController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.ICollectService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.CollectServiceImpl;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.List;

public class CollectActivity extends ActionBarFourActivity implements ICollectController, OnItemClickListener, OnItemDeleteListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;

    private CollectAdapter mCollectAdapter;

    @ViewInject(R.id.layout_edit)
    private RelativeLayout mLayoutEdit;

    @ViewInject(R.id.checkbox)
    private CheckBox mCheckBox;
    @ViewInject(R.id.btn_cancel_collect)
    private Button mBtnCancelCollect;

    private UnfavoriteDialog mUnfavoriteDialog;

    private boolean isEdit;

    private ICollectService mCollectService;
    private int pageIndex = 1;
    private int deletePosition = -1;


    @Override
    protected void initSubView() {
        setTitleText("收藏");
        setCancelText("我");
        setActionText("编辑");

        isEdit = false;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));


        mRecyclerView.setAdapter(mCollectAdapter);
        mUnfavoriteDialog = new UnfavoriteDialog(this);
        mCollectService = new CollectServiceImpl(this);
        getData();
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_collect;
    }

    @Override
    protected void initListener() {
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCollectAdapter.setAllChecked(isChecked);
            }
        });

        mBtnCancelCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnfavoriteDialog.startDialog();
            }
        });

        mSwipeRefreshLayout.setOnLoadListener(new AppSwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onPullUp() {
                getData();
            }

            @Override
            public void onPullDown() {
                pageIndex = 1;
                getData();
            }
        });

        mUnfavoriteDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ids = mCollectAdapter.getCheckedIds();
                mCollectService.unCollect(ids);
            }
        });

    }

    @Override
    public void onActionClick(View view) {

        if (isEdit) {
            //complete
            isEdit = false;
            mLayoutEdit.setVisibility(View.GONE);
        } else {
            // edit
            isEdit = true;
            mLayoutEdit.setVisibility(View.VISIBLE);
        }

        mCollectAdapter.setEdit(isEdit);
        if (isEdit) {
            setActionText("完成");
        } else {
            setActionText("编辑");
        }

    }

    private void getData() {
        mCollectService.getCollectList(pageIndex);
    }

    @Override
    public void onComplete(List<Business> collects, int page) {
        mSwipeRefreshLayout.stopLoading();
        pageIndex = page + 1;
        if (mCollectAdapter == null) {
            mCollectAdapter = new CollectAdapter(this, collects);
            mCollectAdapter.addOnItemClickListener(this);
            mCollectAdapter.addOnItemDeleteListener(this);
            mRecyclerView.setAdapter(mCollectAdapter);
        } else {
            if (page == 1) {
                mCollectAdapter.replaceData(collects);
            } else {
                mCollectAdapter.addData(collects);
            }
        }
    }

    @Override
    public void onComplete() {
        if (deletePosition == -1) {
            mCollectAdapter.removeAllData();
            mUnfavoriteDialog.closeDialog();
        } else {
            mCollectAdapter.removeData(deletePosition);
            deletePosition = -1;
        }

    }

    @Override
    public void click(View v, int position) {
        Business business = mCollectAdapter.getItem(position);
        Intent intent = new Intent();
        if (business.getType() == 1) {
            //hall
            intent.setClass(this, DetailsActivity.class);
        } else {
            intent.setClass(this, com.maimaizaixian.transactiononline.module.neighborhood.ui.DetailsActivity.class);
        }

        intent.putExtra("object", business);
        startActivity(intent);

    }

    @Override
    public void delete(View v, int position) {
        deletePosition = position;
        mCollectService.unCollect(mCollectAdapter.getItem(position).getId());
    }
}
