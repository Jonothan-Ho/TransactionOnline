package com.maimaizaixian.transactiononline.framework.http;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.maimaizaixian.transactiononline.utils.CommonUtil;
import com.maimaizaixian.transactiononline.utils.LogUtil;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2015/11/20.
 */
public abstract class TextRequestCallBack extends RequestCallBack<String> {

    public static final String REQUEST_GET_METHOD = "X-Request-Method";
    public static final String REQUEST_TOKEN = "X-App-Token";
    public static final String REQUEST_PAGE = "X-Pagination-Current-Page";
    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_DELETE = "delete";

    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        LogUtil.printf("==>" + responseInfo.statusCode + "==>" + responseInfo.result);
        Header tokenHeaders[] = responseInfo.getHeaders(REQUEST_TOKEN);
        if (tokenHeaders != null && tokenHeaders.length > 0) {
            Header header = tokenHeaders[0];
            HttpUtil.token = header.getValue();
        }
        String method = null;
        Header methodHeaders[] = responseInfo.getHeaders(REQUEST_GET_METHOD);
        if (methodHeaders != null && methodHeaders.length > 0) {
            Header header = methodHeaders[0];
            method = header.getValue();
        }
        int currentPage = 0;
        Header pageHeaders[] = responseInfo.getHeaders(REQUEST_PAGE);
        if (pageHeaders != null && pageHeaders.length > 0) {
            Header header = pageHeaders[0];
            try {
                currentPage = Integer.parseInt(header.getValue());
            } catch (Exception e) {
                currentPage = 0;
            }
        }


        Domain domain = null;
        LogUtil.printf("Method=============>"+method);
        if (!TextUtils.isEmpty(method)) {
            switch (method) {
                case METHOD_POST:
                case METHOD_DELETE:
                case METHOD_PUT:
                    domain = getPostDomain(responseInfo);
                    break;
                case METHOD_GET:
                    domain = getGetDomain(responseInfo);
                    break;
                default:
                    domain = new Domain();
                    domain.setIsSuccess(false);
                    domain.setMsg("服务器未返回请求方法");
                    break;
            }
        } else {
            domain = new Domain();
            domain.setIsSuccess(false);
            domain.setMsg("服务器未返回请求方法");
        }
        domain.setPage(currentPage);
        onResult(domain);
    }

    @Override
    public void onFailure(HttpException e, String s) {
        try {
            LogUtil.printf("=>ExceptionCode" + e.getExceptionCode() + "===>" + URLDecoder.decode(s, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        failure(CommonUtil.getHttpExceptionMessage(s));
    }

    @Override
    public void onCancelled() {
        super.onCancelled();
        failure("已经取消网络连接");
    }

    /**
     *
     */
    private void failure(String message) {
        Domain domain = new Domain();
        domain.setIsSuccess(false);
        domain.setMsg(message);
        onResult(domain);
    }

    /**
     * @param domain
     */
    public abstract void onResult(Domain domain);


    /**
     * @param responseInfo
     */
    private Domain getGetDomain(ResponseInfo<String> responseInfo) {
        Domain domain = null;
        try {
            String text = responseInfo.result;
            domain = new Domain();
            domain.setIsSuccess(true);
            domain.setData(text);
        } catch (Exception e) {
            if (domain == null) {
                domain = new Domain();
            }
            domain.setIsSuccess(false);
            domain.setMsg("服务器异常JSON");
        }
        return domain;
    }


    /**
     * @param responseInfo
     * @return
     */
    private Domain getPostDomain(ResponseInfo<String> responseInfo) {
        Domain domain = null;
        try {
            String text = responseInfo.result;
            domain = JSONObject.parseObject(text, Domain.class);
            domain.setIsSuccess(true);
        } catch (Exception e) {
            if (domain == null) {
                domain = new Domain();
            }
            domain.setIsSuccess(false);
            domain.setMsg("服务器异常JSON");
        }
        return domain;
    }

}

