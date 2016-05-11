package com.maimaizaixian.transactiononline.module.hall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/5.
 */
public class ListModeImageAdapter extends BaseAdapter<String, ListModeImageAdapter.ViewHolder> {


    public ListModeImageAdapter(Context context, List<String> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_hall_list_mode_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String strUrl = mDataSource.get(position);
        BitmapUtil.getInstance(context).displayImage(holder.imgView, strUrl);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

}
