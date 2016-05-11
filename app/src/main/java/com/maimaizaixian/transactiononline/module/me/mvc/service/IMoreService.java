package com.maimaizaixian.transactiononline.module.me.mvc.service;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/11.
 */
public interface IMoreService extends IBaseService {


    void feedBack(String content);

    void getRecruitList(int page);

    void makeResume(RequestParams params);

    void updateResume(RequestParams params);

    void getResume();

    void sendResume(String id);


}
