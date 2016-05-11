package com.maimaizaixian.transactiononline.module.common.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IBusinessDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.BusinessDaoImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/11/26.
 */
public class BusinessServiceImpl extends BaseService implements IBusinessService {

    private IBusinessDao dao;


    public BusinessServiceImpl(IBaseController controller) {
        super(controller);
        dao = new BusinessDaoImpl(controller);
    }

    @Override
    public void matchKeyToCategory(String key) {
        if (TextUtils.isEmpty(key)) {
            ViewUtil.showSnackbar(mController.getRootView(), "请先填写关键字");
            return;
        }

        StringBuilder builder = new StringBuilder(BusinessDaoImpl.API_MATCH_CATEGORY);
        builder.append("searchtitle=").append(key).append("&stage=2");
        mController.openDialog();
        dao.getCategoryByKey(builder.toString());

    }

    @Override
    public void publishBusiness(String mode, int id, int property, int push_num, String desc, String address, String contact, String num, String images, String longitude, String latitude) {
        if (id == 0) {
            ViewUtil.showSnackbar(mController.getRootView(), "请选择一个分类");
            return;
        }

        if (TextUtils.isEmpty(desc)) {
            ViewUtil.showSnackbar(mController.getRootView(), "详情描述不能为空");
            return;
        }
        if (TextUtils.isEmpty(longitude)) {
            ViewUtil.showSnackbar(mController.getRootView(), "当前位置经度不能为空");
            return;
        }
        if (TextUtils.isEmpty(latitude)) {
            ViewUtil.showSnackbar(mController.getRootView(), "当前位置纬度不能为空");
            return;
        }

        if (TextUtils.isEmpty(address)) {
            ViewUtil.showSnackbar(mController.getRootView(), "地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(num)) {
            ViewUtil.showSnackbar(mController.getRootView(), "数量不能为空");
            return;
        }

        if (!TextUtil.isValidPhone(contact, mController.getRootView())) {
            return;
        }


        RequestParams requestParams = new RequestParams();
        requestParams.addBodyParameter("cid", id + "");
        requestParams.addBodyParameter("priority_selection", property + "");
        requestParams.addBodyParameter("recommend_num", push_num + "");
        requestParams.addBodyParameter("need_desc", desc);
        requestParams.addBodyParameter("address", address);
        requestParams.addBodyParameter("contact", contact);
        requestParams.addBodyParameter("num", num);
        requestParams.addBodyParameter("imgpiclist", images);
        requestParams.addBodyParameter("model", mode);
        requestParams.addBodyParameter("longitude", latitude);
        requestParams.addBodyParameter("latitude", latitude);


        mController.openDialog();
        dao.publishBuy(requestParams);


    }

    @Override
    public void updateBusiness(String businessID, String mode, int id, int property, int push_num, String desc, String address, String contact, String num, String images, String longitude, String latitude) {

        if (TextUtils.isEmpty(businessID)) {
            ViewUtil.showSnackbar(mController.getRootView(), "商机ID丢失，不能更新发布");
            return;
        }

        if (id == 0) {
            ViewUtil.showSnackbar(mController.getRootView(), "请选择一个分类");
            return;
        }

        if (TextUtils.isEmpty(desc)) {
            ViewUtil.showSnackbar(mController.getRootView(), "详情描述不能为空");
            return;
        }
        if (TextUtils.isEmpty(longitude)) {
            ViewUtil.showSnackbar(mController.getRootView(), "当前位置经度不能为空");
            return;
        }
        if (TextUtils.isEmpty(latitude)) {
            ViewUtil.showSnackbar(mController.getRootView(), "当前位置纬度不能为空");
            return;
        }

        if (TextUtils.isEmpty(address)) {
            ViewUtil.showSnackbar(mController.getRootView(), "地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(num)) {
            ViewUtil.showSnackbar(mController.getRootView(), "数量不能为空");
            return;
        }

        if (!TextUtil.isValidPhone(contact, mController.getRootView())) {
            return;
        }


        RequestParams requestParams = new RequestParams();
        requestParams.addBodyParameter("cid", id + "");
        requestParams.addBodyParameter("id", businessID);
        requestParams.addBodyParameter("priority_selection", property + "");
        requestParams.addBodyParameter("recommend_num", push_num + "");
        requestParams.addBodyParameter("need_desc", desc);
        requestParams.addBodyParameter("address", address);
        requestParams.addBodyParameter("contact", contact);
        requestParams.addBodyParameter("num", num);
        requestParams.addBodyParameter("imgpiclist", images);
        requestParams.addBodyParameter("model", mode);
        requestParams.addBodyParameter("longitude", latitude);
        requestParams.addBodyParameter("latitude", latitude);


        mController.openDialog();
        dao.updateBusiness(requestParams);

    }

    @Override
    public void publishBusiness(String title, String desc, String images, String longitude, String latitude) {
        RequestParams requestParams = new RequestParams();
        requestParams.addBodyParameter("type", TYPE_NEIGHBORHOOD);
        requestParams.addBodyParameter("need_desc", desc);
        requestParams.addBodyParameter("imgpiclist", images);
        requestParams.addBodyParameter("title", images);
        requestParams.addBodyParameter("longitude", longitude);
        requestParams.addBodyParameter("latitude", latitude);
        mController.openDialog();
        dao.publishBuy(requestParams);
    }

    @Override
    public void getBusinessList(String model, String type, String cid, int page, String order_name, String order_type, int cityID) {
        StringBuilder builder = new StringBuilder(IBusinessDao.API_BUSINESS_LIST);
        builder.append("model=").append(model).append("&type=").append(type)
                .append("&cid=").append(cid)
                .append("&order_name=").append(order_name)
                .append("&order_type=").append(order_type)
                .append("&city=").append(cityID)
                .append("&p=").append(page);

        dao.getBusinessList(builder.toString());
    }

    @Override
    public void getBusinessList(String title, String longitude, String latitude, int page) {
        StringBuilder builder = new StringBuilder(IBusinessDao.API_BUSINESS_LIST);
        builder.append("&type=").append(IBusinessService.TYPE_NEIGHBORHOOD)
                .append("&longitude=").append(longitude)
                .append("&latitude=").append(latitude)
                .append("&p=").append(page);

        if (!TextUtils.isEmpty(title)) {
            builder.append("&title=").append(title);
        }

        dao.getBusinessList(builder.toString());
    }

    @Override
    public void payBusiness(String id, String passwd) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        params.addBodyParameter("alipay_password", passwd);
        mController.openDialog();
        dao.pay(params);
    }

    @Override
    public void report(String id, String content) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        params.addBodyParameter("comment", content);
        mController.openDialog();
        dao.report(params);

    }

