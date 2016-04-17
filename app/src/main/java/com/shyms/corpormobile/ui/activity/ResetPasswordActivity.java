package com.shyms.corpormobile.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shyms.corpormobile.R;
import com.shyms.corpormobile.base.BaseActivity;
import com.shyms.corpormobile.net.NetRequest;
import com.shyms.corpormobile.util.SPUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;

public class ResetPasswordActivity extends BaseActivity {

    public final boolean VERTIFY_CODE_SUCCESS = false;
    @Bind(R.id.top_bar_left_image)
    protected ImageView mTopBarLeftIcon;
    @Bind(R.id.top_bar_title)
    protected TextView mTopBarTitle;
    @Bind(R.id.top_bar_right_text)
    protected TextView mTopBarRightText;
    @Bind(R.id.et_username)
    protected EditText mEtUsername;
    @Bind(R.id.et_previpus_password)
    protected EditText mEtPrePassword;
    @Bind(R.id.et_new_password)
    protected EditText mEtNewPassword;
    @Bind(R.id.et_vertify_code)
    protected EditText mEtVertifyCode;

    @Bind(R.id.re_send_code_auth_code_page)
    protected Button mSendCode;
    @Bind(R.id.btn_accomplish)
    protected Button mAccomplish;

    @Bind(R.id.tips_email_tips_page)
    protected TextView mEmailTips;

    private CountDownTimer mCountDownTimer;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        mTopBarTitle.setText("修改密码");
        mTopBarRightText.setText("完成");
        mobile = (String) SPUtil.get("mobile", "13611909778");
        mEtUsername.setText(mobile);

        sendVertifyCode();
    }


    @OnClick(R.id.btn_accomplish)
    protected void onClickAccomplish() {
        //TODO 又不用验证码 提示验证码 干嘛？

        String prePassword = mEtPrePassword.getText().toString().trim();
        String newPassword = mEtNewPassword.getText().toString().trim();

        NetRequest.APIInstance.changePassword(mobile, prePassword, newPassword)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {


                }, throwable -> {
                });

    }

    @OnClick(R.id.top_bar_left_image)
    protected void onBackPress() {
        finish();
    }

    private void sendVertifyCode() {
        NetRequest.APIInstance.UserVerifyCode(mobile)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {


                }, throwable -> {
                });
        generateCountDownTimer();
    }


    private void generateCountDownTimer() {
//        reSendCodeButton.setBackgroundResource(R.drawable.bg_count_down_btn);
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mSendCode.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                mSendCode.setText("重新发送验证码");
//                reSendCodeButton.setBackgroundResource(R.drawable.bg_coupon_detail_btn);
            }
        };
        mCountDownTimer.start();
    }
}
