package com.example.sumitthakur.wission.network;

import com.example.sumitthakur.wission.db.CommonData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * The API interface for your application
 */
public interface ApiInterface {
    String GET_YOUTUBE_VIDEOS = "/youtube/v3/search`";

    /**
     * Gets geo address.
     *
     * @param map map
     * @return Response in GeoCodedApiResponse
     */
    @GET(GET_YOUTUBE_VIDEOS)
    Call<CommonData> getGeoAddress(@QueryMap Map<String, String> map);

}
