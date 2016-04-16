package com.shyms.corpormobile.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shyms.corpormobile.R;
import com.shyms.corpormobile.base.BaseFragment;
import com.shyms.corpormobile.modle.NInformationData;
import com.shyms.corpormobile.modle.NObjectList;
import com.shyms.corpormobile.net.NetRequest;
import com.shyms.corpormobile.ui.activity.AffairRecordActivity;
import com.shyms.corpormobile.ui.activity.LineUpActivity;
import com.shyms.corpormobile.ui.activity.RemindActivity;
import com.shyms.corpormobile.ui.adapter.MyPageAdapter;

import butterknife.Bind;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Weimin on 4/9/2016.
 */
public class HomePageFragment extends BaseFragment {

    @Bind(R.id.top_bar_left_image)
    protected ImageView mTopBarBack;
    @Bind(R.id.vp_home_pic_auto)

    protected AutoScrollViewPager mViewPager;
    private MyPageAdapter adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_page_home;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTopBarBack.setVisibility(View.INVISIBLE);
        adapter = new MyPageAdapter(mActivity);
        getLatestData();
        mViewPager.setAdapter(adapter);
//        mViewPager.startAutoScroll();
    }

    @OnClick(R.id.rl_home_party1)
    protected void onClickRecord() {
        startActivity(new Intent(mActivity, AffairRecordActivity.class));
    }

    @OnClick(R.id.rl_home_party3)
    protected void onClickLineUp() {
        startActivity(new Intent(mActivity, LineUpActivity.class));
    }

    @OnClick(R.id.rl_home_party4)
    protected void onClickRemind() {
        startActivity(new Intent(mActivity, RemindActivity.class));
    }


    public void getLatestData() {
        NetRequest.APIInstance.getInformation()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<NObjectList<NInformationData>>bindToLifecycle())
                .subscribe(results -> {
                    adapter.setDataList(results.data);
                });

    }


}