package com.practice.api;

import com.practice.api.utility.RetrofitUtility;

public class CountryApiClient {
    private static CountryApi countryApi;
    private static final Object mLock = new Object();
    public static CountryApi getCountryApi() {
        synchronized (mLock){
            if(countryApi == null){
                countryApi = RetrofitUtility.getRetrofit().create(CountryApi.class);
            }
        }
        return countryApi;
    }
}