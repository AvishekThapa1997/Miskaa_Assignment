package com.practice.api.utility;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtility {
    private static Retrofit retrofit;
    private static final Object mLock = new Object();
    public static Retrofit getRetrofit() {
        synchronized (mLock){
            if(retrofit == null){
                retrofit = new Retrofit.Builder().
                        baseUrl("https://restcountries.eu/rest/v2/").
                        addConverterFactory(GsonConverterFactory.create()).
                        build();
            }
            return retrofit;
        }
    }
}
