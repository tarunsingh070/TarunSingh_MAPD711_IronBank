package com.example.tarunsingh.tarunsingh_mapd711_ironbank.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tarunsingh on 2018-01-06.
 */

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://ironbank-test.herokuapp.com/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
