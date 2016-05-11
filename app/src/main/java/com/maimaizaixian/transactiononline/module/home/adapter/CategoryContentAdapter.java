package com.maimaizaixian.transactiononline.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class CategoryContentAdapter extends BaseAdapter<CategoryContent, CategoryContentAdapter.ViewHolder> {


    public CategoryContentAdapter(Context context, List<CategoryContent> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_home_category_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CategoryContent content = mDataSource.get(position);
        BitmapUtil.getInstance(context).displayImage(holder.mImgView, content.getImgpic_link());
        holder.mTextName.setText(content.getTitle());

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

        public ImageView mImgView;
        public TextView mTextName;
        public CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.iv_image);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
