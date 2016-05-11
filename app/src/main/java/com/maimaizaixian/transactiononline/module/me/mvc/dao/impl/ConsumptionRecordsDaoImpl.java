package com.maimaizaixian.transactiononline.module.me.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IConsumptionRecordsController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IConsumptionRecordsDao;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/12/8.
 */
public class ConsumptionRecordsDaoImpl extends BaseService implements IConsumptionRecordsDao {

    private IConsumptionRecordsController consumptionRecordsController;

    public ConsumptionRecordsDaoImpl(IBaseController controller) {
        super(controller);
        consumptionRecordsController = (IConsumptionRecordsController) controller;
    }

    @Override
    public void getConsumptionRecordsList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<ConsumptionRecords>(ObjectRequestCallBack.Json_parser.Array, ConsumptionRecords.class) {
            @Override
            public void onResponse(List<ConsumptionRecords> array, Domain domain) {
                if (array != null && domain.isSuccess()) {
                    consumptionRecordsController.onComplete(array, domain.getPage());
                }
            }
        });
    }

    @Override
    public void withDrawal(RequestParams params) {
        HttpUtil.post(API_BANK, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(consumptionRecordsController.getRootView(), domain.getMsg());
                consumptionRecordsController.closeDialog();
                if (domain.isSuccess()) {
                    consumptionRecordsController.onComplete(IConsumptionRecordsController.Consumption_Action.Withdrawal);
                }
            }
        });
    }

    @Override
    public void sendInviteCode(RequestParams params) {
        HttpUtil.post(API_CODE, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(consumptionRecordsController.getRootView(), domain.getMsg());
                consumptionRecordsController.closeDialog();
                if (domain.isSuccess()) {
                    consumptionRecordsController.onComplete(IConsumptionRecordsController.Consumption_Action.InviteCode, domain.getData());
                }
            }
        });
    }

    @Override
    public void bindBankCard(RequestParams params) {
        HttpUtil.post(API_BANK, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(consumptionRecordsController.getRootView(), domain.getMsg());
                consumptionRecordsController.closeDialog();
                if (domain.isSuccess()) {
                    consumptionRecordsController.onComplete(IConsumptionRecordsController.Consumption_Action.BindCard);
                }
            }
        });
    }

    @Override
    public void getWithdrawal(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(consumptionRecordsController.getRootView(), domain.getMsg());
                mController.closeDialog();
                if (domain.isSuccess()) {
                    consumptionRecordsController.onComplete(IConsumptionRecordsController.Consumption_Action.Withdrawal, domain.getData());
                }
            }
        });
    }
}
