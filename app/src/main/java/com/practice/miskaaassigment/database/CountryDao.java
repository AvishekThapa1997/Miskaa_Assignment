package com.practice.miskaaassigment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.practice.api.model.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Country> countries);

    @Query("SELECT * FROM countries")
    List<Country> getCountries();

    @Query("DELETE FROM countries WHERE name = :countryName")
    void deleteCountry(String countryName);

    @Query("SELECT * FROM countries WHERE name = :countryName")
    Country getCountry(String countryName);
}
