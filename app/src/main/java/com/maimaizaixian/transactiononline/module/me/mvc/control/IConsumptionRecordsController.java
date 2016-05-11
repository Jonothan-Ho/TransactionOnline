package com.maimaizaixian.transactiononline.module.me.mvc.control;

import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;

import java.util.List;

/**
 * Created by Administrator on 2015/12/8.
 */
public interface IConsumptionRecordsController extends IBaseController {


    enum Consumption_Action {
        Withdrawal,InviteCode,BindCard
    }


    void onComplete(List<ConsumptionRecords> recordses, int page);

    void onComplete(Consumption_Action action);

    void onComplete(Consumption_Action action, String data);


}
