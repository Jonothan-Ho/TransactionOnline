package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.GroupRecruit;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class GroupRecruitAdapter extends BaseAdapter<GroupRecruit, GroupRecruitAdapter.ViewHolder> {


    public GroupRecruitAdapter(Context context, List<GroupRecruit> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_me_group_recruit, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        GroupRecruit recruit = mDataSource.get(position);
        holder.mTextDate.setText(recruit.getCreate_time());
        holder.mTextName.setText(recruit.getTitle());

        if (mItemClickListener != null) {
            holder.mLayoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(v, position);
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextName;
        public TextView mTextDate;
        public LinearLayout mLayoutRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mTextDate = (TextView) itemView.findViewById(R.id.tv_date);
            mLayoutRoot = (LinearLayout) itemView.findViewById(R.id.layout_root);
        }
    }

}
