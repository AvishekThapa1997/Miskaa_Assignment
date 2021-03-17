package com.practice.miskaaassigment.viewmodels;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.practice.api.model.Country;
import com.practice.miskaaassigment.repository.AppRepository;
import com.practice.miskaaassigment.utility.ApiResponse;
import com.practice.miskaaassigment.utility.Status;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final AppRepository appRepository;
    private final MutableLiveData<ApiResponse<List<Country>>> mutableLiveDataCountries;

    public MainViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
        mutableLiveDataCountries = new MutableLiveData<>();
    }

    public void getData() {
        appRepository.getCountries(mutableLiveDataCountries::postValue);
    }

    public LiveData<ApiResponse<List<Country>>> observableCountries() {
        return mutableLiveDataCountries;
    }

    public void deleteCountry(Country country) {
        appRepository.deleteCountry(country);
        List<Country> countries = mutableLiveDataCountries.getValue().getData();
        if (countries != null) {
            List<Country> newList = new ArrayList<>(countries);
            newList.remove(country);
            mutableLiveDataCountries.setValue(new ApiResponse<>(Status.SUCCESS, newList));
        }
    }
}
