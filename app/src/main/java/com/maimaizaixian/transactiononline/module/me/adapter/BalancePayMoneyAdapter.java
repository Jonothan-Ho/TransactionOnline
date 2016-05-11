package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class BalancePayMoneyAdapter extends BaseAdapter {


    public BalancePayMoneyAdapter(Context context, List source) {
        super(context, source);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextMoney = (TextView) itemView.findViewById(android.R.id.text1);

           // mTextMoney.setBackgroundResource(R.drawable.selec);

        }
    }

}
