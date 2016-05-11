package com.maimaizaixian.transactiononline.module.common.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/27.
 */
public class ImagesResult implements Serializable {

    private String data;
    private String path;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
