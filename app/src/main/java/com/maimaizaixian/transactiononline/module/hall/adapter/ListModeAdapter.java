package com.maimaizaixian.transactiononline.module.hall.adapter;

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
import com.maimaizaixian.transactiononline.module.hall.adapter.decoration.ImageSpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.hall.domain.MatchResult;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class ListModeAdapter extends BaseAdapter<Business, ListModeAdapter.ViewHolder> {


    public ListModeAdapter(Context context, List<Business> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_hall_list_mode, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Business result = mDataSource.get(position);
        BaseAdapter adapter = (BaseAdapter) holder.recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new ListModeImageAdapter(context, Arrays.asList(result.getImgpiclist_link()));
            holder.recyclerView.setAdapter(adapter);
        } else {
            adapter.replaceData(Arrays.asList(result.getImgpiclist_link()));
        }

        holder.textAddress.setText(result.getAddress());
        holder.textContent.setText(result.getNeed_desc());
        holder.textDate.setText(result.getCreate_time());
        holder.textName.setText(result.getTitle());

        if (result.getHas_pay() == 0) {
            holder.textStatus.setVisibility(View.GONE);
        } else {
            holder.textStatus.setVisibility(View.VISIBLE);
        }

        if (mItemClickListener != null) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(v, position);
                }
            });
        }


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textName;
        public TextView textContent;
        public TextView textDate;
        public TextView textAddress;
        public TextView textStatus;
        public RecyclerView recyclerView;
        public CardView cardView;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.tv_name);
            textContent = (TextView) itemView.findViewById(R.id.tv_content);
            textAddress = (TextView) itemView.findViewById(R.id.tv_address);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            textDate = (TextView) itemView.findViewById(R.id.tv_date);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            textStatus = (TextView) itemView.findViewById(R.id.tv_status);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new ImageSpaceItemDecoration(10));
        }
    }

}
