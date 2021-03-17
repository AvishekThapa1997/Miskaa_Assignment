package com.practice.miskaaassigment.database.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.practice.api.model.Country;

import java.util.List;


public class Converters {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToList(String data) {
        return gson.fromJson(data, new TypeToken<List<String>>() {
        }.getType());
    }

    @TypeConverter
    public static String listToString(List<String> data) {
        return gson.toJson(data);
    }

    @TypeConverter
    public static List<Country.CountryLanguages> createLanguageList(String languages) {
        return gson.fromJson(languages, new TypeToken<List<Country.CountryLanguages>>() {
        }.getType());
    }

    @TypeConverter
    public static String fromLanguageList(List<Country.CountryLanguages> languages) {
        return gson.toJson(languages);
    }
}
