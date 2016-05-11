package com.maimaizaixian.transactiononline.module.message.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.hall.adapter.ListModeImageAdapter;
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.ImageSpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.message.domain.BusinessMessage;
import com.maimaizaixian.transactiononline.module.message.domain.UserMessage;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/11/9.
 */
public class BusinessMessageAdapter extends BaseAdapter<Business, BusinessMessageAdapter.ViewHolder> {


    public BusinessMessageAdapter(Context context, List<Business> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_message_business_message, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Business message = mDataSource.get(position);
        holder.textAddress.setText(message.getAddress());
        holder.textContent.setText(message.getNeed_desc());
        holder.textName.setText(message.getTitle());
        holder.textTime.setText(message.getDate());

        BaseAdapter adapter = (BaseAdapter) holder.recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new ListModeImageAdapter(context, Arrays.asList(message.getImageList()));
            holder.recyclerView.setAdapter(adapter);
        } else {
            adapter.replaceData(Arrays.asList(message.getImageList()));
        }

        if (mItemClickListener != null) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(holder.cardView, position);
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textName;
        public TextView textAddress;
        public TextView textContent;
        public TextView textTime;
        public RecyclerView recyclerView;
        public CardView cardView;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            textName = (TextView) itemView.findViewById(R.id.tv_name);
            textAddress = (TextView) itemView.findViewById(R.id.tv_address);
            textContent = (TextView) itemView.findViewById(R.id.tv_content);
            textTime = (TextView) itemView.findViewById(R.id.tv_time);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new ImageSpaceItemDecoration(10));
        }
    }

}
