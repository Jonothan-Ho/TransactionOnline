package com.maimaizaixian.transactiononline.framework.listener;

import android.view.View;

/**
 * {@link android.support.v7.widget.RecyclerView} and {@link com.maimaizaixian.transactiononline.framework.adapter.BaseAdapter}
 * <p/>
 * Created by Administrator on 2015/10/27.
 */
public interface OnItemDeleteListener {

    void delete(View v, int position);
}
