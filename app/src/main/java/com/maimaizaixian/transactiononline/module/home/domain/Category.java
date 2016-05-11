package com.maimaizaixian.transactiononline.module.home.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class Category implements Serializable {
    private int id;
    private int sort;
    private int pid;
    private int mid;
    private int status;
    private String alias;
    private String imgpic;
    private String title;
    private String remark;
    private String route;
    private List<CategoryContent> child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImgpic() {
        return imgpic;
    }

    public void setImgpic(String imgpic) {
        this.imgpic = imgpic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<CategoryContent> getChild() {
        return child;
    }

    public void setChild(List<CategoryContent> child) {
        this.child = child;
    }

}
