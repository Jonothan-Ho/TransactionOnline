package com.maimaizaixian.transactiononline.module.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class UploadImageAdapter extends BaseAdapter<LocalBitmap, UploadImageAdapter.ViewHolder> {


    public UploadImageAdapter(Context context, List<LocalBitmap> source) {
        super(context, source);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_home_upload_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LocalBitmap localBitmap = mDataSource.get(position);
        if (localBitmap.getBitmap() != null) {
            holder.mImgView.setImageBitmap(localBitmap.getBitmap());
        }
        holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
    }

    @Override
    public void handleRemovedData(LocalBitmap obj) {
        super.handleRemovedData(obj);
        Bitmap bitmap = obj.getBitmap();

        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImgView;
        public ImageButton mBtnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.iv_image);
            mBtnDelete = (ImageButton) itemView.findViewById(R.id.ibtn_delete);
        }
    }


}
