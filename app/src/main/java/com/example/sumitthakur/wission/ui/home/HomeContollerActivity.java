package com.example.sumitthakur.wission.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.adapter.YoutubeAdapter;
import com.example.sumitthakur.wission.base.BaseActivity;
import com.example.sumitthakur.wission.model.YoutubeResponse;
import com.example.sumitthakur.wission.network.RestClient;
import com.example.sumitthakur.wission.ui.videoplayer.YoutubeVideoPlayerActivity;
import com.example.sumitthakur.wission.util.Util;

import java.io.IOException;

import static com.example.sumitthakur.wission.Constants.ApiConstants.FAVOURITE;
import static com.example.sumitthakur.wission.Constants.ApiConstants.QUESTION;
import static com.example.sumitthakur.wission.Constants.AppConstants.VIDEO_ID;

public class HomeContollerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeView, YoutubeAdapter.onItemClicked, View.OnClickListener {

    private static int SELECT_IMAGE = 1;
    private HomePresenter homePresenter;
    private RecyclerView rvVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_contoller);
        homePresenter = new HomePresenterImpl(this, new HomeInteractorImpl(RestClient.getRetrofitBuilder()));
        init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.label_home));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePresenter.getVideosYoutube(FAVOURITE);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_contoller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // openCamera();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            // openGallery();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, SELECT_IMAGE);
    }


    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);

    }


    private void init() {
        homePresenter.getVideosYoutube(QUESTION);
        rvVideos = findViewById(R.id.rvVideos);
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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
