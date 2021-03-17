package com.practice.miskaaassigment.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.practice.api.model.Country;
import com.practice.miskaaassigment.database.converters.Converters;

@Database(entities = Country.class, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDao getCountryDao();
}
