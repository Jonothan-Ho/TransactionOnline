package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    private SparseArray<Boolean> sparseArray;

    public CategoryContentAdapter(Context context, List<CategoryContent> source) {
        super(context, source);
        sparseArray = new SparseArray<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_me_category_content, parent, false);
        return new ViewHolder(view);
    }


    /**
     * @return
     */
    public SparseArray<Boolean> getSparseArray() {
        return sparseArray;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CategoryContent content = mDataSource.get(position);
        BitmapUtil.getInstance(context).displayImage(holder.mImgView, content.getImgpic_link());
        holder.mTextName.setText(content.getTitle());

        if (sparseArray.get(position) == null) {
            sparseArray.put(position, false);
        }

        boolean isSelected = sparseArray.get(position);
        if (isSelected) {
            holder.mBoxSelected.setVisibility(View.VISIBLE);
        } else {
            holder.mBoxSelected.setVisibility(View.GONE);
        }

        if (mItemClickListener != null) {
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isCheck = holder.mBoxSelected.isChecked();
                    if (isCheck) {
                        holder.mBoxSelected.setVisibility(View.GONE);
                        sparseArray.put(position, false);
                    } else {
                        holder.mBoxSelected.setVisibility(View.VISIBLE);
                        sparseArray.put(position, true);
                    }
                    holder.mBoxSelected.setChecked(!isCheck);
                    mItemClickListener.click(v, position);
                }
            });

            holder.mBoxSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mItemClickListener.click(buttonView, position);
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImgView;
        public TextView mTextName;
        public CardView mCardView;
        public CheckBox mBoxSelected;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.iv_image);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mBoxSelected = (CheckBox) itemView.findViewById(R.id.checkbox_selected);
        }
    }

}
