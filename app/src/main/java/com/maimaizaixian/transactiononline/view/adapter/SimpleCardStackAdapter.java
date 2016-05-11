package com.maimaizaixian.transactiononline.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.module.common.domain.CardModel;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.CardContainer;


public final class SimpleCardStackAdapter extends CardStackAdapter {

    private int mImgWidth;

    public SimpleCardStackAdapter(Context mContext) {
        super(mContext);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mImgWidth = displayMetrics.widthPixels - CardContainer.PADDING;

    }

    @Override
    public View getCardView(final int position, CardModel model, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.view_hall_single_page, parent, false);
            assert convertView != null;

            viewHolder = new ViewHolder();
            viewHolder.btnAction = (Button) convertView.findViewById(R.id.btn_action);
            viewHolder.textName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.textDate = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.textAddress = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.textContent = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.iv_image);

            ViewGroup.LayoutParams params = viewHolder.imgView.getLayoutParams();
            params.height = (int) (mImgWidth * 0.9);
            params.width = mImgWidth;
            viewHolder.imgView.setLayoutParams(params);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BitmapUtil.getInstance(getContext()).displayImage(viewHolder.imgView, "http://f.hiphotos.baidu.com/image/pic/item/ac6eddc451da81cb4c55d6195666d016082431b6.jpg");

        viewHolder.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtil.showToast(getContext(), "position=>" + position);
            }
        });


        return convertView;
    }

    public static class ViewHolder {
        TextView textName;
        TextView textContent;
        TextView textAddress;
        TextView textDate;
        ImageView imgView;
        Button btnAction;
    }
}
