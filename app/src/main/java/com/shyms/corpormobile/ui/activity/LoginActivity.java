package com.shyms.corpormobile.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.shyms.corpormobile.R;
import com.shyms.corpormobile.base.BaseActivity;
import com.shyms.corpormobile.helper.GotoHelper;
import com.shyms.corpormobile.modle.NUser;
import com.shyms.corpormobile.net.NetRequest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.tv_forget_password)
    protected TextView mJumpMain;
    @Bind(R.id.username_login_page)
    protected EditText mUsername;
    @Bind(R.id.password_login_page)
    protected EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    NUser mNUser = new NUser();

    @OnClick(R.id.login_button_login_page)
    protected void OnClickLogin() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        NetRequest.APIInstance.login(username, password)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    mNUser = results.data;
//                    BaseApplication.mUser.init(results.data);
                    GotoHelper.gotoActivity(LoginActivity.this, MainActivity.class);

                }, throwable -> {
                });
    }


    @OnClick(R.id.tv_forget_password)
    protected void OnClickMain() {
//        GotoHelper.gotoActivity(LoginActivity.this, MainActivity.class);
//        finish();
    }


}
