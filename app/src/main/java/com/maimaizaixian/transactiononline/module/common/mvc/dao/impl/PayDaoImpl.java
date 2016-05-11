package com.maimaizaixian.transactiononline.module.common.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IPayController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IPayDao;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
public class PayDaoImpl extends BaseDao implements IPayDao {

    private IPayController mPayController;

    public PayDaoImpl(IBaseController baseController) {
        super(baseController);
        mPayController = (IPayController) baseController;
    }

    @Override
    public void getAccountBalance() {
        HttpUtil.get(API_BALANCE, new ObjectRequestCallBack<PayBalance>(ObjectRequestCallBack.Json_parser.Array, PayBalance.class) {
            @Override
            public void onResponse(List<PayBalance> array, Domain domain) {
                if (domain.isSuccess() && array != null && array.size() > 0) {
                    mPayController.onAccountBalance(array.get(0));
                }
            }
        });
    }

    @Override
    public void setPayPasswd(RequestParams params) {
        HttpUtil.post(API_SET_PASSWD, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                mPayController.closeDialog();
                if (domain.isSuccess()) {
                    mPayController.onComplete();
                }
            }
        });
    }

}
