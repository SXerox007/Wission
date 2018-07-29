package com.example.sumitthakur.wission.ui.home;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.network.RestClient;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class HomeActivity extends YouTubeFailureRecoveryActivity implements HomeView {

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homePresenter = new HomePresenterImpl(this, new HomeInteractorImpl(RestClient.getRetrofitBuilder()));
        init();
    }

    private void init() {
        homePresenter.getVideosYoutube();
        findViewById(R.id.ivBack).setVisibility(View.GONE);
        findViewById(R.id.ivMenu).setVisibility(View.VISIBLE);
        ((AppCompatTextView) findViewById(R.id.tvHeading)).setText(getString(R.string.label_home));
        YouTubePlayerView youTubeView = findViewById(R.id.youtube_view);
        youTubeView.initialize(getString(R.string.api_key_value), this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        // if (!wasRestored) {
        // youTubePlayer.cueVideo("gm0iQQyKHlQ");
        youTubePlayer.loadVideo("wKJ9KzGQq0w");
        // youTubePlayer.play();
        //}
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


//    @Override
//    public void onResume(){
//        super.onResume();
//        try {
//            if (video == null) {
//                return;
//            }
//
//            YouTubePlayer.loadVideo(String videoId, int timeMillis);
//
//        } catch (Throwable ignored) {}
//    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return findViewById(R.id.youtube_view);
    }
}
