package com.maimaizaixian.transactiononline.module.common.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/3.
 */
public class PayBalance implements Serializable {

    private int award_amount;
    private int recharge_amount;
    private int agent_invite_award_amount;
    private int agent_recharge_award_amount;
    private int award_sum;
    private int agent_recharge_award_sum;

    public int getAward_sum() {
        return award_sum;
    }

    public void setAward_sum(int award_sum) {
        this.award_sum = award_sum;
    }

    public int getAgent_recharge_award_sum() {
        return agent_recharge_award_sum;
    }

    public void setAgent_recharge_award_sum(int agent_recharge_award_sum) {
        this.agent_recharge_award_sum = agent_recharge_award_sum;
    }

    public int getAgent_invite_award_sum() {
        return agent_invite_award_sum;
    }

    public void setAgent_invite_award_sum(int agent_invite_award_sum) {
        this.agent_invite_award_sum = agent_invite_award_sum;
    }

    private int agent_invite_award_sum;

    public int getAward_amount() {
        return award_amount;
    }

    public void setAward_amount(int award_amount) {
        this.award_amount = award_amount;
    }

    public int getRecharge_amount() {
        return recharge_amount;
    }

    public void setRecharge_amount(int recharge_amount) {
        this.recharge_amount = recharge_amount;
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


    public String getBalance() {
        return (award_amount + recharge_amount + agent_invite_award_amount + agent_recharge_award_amount) + "";
    }

    /**
     * @return
     */
    public String getAgentAward() {
        return (agent_invite_award_amount + agent_recharge_award_amount) + "";
    }

}
