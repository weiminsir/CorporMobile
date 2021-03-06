package com.shyms.corpormobile.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shyms.corpormobile.R;
import com.shyms.corpormobile.base.BaseActivity;
import com.shyms.corpormobile.base.BaseApplication;
import com.shyms.corpormobile.constants.GlobalConstant;
import com.shyms.corpormobile.helper.GotoHelper;
import com.shyms.corpormobile.modle.NObject;
import com.shyms.corpormobile.modle.NUser;
import com.shyms.corpormobile.net.NetRequest;
import com.shyms.corpormobile.util.SPUtil;
import com.shyms.corpormobile.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SmsVertifyActivity extends BaseActivity {

    public static final String VERIFY_SOURCE = "verify_source";
    @Bind(R.id.top_bar_left_image)
    protected ImageView mTopBarLeftIcon;
    @Bind(R.id.top_bar_title)
    protected TextView mTopBarTitle;
    @Bind(R.id.top_bar_right_text)
    protected TextView mTopBarRightText;
    @Bind(R.id.phone_number_auth_code_page)
    protected TextView mPhoneNumber;
    @Bind(R.id.code_auth_code_page)
    protected EditText mVerifyCode;
    @Bind(R.id.re_send_code_auth_code_page)
    protected Button mSendCode;
    private String mobile;
    private CountDownTimer mCountDownTimer;
    private String password;
    private String verifyCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_vertify);
        ButterKnife.bind(this);
        mTopBarTitle.setText("验证手机号");
        mTopBarRightText.setText("继续");
        mTopBarLeftIcon.setVisibility(View.VISIBLE);

        mobile = (String) SPUtil.get("mobile", "13611909778");
        password = (String) SPUtil.get("password", "123456");


//        mobile = getIntent().getStringExtra(VERIFY_SOURCE);
        mPhoneNumber.setText(mobile);
        generateCountDownTimer();

    }

    @OnClick(R.id.top_bar_right)
    protected void onClickContinue() {
        verifyCode = mVerifyCode.getText().toString().trim();

        if (TextUtils.isEmpty(verifyCode)) {
            ToastUtil.shortShowText("验证码不能为空");
            return;
        }

        NetRequest.APIInstance.checkVerifyCode(mobile, verifyCode)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    if (results.code.equals("0")) {
                        Log.d(GlobalConstant.TAG, "进入注册");
                        SmsVertifyActivity.this.Register();
                    }


                }, throwable -> {
//                    ToastUtil.shortShowText(throwable.toString());
                });
    }

    private void Register() {

        NetRequest.APIInstance.register(mobile, password, mobile, mobile, verifyCode, 1)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NObject<NUser>>() {
                    @Override
                    public void call(NObject<NUser> results) {

                        ToastUtil.shortShowText("注册进去了 ");
                        BaseApplication.mUser.init(results.data);
                        GotoHelper.gotoActivity(SmsVertifyActivity.this, MainActivity.class);
                    }
                }, throwable -> {
                });


    }

    @OnClick(R.id.re_send_code_auth_code_page)
    protected void onClickSendVertifyCode() {
        NetRequest.APIInstance.sendVerifyCode(mobile)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NObject<Boolean>>() {
                    @Override
                    public void call(NObject<Boolean> results) {

                    }
                }, throwable -> {
                });
        generateCountDownTimer();
    }

    @OnClick(R.id.top_bar_left_image)
    protected void onBackPress() {
        finish();
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
