package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IConsumptionRecordsController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IConsumptionRecordsService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.ConsumptionRecordsServiceImpl;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;
import com.maimaizaixian.transactiononline.utils.ShareUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class InviteCodeUnAgentActivity extends ActionBarOneActivity implements View.OnClickListener, IConsumptionRecordsController {

    @ViewInject(R.id.tv_code)
    private TextView mTextInviteCode;
    @ViewInject(R.id.tv_rewards_money)
    private TextView mTextRewards;

    @ViewInject(R.id.et_code)
    private EditText mEditCode;

    @ViewInject(R.id.tv_weixin)
    private TextView mTextWeixin;
    @ViewInject(R.id.tv_friend)
    private TextView mTextFriend;
    @ViewInject(R.id.tv_qzone)
    private TextView mTextQzone;
    @ViewInject(R.id.tv_qq)
    private TextView mTextQQ;
    @ViewInject(R.id.tv_weibo)
    private TextView mTextWeibo;


    private IConsumptionRecordsService mService;

    @Override
    protected void initSubView() {
        setTitleBarHeadline("邀请码");
        setTitleBarHint("我");

        PayBalance payBalance = (PayBalance) getIntent().getSerializableExtra("object");
        mTextRewards.setText(payBalance.getAward_amount() + "元");
        User user = PreferencesUtil.getInstance(this).get(User.class);
        mTextInviteCode.setText(user.getIncode());

        mService = new ConsumptionRecordsServiceImpl(this);

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_invite_code_un_agent;
    }

    @Override
    protected void initListener() {
        mTextFriend.setOnClickListener(this);
        mTextQQ.setOnClickListener(this);
        mTextQzone.setOnClickListener(this);
        mTextWeibo.setOnClickListener(this);
        mTextWeixin.setOnClickListener(this);

        mEditCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String code = mEditCode.getText().toString().trim();
                    mService.sendInvateCode(code);
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_friend:
                shareWeixinChatMoments();
                break;
            case R.id.tv_qq:
                shareQQ();
                break;
            case R.id.tv_qzone:
                shareQzone();
                break;
            case R.id.tv_weibo:
                shareWeibo();
                break;
            case R.id.tv_weixin:
                shareWeixinChat();
                break;
        }
    }

    @Override
    public void onComplete(List<ConsumptionRecords> recordses, int page) {

    }

    @Override
    public void onComplete(Consumption_Action action) {

    }

    @Override
    public void onComplete(Consumption_Action action, String data) {
        mTextRewards.setText(data + "元");
        setResult(RESULT_OK, null);
    }

    private void shareQQ() {
        QQ.ShareParams params = new QQ.ShareParams();
        params.setTitle("买卖在线分享");
        params.setTitleUrl("http://www.baidu.com");
        params.setText("我正在使用买卖在线，我的邀请码是" + mTextInviteCode.getText().toString().trim());
        params.setImageUrl("http://a.hiphotos.baidu.com/zhidao/pic/item/d4628535e5dde71120d7a1d7a5efce1b9d16618e.jpg");
        ShareUtil.getInstance(this).shareQQ(params, new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ViewUtil.showSnackbar(getRootView(), "分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ViewUtil.showSnackbar(getRootView(), throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ViewUtil.showSnackbar(getRootView(), "已取消分享");
            }
        });

    }

    private void shareWeixinChat() {
        Wechat.ShareParams params = new Wechat.ShareParams();
        params.setShareType(Platform.SHARE_WEBPAGE);
        params.setTitle("买卖在线分享");
        params.setUrl("http://www.baidu.com");
        params.setText("我正在使用买卖在线，我的邀请码是" + mTextInviteCode.getText().toString().trim());
        params.setImageUrl("http://a.hiphotos.baidu.com/zhidao/pic/item/d0c8a786c9177f3ea71a306273cf3bc79f3d56b7.jpg");
        ShareUtil.getInstance(this).shareWXingChat(params, new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ViewUtil.showSnackbar(getRootView(), "分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ViewUtil.showSnackbar(getRootView(), throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ViewUtil.showSnackbar(getRootView(), "已取消分享");
            }
        });

    }

    private void shareWeixinChatMoments() {
        WechatMoments.ShareParams params = new WechatMoments.ShareParams();
        params.setShareType(Platform.SHARE_WEBPAGE);
        params.setTitle("买卖在线分享");
        params.setUrl("http://www.baidu.com");
        params.setText("我正在使用买卖在线，我的邀请码是" + mTextInviteCode.getText().toString().trim());
        params.setImageUrl("http://a.hiphotos.baidu.com/zhidao/pic/item/d4628535e5dde71120d7a1d7a5efce1b9d16618e.jpg");
        ShareUtil.getInstance(this).shareWXingChat(params, new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ViewUtil.showSnackbar(getRootView(), "分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ViewUtil.showSnackbar(getRootView(), throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ViewUtil.showSnackbar(getRootView(), "已取消分享");
            }
        });

    }

    private void shareQzone() {
        QZone.ShareParams params = new QZone.ShareParams();
        params.setTitle("买卖在线分享");
        params.setTitleUrl("http://www.baidu.com");
        params.setText("我正在使用买卖在线，我的邀请码是" + mTextInviteCode.getText().toString().trim());
        params.setImageUrl("http://a.hiphotos.baidu.com/zhidao/pic/item/d4628535e5dde71120d7a1d7a5efce1b9d16618e.jpg");
        ShareUtil.getInstance(this).shareQZone(params, new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ViewUtil.showSnackbar(getRootView(), "分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ViewUtil.showSnackbar(getRootView(), throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ViewUtil.showSnackbar(getRootView(), "已取消分享");
            }
        });
    }

    private void shareWeibo() {
        SinaWeibo.ShareParams params = new SinaWeibo.ShareParams();
        params.setTitle("买卖在线分享");
        params.setTitleUrl("http://www.baidu.com");
        params.setText("我正在使用买卖在线，我的邀请码是" + mTextInviteCode.getText().toString().trim());
        params.setImageUrl("http://a.hiphotos.baidu.com/zhidao/pic/item/d4628535e5dde71120d7a1d7a5efce1b9d16618e.jpg");
        ShareUtil.getInstance(this).shareWeibo(params, new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ViewUtil.showSnackbar(getRootView(), "分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ViewUtil.showSnackbar(getRootView(), throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ViewUtil.showSnackbar(getRootView(), "已取消分享");
            }
        });
    }


}
