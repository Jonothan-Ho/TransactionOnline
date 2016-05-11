package com.maimaizaixian.transactiononline.module.account.domain.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.maimaizaixian.transactiononline.framework.http.parser.BaseParser;
import com.maimaizaixian.transactiononline.framework.http.parser.IJsonParser;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.TextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class UserParser extends BaseParser<User> implements IJsonParser<User> {

    @Override
    public User parseObject(String data, Class<User> clazz) {

        User user = null;
        try {
            user = JSON.parseObject(data, User.class);
            JSONObject object = new JSONObject(data);
            String text = object.getString("trade_category");
            if (!TextUtils.isEmpty(text)) {
                user.setCategories(JSON.parseArray(text, User.Category.class));
            } else {
                user.setCategories(new ArrayList<User.Category>());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> parseArrayObject(String data, Class<User> clazz) {
        List<User> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                User user = this.parseObject(jsonArray.getString(i), clazz);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.printf("===>array=>" + e.getMessage());
        }

        return list;
    }
}
