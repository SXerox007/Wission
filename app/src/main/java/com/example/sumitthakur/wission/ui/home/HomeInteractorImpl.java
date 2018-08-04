package com.example.sumitthakur.wission.ui.home;

import com.example.sumitthakur.wission.Constants.ApiConstants;
import com.example.sumitthakur.wission.db.CommonData;
import com.example.sumitthakur.wission.model.YoutubeResponse;
import com.example.sumitthakur.wission.network.ApiError;
import com.example.sumitthakur.wission.network.ApiInterface;
import com.example.sumitthakur.wission.network.CommonParams;
import com.example.sumitthakur.wission.network.ResponseResolver;

import retrofit2.Retrofit;

/**
 * Created by sumitthakur on 29/07/18.
 */

public class HomeInteractorImpl implements HomeInteractor, ApiConstants {
    private ApiInterface mApiInterface;
    private Retrofit mRetrofit;

    public HomeInteractorImpl(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
        this.mApiInterface = mRetrofit.create(ApiInterface.class);
    }

    @Override
    public void getYoutubeVideos(final HomeView view, final String searchText) {
        CommonParams commonParams = new CommonParams.Builder()
                .add("part", PART)
                .add("q", searchText)
                //.add("forContentOwner", true)
                // .add("forMine", false)
                .add("type", VIDEO)
                // .add("relatedToVideoId", "")
                .add("key", DEV_KEY)
                //.add("forDeveloper", true)
                .add("maxResults", 20)
                .build();

        mApiInterface.getGeoAddress(commonParams.getMap()).enqueue(new ResponseResolver<YoutubeResponse>(mRetrofit) {
            @Override
            public void onSuccess(YoutubeResponse commonData) {
                view.setRecyclerviewData(commonData);
            }

            @Override
            public void onError(ApiError error) {
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
