package com.maimaizaixian.transactiononline.module.home.mvc.dao.impl;

import com.lidroid.xutils.util.LogUtils;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.home.domain.Poster;
import com.maimaizaixian.transactiononline.module.home.mvc.controller.IHomeController;
import com.maimaizaixian.transactiononline.module.home.mvc.dao.IHomeDao;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class HomeDaoImpl extends BaseDao<Poster> implements IHomeDao {

    public static final String API_AD = "api/item/poster";
    private IHomeController homeController;

    public HomeDaoImpl(IBaseController baseController) {
        super(baseController);
        homeController = (IHomeController) baseController;
    }

    @Override
    public void getAdImages() {
        HttpUtil.get(API_AD, new ObjectRequestCallBack<Poster>(ObjectRequestCallBack.Json_parser.Array, Poster.class) {
            @Override
            public void onResponse(Poster obj, Domain domain) {

            }

            @Override
            public void onResponse(List<Poster> array, Domain domain) {
                ViewUtil.showSnackbar(homeController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    homeController.onAdImages(array);
                }
            }
        });
    }
}
