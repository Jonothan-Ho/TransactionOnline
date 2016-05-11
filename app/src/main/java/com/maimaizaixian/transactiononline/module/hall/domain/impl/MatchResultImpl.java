package com.maimaizaixian.transactiononline.module.hall.domain.impl;


import com.maimaizaixian.transactiononline.module.hall.domain.MatchResult;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MatchResultImpl implements MatchResult {

    @Override
    public String[] getImageUrls() {
        String[] urls = {"http://pic26.nipic.com/20130105/11664993_134343361131_2.jpg",
                "http://img.sucai.redocn.com/attachments/images/201107/20110705/Redocn_2011062615171120.jpg",
                "http://pic17.nipic.com/20111031/7666075_110546353106_2.jpg",
                "http://picm.photophoto.cn/073/008/055/0080550102.jpg",
                "http://www.dabaoku.com/sucaidatu/gongye/gongyechanpin/0315425.jpg"};

        return urls;
    }
}
