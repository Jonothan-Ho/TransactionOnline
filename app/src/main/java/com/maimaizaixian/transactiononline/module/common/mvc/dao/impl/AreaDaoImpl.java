package com.maimaizaixian.transactiononline.module.common.mvc.dao.impl;

import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.common.domain.Area;
import com.maimaizaixian.transactiononline.module.common.domain.parser.AreaParser;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IAreaController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IAreaDao;

import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class AreaDaoImpl extends BaseDao implements IAreaDao {

    private IAreaController mAreaController;

    public AreaDaoImpl(IBaseController baseController) {
        super(baseController);
        mAreaController = (IAreaController) baseController;
    }

    @Override
    public void getAreaList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<Area>(ObjectRequestCallBack.Json_parser.Array,new AreaParser()){

            @Override
            public void onResponse(List<Area> array, Domain domain) {
                if (domain.isSuccess() && array != null) {
                    mAreaController.onCompleteArea(array);
                }
            }
        });
    }
}
