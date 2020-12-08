package com.swapnil.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swapnil.myapplication.di.components.DaggerAppComponent;
import com.swapnil.myapplication.model.pojo.CountryModel;
import com.swapnil.myapplication.model.repository.UserRepository;
import com.swapnil.myapplication.model.repository.ViewModelImpl;

import java.util.List;

import javax.inject.Inject;

public class CountryViewModel extends ViewModel implements ViewModelImpl {

    public MutableLiveData<List<CountryModel>> dataFromRepo = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> errorFromRepo = new MutableLiveData<>();

    @Inject
    UserRepository repository;

    @Inject
    public CountryViewModel() {
        super();
        DaggerAppComponent.create().injectUserRepository(this);
    }

    public void getCountryData(){
        isLoading.setValue(true);
        repository.getDataFromApi(this);
    }

    @Override
    public void onSuccess(List<CountryModel> countryModelList) {
        dataFromRepo.setValue(countryModelList);
        isLoading.setValue(false);
        errorFromRepo.setValue(false);
    }

    @Override
    public void onFailure() {
        isLoading.setValue(false);
        errorFromRepo.setValue(true);
    }
}
