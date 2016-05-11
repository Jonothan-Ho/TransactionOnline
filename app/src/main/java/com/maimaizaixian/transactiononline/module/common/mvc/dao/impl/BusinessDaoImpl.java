package com.maimaizaixian.transactiononline.module.common.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IBusinessDao;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class BusinessDaoImpl extends BaseDao implements IBusinessDao {

    public static final String API_MATCH_CATEGORY = "api/category?";
    public static final String API_PUBLISH_BUY = "api/trade";


    private IBusinessController mPublishController;

    public BusinessDaoImpl(IBaseController baseController) {
        super(baseController);
        mPublishController = (IBusinessController) baseController;
    }

    @Override
    public void getCategoryByKey(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<CategoryContent>
                (ObjectRequestCallBack.Json_parser.Array, CategoryContent.class) {
            @Override
            public void onResponse(List<CategoryContent> array, Domain domain) {
                mPublishController.closeDialog();
                if (domain.isSuccess()) {
                    mPublishController.onMatchResult(array);
                }
            }
        });
    }

    @Override
    public void publishBuy(RequestParams params) {
        HttpUtil.post(API_PUBLISH_BUY, params, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Object, Business.class) {
            @Override
            public void onResponse(Business obj, Domain domain) {
                mPublishController.closeDialog();
                ViewUtil.showSnackbar(mPublishController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    mPublishController.onCompleteBusiness(obj, IBusinessController.Business_Action.Publish);
                }
            }
        });
    }

    @Override
    public void updateBusiness(RequestParams params) {
        HttpUtil.put(API_PUBLISH_BUY, params, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Object, Business.class) {
            @Override
            public void onResponse(Business obj, Domain domain) {
                mPublishController.closeDialog();
                ViewUtil.showSnackbar(mPublishController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    mPublishController.onCompleteBusiness(obj, IBusinessController.Business_Action.Publish);
                }
            }
        });
    }

    @Override
    public void getBusinessList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Array, Business.class) {
            @Override
            public void onResponse(List<Business> array, Domain domain) {
                if (domain.isSuccess() && array != null) {
                    mPublishController.onCompleteBusiness(array, domain.getPage());
                }
            }
        });
    }

    @Override
    public void pay(RequestParams params) {
        HttpUtil.post(API_PAY, params, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Object, Business.class) {
            @Override
            public void onResponse(Business obj, Domain domain) {
                mPublishController.closeDialog();
                if (domain.isSuccess()) {
                    mPublishController.onCompleteBusiness(obj, IBusinessController.Business_Action.Pay);
                }
            }
        });
    }

    @Override
    public void report(RequestParams params) {
        HttpUtil.post(API_REPORT, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                mPublishController.closeDialog();
                ViewUtil.showSnackbar(mPublishController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    mPublishController.onComplete(IBusinessController.Business_Type.Report);
                }
            }
        });
    }

    @Override
    public void collect(RequestParams params) {
        HttpUtil.post(API_COLLECT, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                mPublishController.closeDialog();
                ViewUtil.showSnackbar(mPublishController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    mPublishController.onComplete(IBusinessController.Business_Type.Collect);
                }
            }
        });
    }

    @Override
    public void unCollect(String url) {
        HttpUtil.delete(url, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                mPublishController.closeDialog();
                ViewUtil.showSnackbar(mPublishController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    mPublishController.onComplete(IBusinessController.Business_Type.Collect);
                }
            }
        });
    }

    @Override
    public void getBusinessPush(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Array, Business.class) {
            @Override
            public void onResponse(List<Business> array, Domain domain) {
                if (array != null && domain.isSuccess()) {
                    mPublishController.onCompleteBusiness(array, domain.getPage());
                }
            }
        });
    }

    @Override
    public void getBusinessInfo(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Array, Business.class) {
            @Override
            public void onResponse(List<Business> array, Domain domain) {
                mPublishController.closeDialog();
                ViewUtil.showSnackbar(mPublishController.getRootView(), domain.getMsg());
                if (domain.isSuccess() && array != null && array.size() > 0) {
                    mPublishController.onCompleteBusiness(array.get(0), IBusinessController.Business_Action.Info);
                }
            }
        });
    }

    @Override
    public void closeBusiness(RequestParams params) {
        HttpUtil.put(API_PUBLISH_BUY, params, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Object, Business.class) {
            @Override
            public void onResponse(Business obj, Domain domain) {
                mPublishController.closeDialog();
                if (domain.isSuccess()) {
                    mPublishController.onCompleteBusiness(obj, IBusinessController.Business_Action.Close);
                }
            }
        });
    }

    @Override
    public void deleteBusiness(String url) {
        HttpUtil.delete(url, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                mPublishController.closeDialog();
                if (domain.isSuccess()) {
                    mPublishController.onComplete(IBusinessController.Business_Type.Delete);
                }
            }
        });
    }
}
