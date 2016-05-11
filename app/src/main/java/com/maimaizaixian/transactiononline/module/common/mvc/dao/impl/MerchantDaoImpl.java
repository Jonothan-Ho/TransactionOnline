package com.maimaizaixian.transactiononline.module.common.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.account.domain.parser.UserParser;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IMerchantController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IMerchantDao;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public class MerchantDaoImpl extends BaseDao implements IMerchantDao {

    public static final String API_MERCHANT_LIST = "api/users?";
    private IMerchantController mMerchantController;

    public MerchantDaoImpl(IBaseController baseController) {
        super(baseController);
        mMerchantController = (IMerchantController) baseController;
    }

    @Override
    public void getMerchantList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<User>(ObjectRequestCallBack.Json_parser.Array, new UserParser()) {

            @Override
            public void onResponse(List<User> array, Domain domain) {
                ViewUtil.showSnackbar(mMerchantController.getRootView(), domain.getMsg());
                if (domain.isSuccess() && array != null) {
                    mMerchantController.onCompleteMerchants(array, domain.getPage());
                }
            }
        });
    }

    @Override
    public void getMerchantInfo(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<User>(ObjectRequestCallBack.Json_parser.Object, new UserParser()) {
                    @Override
                    public void onResponse(User obj, Domain domain) {
                        super.onResponse(obj, domain);
                    }
                }
        );
    }
}
