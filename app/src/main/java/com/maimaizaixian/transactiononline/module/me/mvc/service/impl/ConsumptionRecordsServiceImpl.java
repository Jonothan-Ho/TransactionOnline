package com.maimaizaixian.transactiononline.module.me.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IConsumptionRecordsDao;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.impl.ConsumptionRecordsDaoImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IConsumptionRecordsService;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/8.
 */
public class ConsumptionRecordsServiceImpl extends BaseService implements IConsumptionRecordsService {

    private IConsumptionRecordsDao dao;

    public ConsumptionRecordsServiceImpl(IBaseController controller) {
        super(controller);
        dao = new ConsumptionRecordsDaoImpl(controller);
    }

    @Override
    public void getConsumptionRecordsList(int page) {
        StringBuilder builder = new StringBuilder(IConsumptionRecordsDao.API_LIST);
        builder.append("?p=").append(page);
        dao.getConsumptionRecordsList(builder.toString());
    }

    @Override
    public void withDrawal(String money) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("model", "kiting");
        params.addBodyParameter("amount", "money");
        mController.openDialog();
        dao.withDrawal(params);
    }

    @Override
    public void sendInvateCode(String code) {
        if (TextUtils.isEmpty(code)) {
            ViewUtil.showSnackbar(mController.getRootView(), "邀请码不能为空");
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("fromecode", code);

        mController.openDialog();

        dao.sendInviteCode(params);


    }

    @Override
    public void bindBandCard(String name, String account, String bank, String code) {
        if (TextUtils.isEmpty(name)) {
            ViewUtil.showSnackbar(mController.getRootView(), "持卡人姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            ViewUtil.showSnackbar(mController.getRootView(), "银行账户不能为空");
            return;
        }
        if (TextUtils.isEmpty(bank)) {
            ViewUtil.showSnackbar(mController.getRootView(), "发卡银行不能为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ViewUtil.showSnackbar(mController.getRootView(), "验证码不能为空");
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("model", "bank");
        params.addBodyParameter("name", name);
        params.addBodyParameter("account", account);
        params.addBodyParameter("bank", bank);
        params.addBodyParameter("code", code);

        mController.openDialog();

        dao.bindBankCard(params);


    }

    @Override
    public void getWithDrawalRecordsList(int page) {
        StringBuilder builder = new StringBuilder(IConsumptionRecordsDao.API_LIST);
        builder.append("?p=").append(page).append("&type=2");
        dao.getConsumptionRecordsList(builder.toString());
    }

    @Override
    public void getSumWithdrawal() {
        mController.openDialog();
        dao.getWithdrawal(IConsumptionRecordsDao.API_AMOUNT);
    }
}
