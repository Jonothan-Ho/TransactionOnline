package com.maimaizaixian.transactiononline.module.account.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.CardModel;
import com.maimaizaixian.transactiononline.module.home.domain.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/10/23.
 */
public class User extends CardModel {

    private String id;
    private String birthday;
    private String register_money;
    private String enterprise_people;
    private String register_time;
    private String trade_cid;
    private String qq;
    private String short_company_name;
    private String nickname;
    private String real_name;
    private String head;
    private String mail;
    private String address;
    private String username;
    private String last_login_ip;
    private String business_license;
    private String company_name;
    private String fromecode;
    private String incode;
    private String IDCard;
    private String IDCard_license;
    private String app_token;
    private String province;
    private String city;
    private String county;
    private String region;
    private long create_time;
    private long last_login_time;
    private int award_amount;
    private String reg_ip;
    private int recharge_amount;
    private int vip;
    private int status;
    private int is_admin;
    private int is_auth;
    private int weight;
    private int agent_invite_award_amount;
    private int agent_recharge_award_amount;
    private int reg_time;
    private int login;
    private String mobile;
    private double longitude;
    private double latitude;
    private String advantage;
    private String service;
    private List<Category> categories;
    private int has_alipay_password;
    private int info_price;
    private int user_auth;
    private String distance;
    private String ring_password;

    public String getRing_password() {
        return ring_password;
    }

    public void setRing_password(String ring_password) {
        this.ring_password = ring_password;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getUser_auth() {
        return user_auth;
    }

    public void setUser_auth(int user_auth) {
        this.user_auth = user_auth;
    }

    public int getInfo_price() {
        return info_price;
    }

    public void setInfo_price(int info_price) {
        this.info_price = info_price;
    }

    public int getHas_alipay_password() {
        return has_alipay_password;
    }

    public void setHas_alipay_password(int has_alipay_password) {
        this.has_alipay_password = has_alipay_password;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getTrade_cid() {
        return trade_cid;
    }

    public void setTrade_cid(String trade_cid) {
        this.trade_cid = trade_cid;
    }


    public String getShort_company_name() {
        return short_company_name;
    }

    public void setShort_company_name(String short_company_name) {
        this.short_company_name = short_company_name;
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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getTrade() {
        return trade_cid;
    }

    @Override
    public String getDate() {
        return create_time + "";
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public String[] getImageList() {
        return new String[0];
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFromecode() {
        return fromecode;
    }

    public void setFromecode(String fromecode) {
        this.fromecode = fromecode;
    }

    public String getIncode() {
        return incode;
    }

    public void setIncode(String incode) {
        this.incode = incode;
    }


    public String getRegister_money() {
        return register_money;
    }

    public void setRegister_money(String register_money) {
        this.register_money = register_money;
    }

    public String getEnterprise_people() {
        return enterprise_people;
    }

    public void setEnterprise_people(String enterprise_people) {
        this.enterprise_people = enterprise_people;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getIDCard() {
        return IDCard;
    }

    @JSONField(name = "IDCard")
    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    @JSONField(name = "IDCard_license")
    public String getIDCard_license() {
        return IDCard_license;
    }

    public void setIDCard_license(String IDCard_license) {
        this.IDCard_license = IDCard_license;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(long last_login_time) {
        this.last_login_time = last_login_time;
    }

    public long getAward_amount() {
        return award_amount;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public long getRecharge_amount() {
        return recharge_amount;
    }


    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public int getIs_auth() {
        return is_auth;
    }

    public void setIs_auth(int is_auth) {
        this.is_auth = is_auth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAgent_invite_award_amount() {
        return agent_invite_award_amount;
    }

    public void setAgent_invite_award_amount(int agent_invite_award_amount) {
        this.agent_invite_award_amount = agent_invite_award_amount;
    }

    public int getAgent_recharge_award_amount() {
        return agent_recharge_award_amount;
    }

    public void setAgent_recharge_award_amount(int agent_recharge_award_amount) {
        this.agent_recharge_award_amount = agent_recharge_award_amount;
    }

    public int getReg_time() {
        return reg_time;
    }

    public void setReg_time(int reg_time) {
        this.reg_time = reg_time;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "UserImpl{" +
                "birthday=" + birthday +
                ", register_money=" + register_money +
                ", enterprise_people=" + enterprise_people +
                ", city=" + city +
                ", register_time=" + register_time +
                ", trade_cid='" + trade_cid + '\'' +
                ", qq=" + qq +
                ", short_company_name='" + short_company_name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", real_name='" + real_name + '\'' +
                ", head='" + head + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", last_login_ip='" + last_login_ip + '\'' +
                ", business_license='" + business_license + '\'' +
                ", company_name='" + company_name + '\'' +
                ", fromecode='" + fromecode + '\'' +
                ", incode='" + incode + '\'' +
                ", IDCard='" + IDCard + '\'' +
                ", IDCard_license='" + IDCard_license + '\'' +
                ", app_token='" + app_token + '\'' +
                ", create_time=" + create_time +
                ", last_login_time=" + last_login_time +
                ", award_amount=" + award_amount +
                ", reg_ip=" + reg_ip +
                ", recharge_amount=" + recharge_amount +
                ", vip=" + vip +
                ", status=" + status +
                ", is_admin=" + is_admin +
                ", is_auth=" + is_auth +
                ", weight=" + weight +
                ", agent_invite_award_amount=" + agent_invite_award_amount +
                ", agent_recharge_award_amount=" + agent_recharge_award_amount +
                ", reg_time=" + reg_time +
                ", login=" + login +
                ", mobile=" + mobile +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @Override
    public String getTitle() {
        return username;
    }

    @Override
    public String getImage() {
        return head;
    }

    public String getBalance() {
        return (award_amount + recharge_amount + agent_invite_award_amount + agent_recharge_award_amount) + "";
    }

    public void setAward_amount(int award_amount) {
        this.award_amount = award_amount;
    }

    public void setRecharge_amount(int recharge_amount) {
        this.recharge_amount = recharge_amount;
    }

    public static class Category implements Serializable {
        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
