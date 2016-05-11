package com.maimaizaixian.transactiononline.framework.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.maimaizaixian.transactiononline.utils.LogUtil;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public class HttpUtil {

    private static HttpUtils httpUtils;
    public static final int THREAD_POOL_SIZE = 10;
    public static final String HEADER_TOKEN = "accesstoken";
    public static String token;

    //public static final String IP = "http://192.168.1.15/buyonline/";
   //public static final String IP = "http://192.168.1.20/tradeonline/";
    //public static final String IP = "http://123.56.195.163/buyonline/";
    public static final String IP = "http://123.56.195.163/";


    static {
        httpUtils = new HttpUtils();
        httpUtils.configRequestThreadPoolSize(THREAD_POOL_SIZE);
        httpUtils.configSoTimeout(10000);
        httpUtils.configTimeout(10000);
        httpUtils.configResponseTextCharset("UTF-8");
        httpUtils.configHttpCacheSize(0);
    }


    /**
     * @param url
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> HttpHandler<T> get(String url, RequestCallBack<T> callBack) {
        RequestParams requestParams = new RequestParams();
        requestParams.addHeader(HEADER_TOKEN, token);
        LogUtil.printf("Token==>" + token);
        HttpHandler<T> handler = httpUtils.send(HttpRequest.HttpMethod.GET,getAbsoluteURL(url),requestParams,callBack);
        LogUtil.printf(getAbsoluteURL(url));
        return handler;
    }

    /**
     * @param url
     * @param params
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> HttpHandler<T> post(String url, RequestParams params, RequestCallBack<T> callBack) {
        params.addHeader(HEADER_TOKEN, token);
        HttpHandler<T> handler = httpUtils.send(HttpRequest.HttpMethod.POST, getAbsoluteURL(url), params, callBack);
        LogUtil.printf(getAbsoluteURL(url));
        return handler;
    }


    /**
     * @param url
     * @param params
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> HttpHandler<T> put(String url, RequestParams params, RequestCallBack<T> callBack) {
        params.addHeader(HEADER_TOKEN, token);
        HttpHandler<T> handler = httpUtils.send(HttpRequest.HttpMethod.PUT, getAbsoluteURL(url), params, callBack);
        LogUtil.printf(getAbsoluteURL(url));
        return handler;
    }

    /**
     * @param url
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> HttpHandler<T> delete(String url, RequestCallBack<T> callBack) {
        RequestParams requestParams = new RequestParams();
        requestParams.addHeader(HEADER_TOKEN, token);
        HttpHandler<T> handler = httpUtils.send(HttpRequest.HttpMethod.DELETE,getAbsoluteURL(url),requestParams, callBack);
        LogUtil.printf(getAbsoluteURL(url));
        return handler;
    }

    /**
     * @param url
     * @return
     */
    private static String getAbsoluteURL(String url) {
        return new StringBuilder(IP).append(url).toString();
    }


}
