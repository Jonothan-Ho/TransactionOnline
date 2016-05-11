package com.maimaizaixian.transactiononline.module.me.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ConsumptionRecords implements Serializable {

    private String id;
    private String uid;
    private String amount;
    private String trade_title;
    private int trade_type;
    private String created_time;
    private String chance_id;
    private String kiting_balance;

    public String getKiting_balance() {
        return kiting_balance;
    }

    public void setKiting_balance(String kiting_balance) {
        this.kiting_balance = kiting_balance;
    }

    public String getChance_id() {
        return chance_id;
    }

    public void setChance_id(String chance_id) {
        this.chance_id = chance_id;
    }

    public int getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(int trade_type) {
        this.trade_type = trade_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTrade_title() {
        return trade_title;
    }

    public void setTrade_title(String trade_title) {
        this.trade_title = trade_title;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
