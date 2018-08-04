package com.example.sumitthakur.wission.ui.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.ui.home.YouTubeFailureRecoveryActivity;
import com.example.sumitthakur.wission.util.Util;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.example.sumitthakur.wission.Constants.AppConstants.VIDEO_ID;

public class YoutubeVideoPlayerActivity extends YouTubeFailureRecoveryActivity implements View.OnClickListener {

    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video_player);
        init();
    }

    private void init() {
        Intent intent = this.getIntent();
        mBundle = intent.getExtras();
        findViewById(R.id.ivBack).setVisibility(View.VISIBLE);
        findViewById(R.id.ivMenu).setVisibility(View.GONE);
        YouTubePlayerView youTubeView = findViewById(R.id.youtube_view);
        youTubeView.initialize(getString(R.string.api_key_value), this);
        Util.setOnClickListener(this, findViewById(R.id.ivBack));
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        // if (!wasRestored) {
        // youTubePlayer.cueVideo("gm0iQQyKHlQ");
        youTubePlayer.loadVideo(mBundle.getString(VIDEO_ID));
        // youTubePlayer.play();
        //}
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return findViewById(R.id.youtube_view);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                super.onBackPressed();
                break;
            default:
        }

    }
}
