package com.shyms.corpormobile.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shyms.corpormobile.R;
import com.shyms.corpormobile.base.BaseActivity;
import com.shyms.corpormobile.modle.NInformationData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity {

    public final static String NEWS_INFORMATION_ID = "NEWS_ID";

    @Bind(R.id.top_bar_back)
    protected FrameLayout mback;
    @Bind(R.id.top_bar_title)
    protected TextView mTitle;
    @Bind(R.id.iv_news_img)
    protected SimpleDraweeView mNewsImg;
    @Bind(R.id.tv_home_news_title)
    protected TextView mHomeTitle;
    @Bind(R.id.tv_news_title)
    protected TextView mNewsTitle;
    @Bind(R.id.tv_publish_time)
    protected TextView mPublishTime;
    @Bind(R.id.iv_comment)
    protected ImageView mComment;
    @Bind(R.id.iv_favorite)
    protected ImageView mFavorite;
    @Bind(R.id.tv_comment_num)
    protected TextView mCommentNum;
    @Bind(R.id.tv_favorite_num)
    protected TextView mFavoriteNum;
    @Bind(R.id.tv_publish_unit)
    protected TextView mPublishUnit;
    @Bind(R.id.tv_news_detail)
    protected TextView mNewsDetail;
    @Bind(R.id.tv_news_content)
    protected TextView mNewsContent;
    private NInformationData result = new NInformationData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        findViewById(R.id.top_bar_left_image).setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        result = (NInformationData) bundle.getSerializable(NEWS_INFORMATION_ID);
        mNewsTitle.setText(result.title);
        mNewsImg.setImageURI(Uri.parse(result.brief));
        mPublishTime.setText(result.publishTime);
        mCommentNum.setText(result.viewNum);
        mFavoriteNum.setText(result.favoriteNum);
        mNewsContent.setText(result.content);
    }

    @OnClick(R.id.top_bar_back)
    protected void OnClickBack() {
        finish();
    }
}
