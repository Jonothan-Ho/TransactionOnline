package com.maimaizaixian.transactiononline.module.me.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/8.
 */
public interface IConsumptionRecordsService extends IBaseService {

    void getConsumptionRecordsList(int page);

    void withDrawal(String money);

    void sendInvateCode(String code);

    void bindBandCard(String name, String account, String bank, String code);

    void getWithDrawalRecordsList(int page);

    void getSumWithdrawal();

}
