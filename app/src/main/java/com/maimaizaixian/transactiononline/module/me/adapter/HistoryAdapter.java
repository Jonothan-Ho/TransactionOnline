package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
public class HistoryAdapter extends BaseAdapter<Business, RecyclerView.ViewHolder> {

    private SparseArray<Boolean> mCheckClosedMessageArray;
    private SparseArray<Boolean> mCheckOpendMessageArray;
    private boolean isEdit;
    private int whiteColor;
    private int greyColor;
    private int greyDarkColor;
    private Drawable greyDrawable;
    private Drawable redDrawable;

    private Drawable buyDrawable;
    private Drawable sellDrawable;
    private Drawable rentDrawable;
    private Drawable rentOutDrawable;


    public HistoryAdapter(Context context, List<Business> source) {
        super(context, source);
        mCheckClosedMessageArray = new SparseArray<>();
        mCheckOpendMessageArray = new SparseArray<>();
        isEdit = false;

        whiteColor = context.getResources().getColor(R.color.color_white_pure);
        greyColor = context.getResources().getColor(R.color.color_grey_light);
        greyDarkColor = context.getResources().getColor(R.color.color_grey_dark);
        greyDrawable = context.getResources().getDrawable(R.drawable.selector_layout_grey_style_one);
        redDrawable = context.getResources().getDrawable(R.drawable.selector_layout_red_style_one);


        buyDrawable = context.getResources().getDrawable(R.mipmap.icon_status_buy);
        sellDrawable = context.getResources().getDrawable(R.mipmap.icon_status_sell);
        rentDrawable = context.getResources().getDrawable(R.mipmap.icon_status_wanted);
        rentOutDrawable = context.getResources().getDrawable(R.mipmap.icon_status_lease);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View view = mLayoutInflater.inflate(R.layout.view_me_collect, parent, false);
        return new ViewHolder(view, context);*/

        View mybg = mLayoutInflater.inflate(R.layout.slidview_me_history_delete, parent, false);
        mybg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //获取item布局
        View view = mLayoutInflater.inflate(R.layout.view_me_history, parent, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return new ViewHolder(context, mybg, view, RecyclerViewDragHolder.EDGE_RIGHT).getDragViewHolder();


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rHolder, final int position) {
        final Business collect = mDataSource.get(position);
        final ViewHolder holder = (ViewHolder) RecyclerViewDragHolder.getHolder(rHolder);

        BaseAdapter adapter = (BaseAdapter) holder.mRecyclerView.getAdapter();
        if (adapter == null) {
            adapter = new ListModeImageAdapter(context, Arrays.asList(collect.getImageList()));
            holder.mRecyclerView.setAdapter(adapter);
        } else {
            adapter.replaceData(Arrays.asList(collect.getImageList()));
        }

        holder.mTextAddress.setText(collect.getAddress());
        holder.mTextContent.setText(collect.getNeed_desc());
        holder.mTextDate.setText(collect.getDate());
        holder.mTextName.setText(collect.getTitle());

        if ("buy".equals(collect.getModel_alias())) {
            holder.mImgStatus.setImageDrawable(buyDrawable);
        } else if ("sell".equals(collect.getModel_alias())) {
            holder.mImgStatus.setImageDrawable(sellDrawable);
        } else if ("rent".equals(collect.getModel_alias())) {
            holder.mImgStatus.setImageDrawable(rentDrawable);
        } else {
            holder.mImgStatus.setImageDrawable(rentOutDrawable);
        }

        if (collect.getStatus() == 2) {
            holder.mBtnDelete.setText("删除消息");
            holder.mLayoutRoot.setBackgroundColor(greyColor);
            holder.mTextStatus.setVisibility(View.VISIBLE);
            holder.mBtnDelete.setBackgroundDrawable(redDrawable);
            holder.mBtnDelete.setTextColor(whiteColor);
            holder.mCheckBox.setChecked(mCheckClosedMessageArray.get(position) == null ? false : mCheckClosedMessageArray.get(position));
        } else {
            holder.mBtnDelete.setText("关闭消息");
            holder.mLayoutRoot.setBackgroundColor(whiteColor);
            holder.mTextStatus.setVisibility(View.GONE);
            holder.mBtnDelete.setBackgroundDrawable(greyDrawable);
            holder.mBtnDelete.setTextColor(greyDarkColor);
            holder.mCheckBox.setChecked(mCheckOpendMessageArray.get(position) == null ? false : mCheckOpendMessageArray.get(position));
        }


        if (isEdit) {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.GONE);
        }

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (collect.getStatus() == 2) {
                    if ((mCheckClosedMessageArray.get(position) == null) || (mCheckClosedMessageArray.get(position) == false)) {
                        mCheckClosedMessageArray.put(position, true);
                    } else {
                        mCheckClosedMessageArray.put(position, false);
                    }
                } else {
                    if ((mCheckOpendMessageArray.get(position) == null) || (mCheckOpendMessageArray.get(position) == false)) {
                        mCheckOpendMessageArray.put(position, true);
                    } else {
                        mCheckOpendMessageArray.put(position, false);
                    }
                }

            }
        });

        if (mItemDeleteListener != null) {
            holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemDeleteListener.delete(holder.mBtnDelete, position);
                }
            });
        }


        if (mItemClickListener != null) {
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(v, position);
                }
            });
        }


    }


    /**
     * @return
     */
    public SparseArray<Boolean> getCheckClosedMessageArray() {
        return mCheckClosedMessageArray;
    }

    /**
     * @return
     */
    public SparseArray<Boolean> getCheckOpendMessageArray() {
        return mCheckOpendMessageArray;
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
            Business business = mDataSource.get(i);
            if (business.getStatus() == 2) {
                //delete
                mCheckClosedMessageArray.put(i, isChecked);
            } else {
                //close
                mCheckOpendMessageArray.put(i, isChecked);
            }
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
        public TextView mTextStatus;


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
            mTextStatus = (TextView) itemView.findViewById(R.id.tv_status);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new ImageSpaceItemDecoration(10));
        }
    }
*/

    public static class ViewHolder extends RecyclerViewDragHolder {

        public CardView mCardView;
        public ImageView mImgStatus;
        public TextView mTextName;
        public TextView mTextDate;
        public TextView mTextContent;
        public RecyclerView mRecyclerView;
        public TextView mTextAddress;
        public CheckBox mCheckBox;
        public TextView mTextStatus;
        public Button mBtnDelete;
        public RelativeLayout mLayoutRoot;


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
            mTextStatus = (TextView) itemView.findViewById(R.id.tv_status);
            mBtnDelete = (Button) itemView.findViewById(R.id.btn_delete);
            mLayoutRoot = (RelativeLayout) itemView.findViewById(R.id.layout_root);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new ImageSpaceItemDecoration(10));
        }
    }

}
