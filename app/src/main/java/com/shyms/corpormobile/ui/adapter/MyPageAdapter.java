package com.shyms.corpormobile.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shyms.corpormobile.R;
import com.shyms.corpormobile.helper.GotoHelper;
import com.shyms.corpormobile.modle.NInformationData;
import com.shyms.corpormobile.ui.activity.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weimin on 4/11/2016.
 */
public class MyPageAdapter extends PagerAdapter {
    protected LayoutInflater mLayoutInflater;
    private List<NInformationData> mDataList;
    private Context mContext;

    public void setDataList(List<NInformationData> dataList) {

        if (dataList != null) {
            mDataList = new ArrayList<>();
            mDataList = dataList;
        }
        notifyDataSetChanged();
    }

    public MyPageAdapter(Context context) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mContext == null) {
            return null;
        }
        View view = mLayoutInflater.inflate(R.layout.item_page_auto, null);
        SimpleDraweeView mBrief = (SimpleDraweeView) view.findViewById(R.id.fresco_brief);
        //TODO 动态显示图片
        mBrief.setImageURI(Uri.parse(mDataList.get(position).brief));
        mBrief.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
//            bundle.putInt(NewsDetailActivity.NEWS_INFORMATION_ID, position);
            bundle.putSerializable(NewsDetailActivity.NEWS_INFORMATION_ID, mDataList.get(position));
            GotoHelper.gotoActivity(mContext, NewsDetailActivity.class, bundle);
        });
        // 1. 将要显示的View加到ViewPager里
        container.addView(view);
        // 2. 将对象返回给框架, 缓存一些数据// 必须重写此方法, 否则报错
//            container.addView(view);
        return view;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
        //mDataList == null ? 0 : mDataList.size();
    }

    //复用对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}