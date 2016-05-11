package com.maimaizaixian.transactiononline.module.me.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.me.domain.GroupRecruit;
import com.maimaizaixian.transactiononline.module.me.domain.Resume;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IMoreController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IMoreDao;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/12/11.
 */
public class MoreDaoImpl extends BaseDao implements IMoreDao {

    private IMoreController controller;

    public MoreDaoImpl(IBaseController baseController) {
        super(baseController);
        controller = (IMoreController) baseController;
    }

    @Override
    public void feedBack(RequestParams params) {
        HttpUtil.post(API_ITEM, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
                    @Override
                    public void onResponse(String obj, Domain domain) {
                        ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                        controller.closeDialog();
                        if (domain.isSuccess()) {
                            controller.onComplete(IMoreController.More_Action.feedBack);
                        }
                    }
                }
        );
    }

    @Override
    public void getRecruitList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<GroupRecruit>(ObjectRequestCallBack.Json_parser.Array, GroupRecruit.class) {
            @Override
            public void onResponse(List<GroupRecruit> array, Domain domain) {
                if (domain.isSuccess() && array != null) {
                    controller.onComplete(array, domain.getPage());
                }
            }
        });
    }

    @Override
    public void makeResume(RequestParams params) {
        HttpUtil.post(API_ITEM, params, new ObjectRequestCallBack<Resume>(ObjectRequestCallBack.Json_parser.Object, Resume.class) {
            @Override
            public void onResponse(Resume obj, Domain domain) {
                ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                mBaseController.closeDialog();
                if (domain.isSuccess()) {
                    controller.onComplete(obj, IMoreController.More_Action.makeResume);
                }
            }
        });
    }

    @Override
    public void updateResume(RequestParams params) {
        HttpUtil.put(API_ITEM, params, new ObjectRequestCallBack<Resume>(ObjectRequestCallBack.Json_parser.NULL, Resume.class) {
            @Override
            public void onResponse(Resume obj, Domain domain) {
                ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                mBaseController.closeDialog();
                if (domain.isSuccess()) {
                    controller.onComplete(obj, IMoreController.More_Action.updateResume);
                }
            }
        });
    }

    @Override
    public void getResume(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<Resume>(ObjectRequestCallBack.Json_parser.Array, Resume.class) {
            @Override
            public void onResponse(List<Resume> array, Domain domain) {
                controller.closeDialog();
                if (domain.isSuccess()) {
                    Resume resume = null;
                    if (array != null && array.size() > 0) {
                        resume = array.get(0);
                    }
                    controller.onComplete(resume, IMoreController.More_Action.Resume);
                }
            }
        });
    }

    @Override
    public void sendResume(RequestParams params) {
        HttpUtil.post(API_ITEM, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                mBaseController.closeDialog();
                if (domain.isSuccess()) {
                    controller.onComplete(IMoreController.More_Action.sendResume);
                }
            }
        });
    }
}
