package com.maimaizaixian.transactiononline.module.me.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseNormalFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.listener.OnItemDeleteListener;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.me.adapter.HistoryAdapter;
import com.maimaizaixian.transactiononline.module.me.dialog.MessageDialog;
import com.maimaizaixian.transactiononline.module.me.domain.Collect;
import com.maimaizaixian.transactiononline.module.me.ui.HistoryClosedDetailsActivity;
import com.maimaizaixian.transactiononline.module.me.ui.HistoryOpenedDetailsActivity;
import com.maimaizaixian.transactiononline.module.me.ui.HistoryPublishActivity;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;
import com.maimaizaixian.transactiononline.view.AppSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class WantedFragment extends BaseNormalFragment implements IBusinessController, OnItemDeleteListener, HistoryPublishActivity.OnFragmentInteractionListener, OnItemClickListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.refresh_layout)
    private AppSwipeRefreshLayout mSwipeRefreshLayout;

    private HistoryAdapter historyAdapter;

    private MessageDialog messageDialog;

    private IBusinessService mBusinessService;

    private String uid;
    private int pageIndex = 1;
    private int deletePostion = -1;


    @Override
    public void initSubView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));

        messageDialog = new MessageDialog(getActivity());

        uid = PreferencesUtil.getInstance(getActivity()).get(User.class).getId();
        mBusinessService = new BusinessServiceImpl(this);
        getData();

    }

    @Override
    protected int getCustomView() {
        return R.layout.layout_recycler_view_style_one;
    }

    @Override
    protected void initListener() {
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
    }

    /**
     *
     */
    private void getData() {
        mBusinessService.getHistoryList(uid, IBusinessService.MODE_RENT, pageIndex);
    }

    @Override
    public void onFragmentInteraction(boolean isEdit) {
        historyAdapter.setEdit(isEdit);
    }

    @Override
    public void onAllChecked(boolean isChecked) {
        historyAdapter.setAllChecked(isChecked);
    }

    @Override
    public void deleteMessage() {
        SparseArray<Boolean> closeMessage = historyAdapter.getCheckClosedMessageArray();
        if (closeMessage.size() == 0) {
            messageDialog.setMessage("消息未关闭，请先关闭消息");
            messageDialog.startDialog();
            return;
        }

        StringBuilder id = new StringBuilder();
        for (int i = 0; i < closeMessage.size(); i++) {
            int position = closeMessage.keyAt(i);
            id.append(historyAdapter.getItem(position).getId()).append(",");
        }
        mBusinessService.deleteBusiness(id.toString());
    }

    @Override
    public void closeMessage() {
        SparseArray<Boolean> openMessage = historyAdapter.getCheckOpendMessageArray();
        if (openMessage.size() == 0) {
            messageDialog.setMessage("消息已关闭，可以删除消息");
            messageDialog.startDialog();
            return;
        }

        StringBuilder id = new StringBuilder();
        for (int i = 0; i < openMessage.size(); i++) {
            int position = openMessage.keyAt(i);
            id.append(historyAdapter.getItem(position).getId()).append(",");
        }
        mBusinessService.closeBusiness(id.toString());
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business, IBusinessController.Business_Action action) {
        switch (action) {
            case Close:
                if (deletePostion != -1) {
                    historyAdapter.getItem(deletePostion).setStatus(2);
                    historyAdapter.notifyItemChanged(deletePostion);
                    deletePostion = -1;
                } else {
                    SparseArray<Boolean> openMessage = historyAdapter.getCheckOpendMessageArray();
                    for (int i = 0; i < openMessage.size(); i++) {
                        int position = openMessage.keyAt(i);
                        historyAdapter.getItem(position).setStatus(2);
                    }
                    historyAdapter.notifyDataSetChanged();
                    openMessage.clear();
                }

                break;
        }
    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {
        mSwipeRefreshLayout.stopLoading();
        pageIndex = page + 1;
        if (historyAdapter == null) {
            historyAdapter = new HistoryAdapter(getActivity(), business);
            historyAdapter.addOnItemClickListener(this);
            historyAdapter.addOnItemDeleteListener(this);
            mRecyclerView.setAdapter(historyAdapter);
        } else {
            if (page == 1) {
                historyAdapter.replaceData(business);
            } else {
                historyAdapter.addData(business);
            }
        }
    }

    @Override
    public void onComplete(IBusinessController.Business_Type type) {
        switch (type) {
            case Delete:
                if (deletePostion != -1) {
                    historyAdapter.removeData(deletePostion);
                    deletePostion = -1;
                } else {
                    SparseArray<Boolean> closeMessage = historyAdapter.getCheckClosedMessageArray();
                    for (int i = 0; i < closeMessage.size(); i++) {
                        int position = closeMessage.keyAt(i);
                        historyAdapter.justRemoveData(position);
                    }
                    historyAdapter.notifyDataSetChanged();
                    closeMessage.clear();
                }

                break;
        }
    }

    @Override
    public void delete(View v, int position) {
        deletePostion = position;
        Business business = historyAdapter.getItem(position);
        if (business.getStatus() == 1) {
            //close
            mBusinessService.closeBusiness(business.getId());
        } else {
            //delete
            mBusinessService.deleteBusiness(business.getId());
        }
    }

    @Override
    public void click(View v, int position) {
        Business business = historyAdapter.getItem(position);
        Intent intent = new Intent();
        int requestCode = 0;
        if (business.getStatus() == 2) {
            intent.setClass(getActivity(), HistoryClosedDetailsActivity.class);
            requestCode = HistoryAllFragment.REQUEST_CLOSED;
        } else {
            intent.setClass(getActivity(), HistoryOpenedDetailsActivity.class);
            requestCode = HistoryAllFragment.REQUEST_OPENED;
        }
        intent.putExtra("object", business);
        intent.putExtra("position", position);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            switch (requestCode) {
                case HistoryAllFragment.REQUEST_OPENED:
                    historyAdapter.getItem(position).setStatus(2);
                    historyAdapter.notifyItemChanged(position);
                    break;
                case HistoryAllFragment.REQUEST_CLOSED:
                    historyAdapter.removeData(position);
                    break;
            }
        }
    }
}
