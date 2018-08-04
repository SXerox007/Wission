package com.example.sumitthakur.wission.ui.home;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.adapter.YoutubeAdapter;
import com.example.sumitthakur.wission.base.BaseActivity;
import com.example.sumitthakur.wission.model.YoutubeResponse;
import com.example.sumitthakur.wission.network.RestClient;
import com.example.sumitthakur.wission.ui.videoplayer.YoutubeVideoPlayerActivity;
import com.example.sumitthakur.wission.util.Util;


import static com.example.sumitthakur.wission.Constants.ApiConstants.QUESTION;
import static com.example.sumitthakur.wission.Constants.AppConstants.VIDEO_ID;


public class HomeActivity extends BaseActivity implements HomeView, YoutubeAdapter.onItemClicked, View.OnClickListener {

    private HomePresenter homePresenter;
    private RecyclerView rvVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homePresenter = new HomePresenterImpl(this, new HomeInteractorImpl(RestClient.getRetrofitBuilder()));
        init();
    }

    private void init() {
        homePresenter.getVideosYoutube(QUESTION);
        rvVideos = findViewById(R.id.rvVideos);
        findViewById(R.id.ivBack).setVisibility(View.GONE);
        findViewById(R.id.ivMenu).setVisibility(View.VISIBLE);
        ((AppCompatTextView) findViewById(R.id.tvHeading)).setText(getString(R.string.label_home));
        Util.setOnClickListener(this, findViewById(R.id.ivSearch));
    }


    @Override
    public void setRecyclerviewData(YoutubeResponse commonData) {
        YoutubeAdapter youtubeAdapter = new YoutubeAdapter();
        rvVideos.setLayoutManager(new LinearLayoutManager(this));
        rvVideos.setAdapter(youtubeAdapter);
        youtubeAdapter.setData(commonData.getItems(), this);

    }

    @Override
    public void onEmptySearchFieldEnterError() {
        showToast(getString(R.string.err_empty_field_not_allowed));
    }

    @Override
    public void onItemClickedVideo(final String videoId) {
        Bundle bundle = new Bundle();
        bundle.putString(VIDEO_ID, videoId);
        Util.startActivity(this, YoutubeVideoPlayerActivity.class, bundle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSearch:
                homePresenter.getVideosYoutube(((AppCompatEditText) findViewById(R.id.etSearchField)).getText().toString());
                break;
            default:
        }

    }
}
