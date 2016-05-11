package com.maimaizaixian.transactiononline.module.hall.domain.impl;

import com.maimaizaixian.transactiononline.module.hall.domain.Area;

/**
 * Created by Administrator on 2015/11/6.
 */
public class AreaImpl implements Area {
    @Override
    public String getProvinceName() {
        return "四川省";
    }

    @Override
    public String getCityName() {
        return "北京";
    }
}
