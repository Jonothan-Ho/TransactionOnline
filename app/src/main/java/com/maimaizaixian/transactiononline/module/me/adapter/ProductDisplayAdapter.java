package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.common.domain.Product;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;

import java.util.List;

/**
 * @see com.maimaizaixian.transactiononline.module.home.ui.ProductDisplayActivity
 * <p/>
 * Data module
 * Created by Administrator on 2015/10/21.
 */
public class ProductDisplayAdapter extends BaseAdapter<Product, ProductDisplayAdapter.ViewHolder> {

    public ProductDisplayAdapter(Context context, List<Product> source) {
        super(context, source);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_me_product_display, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Product product = mDataSource.get(position);
        BitmapUtil.getInstance(context).displayImage(holder.mImgView, product.getFirstImage());
        holder.mTextContent.setText(product.getTitle());

        if (mItemDeleteListener != null) {
            holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemDeleteListener.delete(v, position);
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


    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public ImageView mImgView;
        public TextView mTextContent;
        public ImageButton mBtnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.iv_image);
            mTextContent = (TextView) itemView.findViewById(R.id.tv_name);
            mBtnDelete = (ImageButton) itemView.findViewById(R.id.btn_delete);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }

    }

}
