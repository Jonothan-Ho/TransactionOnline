package com.maimaizaixian.transactiononline.module.neighborhood.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/9.
 */
public class ListDataAdapter extends BaseAdapter<Business, ListDataAdapter.ViewHolder> {


    public ListDataAdapter(Context context, List<Business> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_neighborehood_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Business business = mDataSource.get(position);
        BitmapUtil.getInstance(context).displayImage(holder.circleImg, business.getHead_link());
        BitmapUtil.getInstance(context).displayImage(holder.imgView, business.getImageList().length > 0 ? business.getImgpiclist_link()[0] : null);

        holder.textName.setText(business.getReal_name());
        holder.textAddress.setText(business.getAddress());
        holder.textContent.setText(business.getNeed_desc());
        holder.textDate.setText(business.getDate());


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

        public CardView cardView;
        public CircleImageView circleImg;
        public TextView textName;
        public TextView textSource;
        public TextView textAddress;
        public TextView textRecommend;
        public TextView textContent;
        public TextView textDistance;
        public TextView textDate;
        public ImageView imgView;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            circleImg = (CircleImageView) itemView.findViewById(R.id.iv_portrait);
            imgView = (ImageView) itemView.findViewById(R.id.iv_image);
            textName = (TextView) itemView.findViewById(R.id.tv_name);
            textSource = (TextView) itemView.findViewById(R.id.tv_source);
            textAddress = (TextView) itemView.findViewById(R.id.tv_address);
            textRecommend = (TextView) itemView.findViewById(R.id.tv_recommend);
            textContent = (TextView) itemView.findViewById(R.id.tv_content);
            textDistance = (TextView) itemView.findViewById(R.id.tv_distance);
            textDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

}
