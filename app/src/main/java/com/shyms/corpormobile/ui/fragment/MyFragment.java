package com.shyms.corpormobile.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shyms.corpormobile.R;
import com.shyms.corpormobile.base.BaseFragment;
import com.shyms.corpormobile.helper.GotoHelper;
import com.shyms.corpormobile.ui.activity.AppointmentActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Weimin on 4/9/2016.
 */
public class MyFragment extends BaseFragment {

    @Bind(R.id.top_bar_title)
    protected TextView mTitle;
    @Bind(R.id.frseco_profile)
    protected SimpleDraweeView mProfile;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_my;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle.setText("我的");

    }


    @OnClick(R.id.ll_my_party2)
    protected void onClickAffairQuery() {
        GotoHelper.gotoActivity(mActivity, AppointmentActivity.class);
    }

    @OnClick(R.id.ll_my_party6)
    protected void onClickDownload() {
        Toast.makeText(mActivity, "我的下载对话框", Toast.LENGTH_SHORT).show();
    }

}
