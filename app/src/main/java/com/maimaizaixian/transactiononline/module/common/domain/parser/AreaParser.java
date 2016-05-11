package com.maimaizaixian.transactiononline.module.common.domain.parser;

import com.alibaba.fastjson.JSON;
import com.maimaizaixian.transactiononline.framework.http.parser.BaseParser;
import com.maimaizaixian.transactiononline.framework.http.parser.IJsonParser;
import com.maimaizaixian.transactiononline.module.common.domain.Area;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class AreaParser extends BaseParser<Area> implements IJsonParser<Area> {

    @Override
    public Area parseObject(String data, Class<Area> clazz) {
        Area area = JSON.parseObject(data, Area.class);
        try {
            JSONObject object = new JSONObject(data);
            String text = object.getString("child");
            List<Area.AreaChild> areas = JSON.parseArray(text, Area.AreaChild.class);
            area.setChildren(areas);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return area;
    }

    @Override
    public List<Area> parseArrayObject(String data, Class<Area> clazz) {
        List<Area> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                Area area = this.parseObject(array.getString(i), clazz);
                list.add(area);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
