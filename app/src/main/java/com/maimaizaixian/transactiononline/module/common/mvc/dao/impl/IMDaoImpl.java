package com.maimaizaixian.transactiononline.module.common.mvc.dao.impl;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IMDao;
import com.maimaizaixian.transactiononline.utils.LogUtil;

/**
 * Created by Administrator on 2015/12/15.
 */
public class IMDaoImpl extends BaseDao implements IMDao {

    public IMDaoImpl(IBaseController baseController) {
        super(baseController);
    }

    @Override
    public void login(String username, String passed, EMCallBack callBack) {
        LogUtil.printf(username + "===>" + passed);
        EMChatManager.getInstance().login(username, passed, callBack);
    }
}
