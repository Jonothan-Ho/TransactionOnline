package com.maimaizaixian.transactiononline.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.Category;

import java.util.List;

/**
 * The Adapter for Category
 *
 * @see com.maimaizaixian.transactiononline.module.home.ui.CategoryActivity
 * Created by Administrator on 2015/10/22.
 */
public class CategoryAdapter extends BaseAdapter<Category, CategoryAdapter.ViewHolder> {

    private int mCurrentItem;

    public CategoryAdapter(Context context, List<Category> source) {
        super(context, source);
        mCurrentItem = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataSource.get(position).getTitle());
        if (mCurrentItem == position) {
            holder.mTextView.setBackgroundResource(R.mipmap.bg_recycler_view_item_style_one_selected);
        } else {
            holder.mTextView.setBackgroundColor(context.getResources().getColor(R.color.color_white_pure));
        }

        if (mItemClickListener != null) {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(v, position);
                }
            });
        }

    }

    /**
     * @param position
     */
    public void setSelectedItem(int position) {
        notifyItemChanged(mCurrentItem);
        notifyItemChanged(position);
        mCurrentItem = position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
            mTextView.setGravity(Gravity.CENTER);

        }
    }

}
