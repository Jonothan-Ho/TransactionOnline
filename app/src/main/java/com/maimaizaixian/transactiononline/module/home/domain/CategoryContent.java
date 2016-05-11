package com.maimaizaixian.transactiononline.module.home.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/10/27.
 */
public class CategoryContent implements Parcelable {

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
    private String imgpic_link;

    public String getImgpic_link() {
        return imgpic_link;
    }

    public void setImgpic_link(String imgpic_link) {
        this.imgpic_link = imgpic_link;
    }

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

    public CategoryContent() {

    }

    public CategoryContent(Parcel in) {
        id = in.readInt();
        sort = in.readInt();
        pid = in.readInt();
        mid = in.readInt();
        status = in.readInt();
        alias = in.readString();
        imgpic = in.readString();
        title = in.readString();
        remark = in.readString();
        route = in.readString();
        imgpic_link = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(sort);
        dest.writeInt(pid);
        dest.writeInt(mid);
        dest.writeInt(status);
        dest.writeString(alias);
        dest.writeString(imgpic);
        dest.writeString(title);
        dest.writeString(remark);
        dest.writeString(route);
        dest.writeString(imgpic_link);
    }

    public static final Parcelable.Creator<CategoryContent> CREATOR = new Parcelable.Creator<CategoryContent>() {
        public CategoryContent createFromParcel(Parcel in) {
            return new CategoryContent(in);
        }

        public CategoryContent[] newArray(int size) {
            return new CategoryContent[size];
        }
    };

}
