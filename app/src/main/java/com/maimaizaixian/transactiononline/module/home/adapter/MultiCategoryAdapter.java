package com.maimaizaixian.transactiononline.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.home.dialog.MultiCategoryDialog;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class MultiCategoryAdapter extends BaseAdapter<CategoryContent, MultiCategoryAdapter.ViewHolder> {


    public MultiCategoryAdapter(Context context, List<CategoryContent> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view, context.getResources().getColor(R.color.color_normal_text));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextName.setText(mDataSource.get(position).getTitle());
        if (mItemClickListener != null) {
            holder.mTextName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    holder.mTextName.setBackgroundResource(R.mipmap.bg_check_style_one_checked);
                    holder.mTextName.setTextColor(context.getResources().getColor(R.color.color_white_pure));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            mItemClickListener.click(v, position);

                        }
                    }).start();


                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextName;

        public ViewHolder(View itemView, int textColor) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(android.R.id.text1);
            mTextName.setBackgroundResource(R.mipmap.bg_check_style_one_normal);
            mTextName.setGravity(Gravity.CENTER);
            mTextName.setTextColor(textColor);
            mTextName.setPadding(0, 0, 0, 0);
        }
    }

}
