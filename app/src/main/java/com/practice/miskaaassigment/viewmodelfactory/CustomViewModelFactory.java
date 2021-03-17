package com.practice.miskaaassigment.viewmodelfactory;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.practice.miskaaassigment.repository.AppRepository;
import com.practice.miskaaassigment.viewmodels.CountryDetailsViewModel;
import com.practice.miskaaassigment.viewmodels.MainViewModel;

public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final AppRepository appRepository;

    public CustomViewModelFactory(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(appRepository);
        } else if (modelClass.isAssignableFrom(CountryDetailsViewModel.class)) {
            return (T) new CountryDetailsViewModel(appRepository);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