    @Override
    public void collect(String id) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("chance_id", id);
        mController.openDialog();
        dao.collect(params);
    }

    @Override
    public void unCollect(String id) {
        StringBuilder builder = new StringBuilder(IBusinessDao.API_COLLECT);
        builder.append("?chance_id=").append(id);
        mController.openDialog();
        dao.unCollect(builder.toString());
    }

    @Override
    public void getBusinessPushList(int p) {
        StringBuilder builder = new StringBuilder(IBusinessDao.API_BUSINESS_PUSH);
        builder.append("p=").append(p);
        dao.getBusinessPush(builder.toString());
    }

    @Override
    public void getBusinessInfo(String id) {
        StringBuilder builder = new StringBuilder(BusinessDaoImpl.API_PUBLISH_BUY);
        builder.append("?id=").append(id);
        mController.openDialog();
        dao.getBusinessInfo(builder.toString());
    }

    @Override
    public void getHistoryList(String id, String model, int p) {
        StringBuilder builder = new StringBuilder(BusinessDaoImpl.API_PUBLISH_BUY);
        builder.append("?uid=").append(id).append("&model=").append(model).append("&p=").append(p);
        dao.getBusinessList(builder.toString());
    }

    @Override
    public void closeBusiness(String id) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", id);
        params.addBodyParameter("status", "2");
        mController.openDialog();
        dao.closeBusiness(params);
    }

    @Override
    public void deleteBusiness(String id) {
        StringBuilder builder = new StringBuilder(BusinessDaoImpl.API_PUBLISH_BUY);
        builder.append("?id=").append(id);
        mController.openDialog();
        dao.deleteBusiness(builder.toString());
    }
}
