package com.maimaizaixian.transactiononline.module.common.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/2.
 */
public abstract class CardModel implements Serializable {

    public abstract String getTitle();

    public abstract String getImage();

    public abstract String getCompany_name();

    public abstract String getAddress();

    public abstract String getTrade();

    public abstract String getDate();

    public abstract String getContent();

    public abstract String[] getImageList();

}
