package com.swapnil.myapplication.model.pojo;

public class CountryModel {

    private String countryName;
    private String countryCapital;
    private String countryFlag;

    public CountryModel(String countryName, String countryCapital, String countryFlag) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
        this.countryFlag = countryFlag;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public String getCountryFlag() {
        return countryFlag;
    }
}
