package com.maimaizaixian.transactiononline.module.common.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IProductDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.ProductDaoImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IProductService;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ProductServiceImpl extends BaseService implements IProductService {

    private IProductDao dao;

    public ProductServiceImpl(IBaseController controller) {
        super(controller);
        dao = new ProductDaoImpl(controller);
    }

    @Override
    public void getProductList(String model, String uid, int page) {
        StringBuilder builder = new StringBuilder(IProductDao.API_PRODUCT_LIST);
        builder.append("model=").append(model).append("&uid=").append(uid).append("&p=").append(page);
        mController.openDialog();
        dao.getProductList(builder.toString());
    }

    @Override
    public void deleteProduct(String id) {
        StringBuilder builder = new StringBuilder(IProductDao.API_PRODUCT_LIST);
        builder.append("id=").append(id);
        mController.openDialog();
        dao.deleteProduct(builder.toString());
    }

    @Override
    public void addProduct(String model, String title, String imgpiclist, String description) {
        if (TextUtils.isEmpty(title)) {
            ViewUtil.showSnackbar(mController.getRootView(), "标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(imgpiclist)) {
            ViewUtil.showSnackbar(mController.getRootView(), "图片不能为空");
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("model", model);
        params.addBodyParameter("title", title);
        params.addBodyParameter("imgpiclist", imgpiclist);
        params.addBodyParameter("description", description);

        mController.openDialog();
        dao.addProduct(params);

    }
}
