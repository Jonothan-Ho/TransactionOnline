package com.maimaizaixian.transactiononline.module.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.common.domain.Product;
import com.maimaizaixian.transactiononline.module.home.domain.Merchants;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */
public class MerchantsAdapter extends BaseAdapter<Product, MerchantsAdapter.ViewHolder> {

    private int mImgWidth;

    public MerchantsAdapter(Context context, List<Product> source) {
        super(context, source);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mImgWidth = displayMetrics.widthPixels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_home_merchants, parent, false);
        return new ViewHolder(view, mImgWidth);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Product merchants = mDataSource.get(position);

        if (merchants.getImgpiclist_link() != null && merchants.getImgpiclist_link().length > 0) {
            BitmapUtil.getInstance(context).displayImage(holder.mImgView, merchants.getImgpiclist_link()[0]);
        }

        holder.mTextName.setText(merchants.getName());


        if (mItemClickListener != null) {
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(v, position);
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public ImageView mImgView;
        public TextView mTextName;

        public ViewHolder(View itemView, int width) {
            super(itemView);
            width = width / 2 - 2 * 10 - 20;
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mImgView = (ImageView) itemView.findViewById(R.id.iv_image);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            ViewGroup.LayoutParams params = mImgView.getLayoutParams();
            params.height = width;
            mImgView.setLayoutParams(params);
        }
    }

}
