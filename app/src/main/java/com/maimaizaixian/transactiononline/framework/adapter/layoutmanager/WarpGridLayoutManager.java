package com.maimaizaixian.transactiononline.framework.adapter.layoutmanager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2015/10/29.
 */
public class WarpGridLayoutManager extends GridLayoutManager {

    private int mItemCount;
    private int mPadding;

    public WarpGridLayoutManager(Context context, int spanCount, int itemCount, int padding) {
        super(context, spanCount);
        this.mItemCount = itemCount;
        this.mPadding = padding;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        View view = recycler.getViewForPosition(0);
        if (view != null) {
            measureChild(view, widthSpec, heightSpec);
            int measuredWidth = View.MeasureSpec.getSize(widthSpec);
            int measuredHeight = view.getMeasuredHeight() + mPadding;
            int line = mItemCount / getSpanCount();
            if (mItemCount % getSpanCount() != 0) {
                line++;
            }
            setMeasuredDimension(measuredWidth, measuredHeight * line);
        }
    }
}
