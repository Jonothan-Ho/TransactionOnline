package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;
import com.maimaizaixian.transactiononline.module.me.domain.WithdrawRecords;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class WithdrawRecordsAdapter extends BaseAdapter<ConsumptionRecords, WithdrawRecordsAdapter.ViewHolder> {


    public WithdrawRecordsAdapter(Context context, List<ConsumptionRecords> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_me_withdraw_records, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ConsumptionRecords records = mDataSource.get(position);
        holder.mTextWithdraw.setText(records.getAmount());
        holder.mTextBalance.setText(records.getKiting_balance());
        holder.mTextDate.setText(records.getCreated_time());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextWithdraw;
        public TextView mTextBalance;
        public TextView mTextDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextBalance = (TextView) itemView.findViewById(R.id.tv_balance_money);
            mTextWithdraw = (TextView) itemView.findViewById(R.id.tv_withdraw);
            mTextDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

}
