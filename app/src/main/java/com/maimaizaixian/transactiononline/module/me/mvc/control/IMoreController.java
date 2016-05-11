package com.maimaizaixian.transactiononline.module.me.mvc.control;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.domain.GroupRecruit;
import com.maimaizaixian.transactiononline.module.me.domain.Resume;

import java.util.List;

/**
 * Created by Administrator on 2015/12/11.
 */
public interface IMoreController extends IBaseController {


    static enum More_Action {
        feedBack, Resume, makeResume, updateResume,sendResume
    }


    void onComplete(More_Action action);

    void onComplete(List<GroupRecruit> groupRecruits, int page);

    void onComplete(Resume resume, More_Action action);

}
