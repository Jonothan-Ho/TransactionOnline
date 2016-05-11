package com.maimaizaixian.transactiononline.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.ProductDisplay;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;

import java.util.List;

/**
 * @see com.maimaizaixian.transactiononline.module.home.ui.ProductDisplayActivity
 * <p/>
 * Data module
 * @see com.maimaizaixian.transactiononline.module.home.domain.ProductDisplay
 * Created by Administrator on 2015/10/21.
 */
public class ProductDisplayAdapter extends BaseAdapter<ProductDisplay, ProductDisplayAdapter.ViewHolder> {

    public ProductDisplayAdapter(Context context, List<ProductDisplay> source) {
        super(context, source);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_common_imageview, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BitmapUtil.getInstance(context).displayImage(holder.mImgView, mDataSource.get(position).getImgUrl());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImgView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.iv_image);
        }

    }

}
