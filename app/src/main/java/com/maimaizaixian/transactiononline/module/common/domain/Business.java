package com.maimaizaixian.transactiononline.module.common.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/27.
 */
public class Business extends CardModel {

    private int cid;
    private int priority_selection;
    private int recommend_num;
    private String uid;
    private int mid;
    private int type;
    private int status;
    private float longitude;
    private float latitude;
    private String id;
    private String num;
    private String need_desc;
    private String address;
    private String contact;
    private String title;
    private String link_id;
    private String province;
    private String city;
    private String county;
    private String nickname;
    private String real_name;
    private String category_title;
    private String model_alias;
    private String category_alias;
    private String imgpic;
    private String imgpiclist;
    private String head_link;
    private String[] imgpiclist_link;
    private String create_time;
    private int has_pay;
    private int has_collect;
    private String distance;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getHas_collect() {
        return has_collect;
    }

    public void setHas_collect(int has_collect) {
        this.has_collect = has_collect;
    }

    public int getHas_pay() {
        return has_pay;
    }

    public void setHas_pay(int has_pay) {
        this.has_pay = has_pay;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getImage() {
        return head_link;
    }

    @Override
    public String getCompany_name() {
        return "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getModel_alias() {
        return model_alias;
    }

    public void setModel_alias(String model_alias) {
        this.model_alias = model_alias;
    }

    public String getCategory_alias() {
        return category_alias;
    }

    public void setCategory_alias(String category_alias) {
        this.category_alias = category_alias;
    }

    public String getImgpic() {
        return imgpic;
    }

    public void setImgpic(String imgpic) {
        this.imgpic = imgpic;
    }

    public String getHead_link() {
        return head_link;
    }

    public void setHead_link(String head_link) {
        this.head_link = head_link;
    }

    public String getImgpiclist() {
        return imgpiclist;
    }

    public void setImgpiclist(String imgpiclist) {
        this.imgpiclist = imgpiclist;
    }

    public String[] getImgpiclist_link() {
        if (imgpiclist_link == null) {
            imgpiclist_link = new String[0];
        }
        return imgpiclist_link;
    }

    public void setImgpiclist_link(String[] imgpiclist_link) {
        this.imgpiclist_link = imgpiclist_link;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPriority_selection() {
        return priority_selection;
    }

    public void setPriority_selection(int priority_selection) {
        this.priority_selection = priority_selection;
    }

    public int getRecommend_num() {
        return recommend_num;
    }

    public void setRecommend_num(int recommend_num) {
        this.recommend_num = recommend_num;
    }


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNeed_desc() {
        return need_desc;
    }

    public void setNeed_desc(String need_desc) {
        this.need_desc = need_desc;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getTrade() {
        return "";
    }

    @Override
    public String getDate() {
        return create_time;
    }

    @Override
    public String getContent() {
        return need_desc;
    }

    @Override
    public String[] getImageList() {
        return getImgpiclist_link();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLink_id() {
        return link_id;
    }

    public void setLink_id(String link_id) {
        this.link_id = link_id;
    }
}
