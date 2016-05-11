package com.maimaizaixian.transactiononline.module.home.mvc.parser;

import com.alibaba.fastjson.JSON;
import com.maimaizaixian.transactiononline.framework.http.parser.BaseParser;
import com.maimaizaixian.transactiononline.framework.http.parser.IJsonParser;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class CategoryParser extends BaseParser<Category> implements IJsonParser<Category> {

    @Override
    public Category parseObject(String data, Class<Category> clazz) {
        Category category = com.alibaba.fastjson.JSONObject.parseObject(data, clazz);
        try {
            JSONObject jsonObject = new JSONObject(data);
            String child = jsonObject.getString("child");
            // List<Category> categories = JSON.parseArray(child, Category.class);
            //category.setChild(categories);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return category;

    }

    @Override
    public List<Category> parseArrayObject(String data, Class<Category> clazz) {
        List<Category> list = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                String text = jsonArray.getString(i);
                Category category = parseObj(text, CategoryContent.class);
                list.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Category parseObj(String data, Class<CategoryContent> clazz) {
        Category category = com.alibaba.fastjson.JSONObject.parseObject(data, Category.class);
        try {
            JSONObject jsonObject = new JSONObject(data);
            String child = jsonObject.getString("child");
            List<CategoryContent> categories = JSON.parseArray(child, CategoryContent.class);
            category.setChild(categories);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return category;
    }

}
