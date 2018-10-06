package com.ansar.wallsy.data.network;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DataProvider {

    @GET("search")
    Observable<String> getData(@QueryMap Map<String, String> options);
}
