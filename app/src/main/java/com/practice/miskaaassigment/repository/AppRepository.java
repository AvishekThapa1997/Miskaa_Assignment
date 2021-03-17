package com.practice.miskaaassigment.repository;

import androidx.annotation.NonNull;
import com.practice.api.CountryApi;
import com.practice.api.CountryApiClient;
import com.practice.api.model.Country;
import com.practice.miskaaassigment.database.CountryDao;
import com.practice.miskaaassigment.utility.Constants;
import com.practice.miskaaassigment.utility.ResultCallback;
import com.practice.miskaaassigment.utility.Status;
import com.practice.miskaaassigment.utility.ApiResponse;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {
    private final CountryDao countryDao;

    private AppRepository(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final CountryApi countryApi = CountryApiClient.getCountryApi();
    private static final Object mLock = new Object();
    private static AppRepository appRepository;

    public static AppRepository getInstance(CountryDao countryDao) {
        synchronized (mLock) {
            if (appRepository == null) {
                appRepository = new AppRepository(countryDao);
            }
        }
        return appRepository;
    }

    public void getCountries(ResultCallback<List<Country>> resultCallback) {
        countryApi.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@NonNull Call<List<Country>> call, @NonNull Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    resultCallback.result(
                            new ApiResponse<>(Status.SUCCESS, response.body())
                    );
                    executorService.execute(() -> countryDao.insert(response.body()));
                } else {
                    resultCallback.result(new ApiResponse<>(Status.FAILURE,null,
                            Constants.SOMETHING_WENT_WRONG));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Country>> call, @NonNull Throwable t) {
                executorService.execute(() -> {
                    List<Country> countries = countryDao.getCountries();
                    if(countries.isEmpty()){
                        if (t instanceof UnknownHostException) {
                            resultCallback.result(new ApiResponse<>(Status.FAILURE,null,Constants.NO_INTERNET_CONNECTION));
                        } else {
                            resultCallback.result(new ApiResponse<>(Status.FAILURE,null,Constants.SOMETHING_WENT_WRONG));
                        }
                    }else{
                        resultCallback.result(new ApiResponse<>(Status.FAILURE,countries,Constants.UNABLE_TO_UPDATE_DATA));
                    }
                });
            }
        });
    }

    public void deleteCountry(Country country){
        executorService.execute(() -> {
            countryDao.deleteCountry(country.getCountryName());
        });
    }
    public void getCountryDetails(ResultCallback<Country> resultCallback,String countryName){
        executorService.execute(() -> {
            Country country = countryDao.getCountry(countryName);
            resultCallback.result(new ApiResponse<>(Status.SUCCESS,country));
        });
    }
}
