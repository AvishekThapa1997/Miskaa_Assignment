package com.practice.api;

import com.practice.api.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApi {
    @GET("region/asia")
    public Call<List<Country>> getCountries();
}
