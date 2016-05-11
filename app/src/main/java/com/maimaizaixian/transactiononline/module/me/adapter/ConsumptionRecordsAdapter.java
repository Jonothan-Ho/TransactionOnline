package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ConsumptionRecordsAdapter extends BaseAdapter<ConsumptionRecords, ConsumptionRecordsAdapter.ViewHolder> {

    public ConsumptionRecordsAdapter(Context context, List<ConsumptionRecords> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_me_consumption_records, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ConsumptionRecords records = mDataSource.get(position);
        holder.mTextDate.setText(records.getCreated_time());
        holder.mTextName.setText(records.getTrade_title());
        holder.mTextRecords.setText(records.getAmount());

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
        public TextView mTextRecords;
        public TextView mTextName;
        public TextView mTextDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mTextRecords = (TextView) itemView.findViewById(R.id.tv_records);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mTextDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

}
