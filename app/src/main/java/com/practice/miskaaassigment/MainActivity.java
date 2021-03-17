package com.practice.miskaaassigment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.practice.api.model.Country;
import com.practice.miskaaassigment.adapter.CountryAdapter;
import com.practice.miskaaassigment.adapter.itemdecorator.MarginItemDecorator;
import com.practice.miskaaassigment.database.DatabaseUtility;
import com.practice.miskaaassigment.databinding.ActivityMainBinding;
import com.practice.miskaaassigment.repository.AppRepository;
import com.practice.miskaaassigment.utility.Constants;
import com.practice.miskaaassigment.utility.RecyclerViewItemClickListener;
import com.practice.miskaaassigment.utility.Status;
import com.practice.miskaaassigment.viewmodelfactory.CustomViewModelFactory;
import com.practice.miskaaassigment.viewmodels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        countryAdapter = new CountryAdapter(new RecyclerViewItemClickListener() {
            @Override
            public void delete(Country country) {
                showDeleteConfirmationDialog(country);
            }

            @Override
            public void details(String name) {
                toDetailsActivity(name);
            }
        });
        setUpRecyclerView();
        mainViewModel = new ViewModelProvider(this,
                new CustomViewModelFactory(AppRepository.getInstance(
                        DatabaseUtility.getAppDatabase(getApplicationContext()).getCountryDao())
                )).
                get(MainViewModel.class);
        mainViewModel.getData();
        observeData();
    }

    private void toDetailsActivity(String name) {
        Intent intent = new Intent(this,CountryDetails.class);
        intent.putExtra(Constants.COUNTRY_NAME,name);
        startActivity(intent);
    }

    private void setUpRecyclerView() {
        activityMainBinding.countriesList.setHasFixedSize(true);
        activityMainBinding.countriesList.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.countriesList.addItemDecoration(
                new MarginItemDecorator(getResources().getDimension(R.dimen.recyclerview_item_top_margin)));
        activityMainBinding.countriesList.setAdapter(countryAdapter);
    }
    private void showDeleteConfirmationDialog(Country country){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK",((dialog, which) -> {
            mainViewModel.deleteCountry(country);
            dialog.dismiss();
        }));
        builder.setNegativeButton("CANCEL",((dialog, which) -> {
            dialog.dismiss();
        }));
        builder.setTitle("Are You Sure?");
        builder.show();
    }
    private void observeData() {
        mainViewModel.observableCountries().observe(this, (response -> {
            if (response.getStatus() == Status.LOADING) {
                activityMainBinding.loadingProgress.setVisibility(View.VISIBLE);
            } else if (response.getStatus() == Status.SUCCESS) {
                activityMainBinding.countriesList.setVisibility(View.VISIBLE);
                countryAdapter.submitList(response.getData());
                activityMainBinding.loadingProgress.setVisibility(View.GONE);
            } else {
                List<Country> datas = response.getData();
                if(datas != null){
                    activityMainBinding.countriesList.setVisibility(View.VISIBLE);
                    activityMainBinding.loadingProgress.setVisibility(View.GONE);
                    countryAdapter.submitList(datas);
                }
                Snackbar.make(activityMainBinding.getRoot(), response.getErrorMessage(), Snackbar.LENGTH_SHORT)
                        .show();
            }
        }));
    }
}