package com.practice.miskaaassigment.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.practice.api.model.Country;
import com.practice.miskaaassigment.repository.AppRepository;

public class CountryDetailsViewModel extends ViewModel {
    private final AppRepository appRepository;
    private final MutableLiveData<Country> countryMutableLiveData = new MutableLiveData<>();

    public CountryDetailsViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public LiveData<Country> getCountryMutableLiveData() {
        return countryMutableLiveData;
    }

    public void getCountryDetails(String countryName) {
        appRepository.getCountryDetails(response -> {
            countryMutableLiveData.postValue(response.getData());
        }, countryName);
    }
}
