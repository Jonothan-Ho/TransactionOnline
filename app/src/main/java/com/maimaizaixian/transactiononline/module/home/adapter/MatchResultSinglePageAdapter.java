package com.maimaizaixian.transactiononline.module.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.module.common.domain.CardModel;
import com.maimaizaixian.transactiononline.module.home.ui.MerchantsHomeActivity;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.view.CardContainer;
import com.maimaizaixian.transactiononline.view.CircleImageView;
import com.maimaizaixian.transactiononline.view.adapter.CardStackAdapter;

import java.util.List;


public final class MatchResultSinglePageAdapter extends CardStackAdapter {

    private int mImgWidth;

    public MatchResultSinglePageAdapter(Context mContext) {
        super(mContext);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mImgWidth = displayMetrics.widthPixels - CardContainer.PADDING;
    }

    @Override
    public View getCardView(final int position, final CardModel model, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.view_home_match_result_single_page, parent, false);
            assert convertView != null;

            viewHolder = new ViewHolder();
            viewHolder.btnAction = (Button) convertView.findViewById(R.id.btn_action);
            viewHolder.textName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.textDate = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.textAddress = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.textRange = (TextView) convertView.findViewById(R.id.tv_range);
            viewHolder.textCompany = (TextView) convertView.findViewById(R.id.tv_company);
            viewHolder.imgPortrait = (CircleImageView) convertView.findViewById(R.id.iv_image);

           /* ViewGroup.LayoutParams params = viewHolder.imgPortrait.getLayoutParams();
            params.height = (int) (mImgWidth * 0.9);
            params.width = mImgWidth;
            viewHolder.imgPortrait.setLayoutParams(params);*/

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textName.setText(model.getTitle());
        viewHolder.textCompany.setText(model.getCompany_name());
        viewHolder.textAddress.setText(model.getAddress());
        viewHolder.textRange.setText(model.getTrade());
        viewHolder.textDate.setText(model.getDate());


        BitmapUtil.getInstance(getContext()).displayImage(viewHolder.imgPortrait,model.getImage());

        viewHolder.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MerchantsHomeActivity.class);
                intent.putExtra("object", model);
                getContext().startActivity(intent);
            }
        });


        return convertView;
    }

    public static class ViewHolder {
        TextView textName;
        TextView textCompany;
        TextView textAddress;
        TextView textRange;
        TextView textDate;
        CircleImageView imgPortrait;
        Button btnAction;
    }
}
