package com.practice.api.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

@Entity(tableName = "countries")
public class Country {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String countryName;

    @SerializedName("capital")
    @ColumnInfo(name = "capital")
    private String countryCapital;

    @SerializedName("flag")
    @ColumnInfo(name = "flag")
    private String countryFlag;

    @SerializedName("region")
    @ColumnInfo(name = "region")
    private String countryRegion;

    @SerializedName("subregion")
    @ColumnInfo(name = "sub_region")
    private String countrySubRegion;

    @SerializedName("population")
    @ColumnInfo(name = "population")
    private Long countryPopulation;

    @SerializedName("borders")
    @ColumnInfo(name = "borders")
    private List<String> countryBorders;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public void setCountrySubRegion(String countrySubRegion) {
        this.countrySubRegion = countrySubRegion;
    }

    public void setCountryPopulation(Long countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public void setCountryBorders(List<String> countryBorders) {
        this.countryBorders = countryBorders;
    }

    public void setCountryLanguages(List<CountryLanguages> countryLanguages) {
        this.countryLanguages = countryLanguages;
    }

    @SerializedName("languages")
    @ColumnInfo(name = "languages")
    private List<CountryLanguages> countryLanguages;

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public String getCountrySubRegion() {
        return countrySubRegion;
    }

    public Long getCountryPopulation() {
        return countryPopulation;
    }

    public List<String> getCountryBorders() {
        return countryBorders;
    }

    public List<CountryLanguages> getCountryLanguages() {
        return countryLanguages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }

    public static class CountryLanguages {
        @SerializedName("name")
        private String languageName;

        public String getLanguageName() {
            return languageName;
        }

        @Override
        public String toString() {
            return languageName;
        }
    }
}
