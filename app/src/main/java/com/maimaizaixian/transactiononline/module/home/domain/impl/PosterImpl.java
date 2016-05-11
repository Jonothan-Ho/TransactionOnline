package com.maimaizaixian.transactiononline.module.home.domain.impl;

import com.maimaizaixian.transactiononline.module.home.domain.Poster;

/**
 * Created by Administrator on 2015/11/25.
 */
public class PosterImpl implements Poster {

    private String content;
    private String imgpic;

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgpic() {
        return imgpic;
    }

    public void setImgpic(String imgpic) {
        this.imgpic = imgpic;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getImgPic() {
        return imgpic;
    }
}
