package com.maimaizaixian.transactiononline.module.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.me.domain.Contact;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class InviteContactAdapter extends BaseAdapter<Contact,InviteContactAdapter.ViewHolder> {


    public InviteContactAdapter(Context context, List<Contact> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_me_invite_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Contact contact = mDataSource.get(position);

        BitmapUtil.getInstance(context).displayImage(holder.mCirclePortrait,"http://d.hiphotos.baidu.com/image/pic/item/902397dda144ad34524fec53d4a20cf430ad8575.jpg");

        if (mItemClickListener != null) {
            holder.mBtnInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.click(v, position);
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView mCirclePortrait;
        public TextView mTextName;
        public TextView mTextPhone;
        public Button mBtnInvite;


        public ViewHolder(View itemView) {
            super(itemView);
            mCirclePortrait = (CircleImageView) itemView.findViewById(R.id.iv_portrait);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name);
            mTextPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            mBtnInvite = (Button) itemView.findViewById(R.id.btn_invite);
        }
    }


}
