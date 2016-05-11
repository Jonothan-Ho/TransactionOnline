package com.maimaizaixian.transactiononline.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.home.domain.MatchResult;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2015/11/2.
 */
public class ResultListAdapter extends BaseAdapter<User, ResultListAdapter.ViewHolder> {


    public ResultListAdapter(Context context, List<User> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_home_result_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        User result = mDataSource.get(position);
        BitmapUtil.getInstance(context).displayImage(holder.mCirclePortrait, result.getHead());
        holder.mTextName.setText(result.getReal_name());
        holder.mCompany.setText(result.getCompany_name());
        holder.mAddress.setText(result.getRegion());
        StringBuilder builder = new StringBuilder("销售范围:");
        List<User.Category> list = result.getCategories();
        if (list!=null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i).getTitle()).append(" ");
            }
        }

        holder.mRange.setText(builder.toString());

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

        public RelativeLayout mLayoutRoot;
        public CircleImageView mCirclePortrait;
        public TextView mTextName;
        public TextView mRecommend;
        public TextView mCompany;
        public TextView mAddress;
        public TextView mRange;


        public ViewHolder(View itemView) {
            super(itemView);
            mLayoutRoot = (RelativeLayout) itemView.findViewById(R.id.layout_root);
            mCirclePortrait = (CircleImageView) itemView.findViewById(R.id.iv_portrait);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mRecommend = (TextView) itemView.findViewById(R.id.tv_recommend);
            mCompany = (TextView) itemView.findViewById(R.id.tv_company);
            mAddress = (TextView) itemView.findViewById(R.id.tv_address);
            mRange = (TextView) itemView.findViewById(R.id.tv_range);
        }
    }

}
