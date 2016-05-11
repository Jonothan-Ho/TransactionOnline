package com.maimaizaixian.transactiononline.module.common.mvc.service.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IMerchantDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.MerchantDaoImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IMerchantService;

/**
 * Created by Administrator on 2015/11/27.
 */
public class MerchantServiceImpl extends BaseService implements IMerchantService {

    private IMerchantDao dao;

    public MerchantServiceImpl(IBaseController controller) {
        super(controller);
        dao = new MerchantDaoImpl(controller);
    }

    @Override
    public void getMerchantList(String type, String cid, int recommend_num, int p, String order_name, String order_type) {
        StringBuilder builder = new StringBuilder(MerchantDaoImpl.API_MERCHANT_LIST);
        builder.append("priority_selection=").append(type).append("&cid=").append(cid).append("&recommend_num=")
                .append(recommend_num).append("&p=").append(p)
                .append("&order_name=").append(order_name)
                .append("&order_type=").append(order_type);
        dao.getMerchantList(builder.toString());
    }

    @Override
    public void getMerchantInfo(String id) {
        String url = IMerchantDao.API_MERCHANT_INFO + id;
        mController.openDialog();
        dao.getMerchantInfo(url);
    }
}
