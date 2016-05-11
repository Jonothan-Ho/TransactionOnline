package com.maimaizaixian.transactiononline.module.message.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.message.domain.UserMessage;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/9.
 */
public class UserMessageAdapter extends BaseAdapter<UserMessage, UserMessageAdapter.ViewHolder> {


    public UserMessageAdapter(Context context, List<UserMessage> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_message_user_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        UserMessage message = mDataSource.get(position);
        BitmapUtil.getInstance(context).displayImage(holder.imgPortrait, "http://g.hiphotos.baidu.com/image/pic/item/c9fcc3cec3fdfc03e426845ed03f8794a5c226fd.jpg");

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

        public CircleImageView imgPortrait;
        public TextView textName;
        public TextView textAddress;
        public TextView textContent;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            imgPortrait = (CircleImageView) itemView.findViewById(R.id.iv_portrait);
            textName = (TextView) itemView.findViewById(R.id.tv_name);
            textAddress = (TextView) itemView.findViewById(R.id.tv_address);
            textContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

}
