package com.maimaizaixian.transactiononline.module.hall.adapter.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2015/10/21.
 */
public class ImageSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public ImageSpaceItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = mSpace;
    }
}
