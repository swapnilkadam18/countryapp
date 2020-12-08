package com.swapnil.myapplication.model.repository;

import com.swapnil.myapplication.model.pojo.CountryModel;

import java.util.List;

public interface ViewModelImpl {

    void onSuccess(List<CountryModel> countryModelList);

    void onFailure();
}
