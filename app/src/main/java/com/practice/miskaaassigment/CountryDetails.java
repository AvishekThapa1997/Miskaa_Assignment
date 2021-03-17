package com.practice.miskaaassigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;
import com.practice.api.model.Country;
import com.practice.miskaaassigment.database.DatabaseUtility;
import com.practice.miskaaassigment.databinding.CountryDetailsBinding;
import com.practice.miskaaassigment.repository.AppRepository;
import com.practice.miskaaassigment.utility.Constants;
import com.practice.miskaaassigment.utility.ImageUtility;
import com.practice.miskaaassigment.viewmodelfactory.CustomViewModelFactory;
import com.practice.miskaaassigment.viewmodels.CountryDetailsViewModel;

public class CountryDetails extends AppCompatActivity {
    private CountryDetailsBinding countryDetailsBinding;
    private CountryDetailsViewModel countryDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryDetailsBinding = CountryDetailsBinding.inflate(getLayoutInflater());
        setContentView(countryDetailsBinding.getRoot());
        countryDetailsViewModel = new ViewModelProvider(this, new CustomViewModelFactory(AppRepository
                .getInstance(DatabaseUtility.getAppDatabase(getApplicationContext()).getCountryDao())))
                .get(CountryDetailsViewModel.class);
        Intent intent = getIntent();
        String countryName = intent.getStringExtra(Constants.COUNTRY_NAME);
        countryDetailsViewModel.getCountryDetails(countryName);
        observeData();
    }

    private void observeData() {
        countryDetailsViewModel.getCountryMutableLiveData().observe(this, this::setUpUI);
    }

    private void setUpUI(Country country) {
        if (country != null) {
            countryDetailsBinding.tvCountryName.setText(country.getCountryName());
            countryDetailsBinding.tvCapitalName.setText(country.getCountryCapital());
            countryDetailsBinding.tvRegionName.setText(country.getCountryRegion());
            countryDetailsBinding.tvSubRegionName.setText(country.getCountrySubRegion());
            countryDetailsBinding.tvPopulationDetails.setText(String.valueOf(country.getCountryPopulation()));
            countryDetailsBinding.tvBorderDetails.setText(splitString(country.getCountryBorders().toString()));
            countryDetailsBinding.tvBorderDetails.setMovementMethod(new ScrollingMovementMethod());
            countryDetailsBinding.tvLanguageDetails.setText(splitString(country.getCountryLanguages().toString()));
            countryDetailsBinding.tvLanguageDetails.setMovementMethod(new ScrollingMovementMethod());
            ImageUtility.setImage(country.getCountryFlag(), countryDetailsBinding.countryImage, getApplicationContext());
        }else{
            Snackbar.make(countryDetailsBinding.getRoot(),Constants.SOMETHING_WENT_WRONG,Snackbar.LENGTH_SHORT).show();
        }
    }

    private String splitString(String data) {
        return data.substring(1, data.length() - 1);
    }
}