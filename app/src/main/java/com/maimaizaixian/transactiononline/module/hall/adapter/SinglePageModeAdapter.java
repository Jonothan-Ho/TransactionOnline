package com.maimaizaixian.transactiononline.module.hall.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.module.common.domain.CardModel;
import com.maimaizaixian.transactiononline.module.hall.popupwindow.ImagesPopupWindow;
import com.maimaizaixian.transactiononline.module.hall.ui.DetailsActivity;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.view.CardContainer;
import com.maimaizaixian.transactiononline.view.adapter.CardStackAdapter;


public final class SinglePageModeAdapter extends CardStackAdapter {

    private int mImgWidth;
    private ImagesPopupWindow mImgsPopupWindow;
    private Activity mActivity;


    public SinglePageModeAdapter(Activity mContext) {
        super(mContext);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mImgWidth = displayMetrics.widthPixels - CardContainer.PADDING;

        mImgsPopupWindow = new ImagesPopupWindow(mContext);

        mActivity = mContext;

    }

    @Override
    public View getCardView(final int position, final CardModel model, View convertView, ViewGroup parent) {

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
            //viewHolder.rootView = (CardView) convertView.findViewById(R.id.card_view);
            viewHolder.layoutRoot = (LinearLayout) convertView.findViewById(R.id.layout_root);

            ViewGroup.LayoutParams params = viewHolder.imgView.getLayoutParams();
            params.height = (int) (mImgWidth * 0.7);
            params.width = mImgWidth;
            viewHolder.imgView.setLayoutParams(params);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textAddress.setText(model.getAddress());
        viewHolder.textContent.setText(model.getContent());
        viewHolder.textDate.setText(model.getDate());
        viewHolder.textName.setText(model.getTitle());

        BitmapUtil.getInstance(getContext()).displayImage(viewHolder.imgView, model.getImage());

        viewHolder.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePageModeAdapter.this.getContext(), DetailsActivity.class);
                intent.putExtra("object", model);
                getContext().startActivity(intent);
            }
        });

        viewHolder.layoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgsPopupWindow.setUrls(mActivity.getWindow().getDecorView(), model.getImageList());
            }
        });


        return convertView;
    }

    public static class ViewHolder {
        // CardView rootView;
        LinearLayout layoutRoot;
        TextView textName;
        TextView textContent;
        TextView textAddress;
        TextView textDate;
        ImageView imgView;
        Button btnAction;
    }
}
