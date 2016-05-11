package com.maimaizaixian.transactiononline.module.common.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/1.
 */
public class Product implements Serializable {

    private String id;
    private String mid;
    private String uid;
    private String price;
    private String title;
    private String name;
    private String description;
    private String imgpiclist;
    private String create_time;
    private String update_time;
    private String[] imgpiclist_link;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgpiclist() {
        return imgpiclist;
    }

    public void setImgpiclist(String imgpiclist) {
        this.imgpiclist = imgpiclist;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String[] getImgpiclist_link() {
        return imgpiclist_link;
    }

    public void setImgpiclist_link(String[] imgpiclist_link) {
        this.imgpiclist_link = imgpiclist_link;
    }

    public String getFirstImage() {
        if (imgpiclist_link != null && imgpiclist_link.length > 0) {
            return imgpiclist_link[0];
        } else {
            return null;
        }
    }

}
