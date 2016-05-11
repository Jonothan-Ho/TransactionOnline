package com.maimaizaixian.transactiononline.module.common.listener;


import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public interface OnFragmentArgumentListener<T> {

    void onComplete(List<T> data, int page);
}

