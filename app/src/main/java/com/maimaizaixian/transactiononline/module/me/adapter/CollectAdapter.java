package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.framework.adapter.RecyclerViewDragHolder;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.hall.adapter.ListModeImageAdapter;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.ImageSpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.me.domain.Collect;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
public class CollectAdapter extends BaseAdapter<Business, RecyclerView.ViewHolder> {

    private SparseArray<Boolean> mCheckArray;
    private boolean isEdit;

    public CollectAdapter(Context context, List<Business> source) {
        super(context, source);
        mCheckArray = new SparseArray<>();
        isEdit = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     /*   View view = mLayoutInflater.inflate(R.layout.view_me_collect, parent, false);
        return new ViewHolder(view, context);*/

        View mybg = mLayoutInflater.inflate(R.layout.slidview_me_history_delete, parent, false);
        mybg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //获取item布局
        View view = mLayoutInflater.inflate(R.layout.view_me_collect, parent, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return new ViewHolder(context, mybg, view, RecyclerViewDragHolder.EDGE_RIGHT).getDragViewHolder();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rHolder, final int position) {
        Business collect = mDataSource.get(position);
        final ViewHolder holder = (ViewHolder) RecyclerViewDragHolder.getHolder(rHolder);
        holder.mTextAddress.setText(collect.getAddress());
        holder.mTextContent.setText(collect.getNeed_desc());
        holder.mTextDate.setText(collect.getDate());
        holder.mTextName.setText(collect.getTitle());

        BaseAdapter adapter = (BaseAdapter) holder.mRecyclerView.getAdapter();
        if (adapter == null) {
            adapter = new ListModeImageAdapter(context, Arrays.asList(collect.getImageList()));
            holder.mRecyclerView.setAdapter(adapter);
        } else {
            adapter.replaceData(Arrays.asList(collect.getImageList()));
        }

        if (isEdit) {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.GONE);
        }

        holder.mCheckBox.setChecked(mCheckArray.get(position) == null ? false : mCheckArray.get(position));
        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((mCheckArray.get(position) == null) || (mCheckArray.get(position) == false)) {
                    mCheckArray.put(position, true);
                } else {
                    mCheckArray.put(position, false);
                }
            }
        });

        if (mItemClickListener != null) {
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(holder.mCardView, position);
                }
            });
        }

        if (mItemDeleteListener != null) {
            holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemDeleteListener.delete(holder.mBtnDelete, position);
                }
            });
        }


    }


    /**
     * @return
     */
    public String getCheckedIds() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mCheckArray.size(); i++) {
            if (mCheckArray.get(i)) {
                builder.append(getItem(i).getId()).append(",");
            }
        }
        return builder.toString();
    }


    /**
     * @param isEdit
     */
    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

    /**
     * @param isChecked
     */
    public void setAllChecked(boolean isChecked) {

        for (int i = 0; i < mDataSource.size(); i++) {
            mCheckArray.put(i, isChecked);
        }

        notifyDataSetChanged();
    }


    /*public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public ImageView mImgStatus;
        public TextView mTextName;
        public TextView mTextDate;
        public TextView mTextContent;
        public RecyclerView mRecyclerView;
        public TextView mTextAddress;
        public CheckBox mCheckBox;


        public ViewHolder(View itemView, Context context) {
            super(itemView);
            mImgStatus = (ImageView) itemView.findViewById(R.id.iv_status);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mTextDate = (TextView) itemView.findViewById(R.id.tv_date);
            mTextContent = (TextView) itemView.findViewById(R.id.tv_content);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            mTextAddress = (TextView) itemView.findViewById(R.id.tv_address);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox_collect);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new ImageSpaceItemDecoration(10));
        }
    }*/


    public static class ViewHolder extends RecyclerViewDragHolder {

        public CardView mCardView;
        public ImageView mImgStatus;
        public TextView mTextName;
        public TextView mTextDate;
        public TextView mTextContent;
        public RecyclerView mRecyclerView;
        public TextView mTextAddress;
        public CheckBox mCheckBox;
        public Button mBtnDelete;


        private Context context;


        public ViewHolder(Context context, View bgView, View topView) {
            super(context, bgView, topView);
            this.context = context;
        }

        public ViewHolder(Context context, View bgView, View topView, int mTrackingEdges) {
            super(context, bgView, topView, mTrackingEdges);
        }

        @Override
        public void initView(View itemView) {
            mImgStatus = (ImageView) itemView.findViewById(R.id.iv_status);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mTextDate = (TextView) itemView.findViewById(R.id.tv_date);
            mTextContent = (TextView) itemView.findViewById(R.id.tv_content);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            mTextAddress = (TextView) itemView.findViewById(R.id.tv_address);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox_collect);
            mBtnDelete = (Button) itemView.findViewById(R.id.btn_delete);

            mBtnDelete.setText("取消收藏");

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new ImageSpaceItemDecoration(10));
        }
    }

}
