package com.maimaizaixian.transactiononline.module.me.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/8.
 */
public interface IConsumptionRecordsDao extends IBaseDao {

    public static final String API_LIST = "api/userFund";
    public static final String API_BANK = "api/item";
    public static final String API_CODE = "api/users/useIncode";
    public static final String API_AMOUNT = "api/userFund/kitingAmount";


    void getConsumptionRecordsList(String url);

    void withDrawal(RequestParams params);

    void sendInviteCode(RequestParams params);

    void bindBankCard(RequestParams params);

    void getWithdrawal(String url);

}
