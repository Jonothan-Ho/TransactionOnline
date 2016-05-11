package com.maimaizaixian.transactiononline.module.me;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseMenuFragment;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IPayController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IPayService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.PayServiceImpl;
import com.maimaizaixian.transactiononline.module.me.ui.AgentSetActivity;
import com.maimaizaixian.transactiononline.module.me.ui.BalanceAgentActivity;
import com.maimaizaixian.transactiononline.module.me.ui.BalanceUnAgentActivity;
import com.maimaizaixian.transactiononline.module.me.ui.CollectActivity;
import com.maimaizaixian.transactiononline.module.me.ui.ConsumptionRecordsActivity;
import com.maimaizaixian.transactiononline.module.me.ui.HistoryPublishActivity;
import com.maimaizaixian.transactiononline.module.me.ui.InviteCodeAgentActivity;
import com.maimaizaixian.transactiononline.module.me.ui.InviteCodeUnAgentActivity;
import com.maimaizaixian.transactiononline.module.me.ui.InviteContactActivity;
import com.maimaizaixian.transactiononline.module.me.ui.MoreActivity;
import com.maimaizaixian.transactiononline.module.me.ui.OrganizingDataActivity;
import com.maimaizaixian.transactiononline.module.me.ui.SystemNoticeActivity;
import com.maimaizaixian.transactiononline.module.me.ui.UnAgentSetActivity;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

/**
 */
public class MeIndexFragment extends BaseMenuFragment implements View.OnClickListener, IPayController {

    public static final int REQUEST_INVITE_CODE = 10;


    @ViewInject(R.id.iv_portrait)
    private CircleImageView mImgPortrait;

    @ViewInject(R.id.tv_status)
    private TextView mTextStatus;
    @ViewInject(R.id.tv_level)
    private TextView mTextLevel;
    @ViewInject(R.id.tv_name)
    private TextView mTextName;
    @ViewInject(R.id.tv_balance_money)
    private TextView mTextBalance;


    @ViewInject(R.id.layout_person)
    private RelativeLayout mLayoutPerson;
    @ViewInject(R.id.layout_collect)
    private RelativeLayout mLayoutCollect;
    @ViewInject(R.id.layout_balance)
    private RelativeLayout mLayoutBalance;
    @ViewInject(R.id.layout_expense_calendar)
    private RelativeLayout mLayoutExpense;
    @ViewInject(R.id.layout_history_publish)
    private RelativeLayout mLayoutHistory;
    @ViewInject(R.id.layout_system_notice)
    private RelativeLayout mLayoutNotice;
    @ViewInject(R.id.layout_invite_friends)
    private RelativeLayout mLayoutInviteFriends;
    @ViewInject(R.id.layout_invite_code)
    private RelativeLayout mLayoutInviteCode;
    @ViewInject(R.id.layout_agent_set)
    private RelativeLayout mLayoutAgent;
    @ViewInject(R.id.layout_more)
    private RelativeLayout mLayoutMore;

    private IPayService mPayService;
    private PayBalance mPayBalance;

    @Override
    public void initSubView() {
        mPayService = new PayServiceImpl(this);
        mPayService.getAccountBalance();

        User user = PreferencesUtil.getInstance(getActivity()).get(User.class);
        BitmapUtil.getInstance(getActivity()).displayImage(mImgPortrait, user.getHead());
        mTextName.setText(user.getReal_name());
        String status = null;
        switch (user.getUser_auth()) {
            case 0:
                status = "未认证";
                break;
            case 1:
                status = "审核中";
                break;
            case 2:
                status = "已认证";
                break;
            case -1:
                status = "未通过";
                break;
        }
        mTextStatus.setText(status);

        if (user.getVip() == 0) {
            mTextLevel.setVisibility(View.GONE);
        } else {
            mTextLevel.setText("VIP" + user.getVip());
        }

    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_me_index;
    }

    @Override
    protected void initListener() {
        mLayoutPerson.setOnClickListener(this);
        mLayoutCollect.setOnClickListener(this);
        mLayoutBalance.setOnClickListener(this);
        mLayoutExpense.setOnClickListener(this);
        mLayoutNotice.setOnClickListener(this);
        mLayoutInviteFriends.setOnClickListener(this);
        mLayoutInviteCode.setOnClickListener(this);
        mLayoutAgent.setOnClickListener(this);
        mLayoutMore.setOnClickListener(this);
        mLayoutHistory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.layout_person:
                intent.setClass(getActivity(), OrganizingDataActivity.class);
                break;
            case R.id.layout_collect:
                intent.setClass(getActivity(), CollectActivity.class);
                break;
            case R.id.layout_balance:
                if (mPayBalance == null) {
                    ViewUtil.showSnackbar(getRootView(), "余额未获取，正在获取");
                    mPayService.getAccountBalance();
                    return;
                }


                User user = PreferencesUtil.getInstance(getActivity()).get(User.class);
                if (user.getIs_auth() == 2) {
                    intent.setClass(getActivity(), BalanceAgentActivity.class);
                } else {
                    intent.setClass(getActivity(), BalanceUnAgentActivity.class);
                }


                if (user.getVip() != 0) {
                    intent.putExtra("vip", "VIP" + user.getVip());
                }

                //intent.setClass(getActivity(), BalanceUnAgentActivity.class);

                intent.putExtra("object", mPayBalance);
                break;
            case R.id.layout_expense_calendar:
                intent.setClass(getActivity(), ConsumptionRecordsActivity.class);
                break;
            case R.id.layout_system_notice:
                intent.setClass(getActivity(), SystemNoticeActivity.class);
                break;
            case R.id.layout_invite_friends:
                intent.setClass(getActivity(), InviteContactActivity.class);
                break;
            case R.id.layout_invite_code:
                if (mPayBalance == null) {
                    ViewUtil.showSnackbar(getRootView(), "余额未获取，正在获取");
                    mPayService.getAccountBalance();
                    return;
                }

                int auth = PreferencesUtil.getInstance(getActivity()).get(User.class).getIs_auth();
                if (auth == 2) {
                    intent.setClass(getActivity(), InviteCodeAgentActivity.class);
                } else {
                    intent.setClass(getActivity(), InviteCodeUnAgentActivity.class);
                }

                intent.putExtra("object", mPayBalance);
                startActivityForResult(intent, REQUEST_INVITE_CODE);

                return;
            case R.id.layout_agent_set:
                if (mPayBalance == null) {
                    ViewUtil.showSnackbar(getRootView(), "余额未获取，正在获取");
                    mPayService.getAccountBalance();
                    return;
                }

                int authStatus = PreferencesUtil.getInstance(getActivity()).get(User.class).getIs_auth();
                if (authStatus == 2) {
                    intent.setClass(getActivity(), AgentSetActivity.class);
                } else {
                    intent.setClass(getActivity(), UnAgentSetActivity.class);
                }

                intent.putExtra("object", mPayBalance);

                break;
            case R.id.layout_more:
                intent.setClass(getActivity(), MoreActivity.class);
                break;
            case R.id.layout_history_publish:
                intent.setClass(getActivity(), HistoryPublishActivity.class);
                break;

        }

        startActivity(intent);
    }

    @Override
    public void onAccountBalance(PayBalance payBalance) {
        mPayBalance = payBalance;
        mTextBalance.setText(payBalance.getBalance() + "元");
    }

    @Override
    public void onComplete() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_INVITE_CODE:
                    mPayService.getAccountBalance();
                    break;
            }
        }
    }
}
