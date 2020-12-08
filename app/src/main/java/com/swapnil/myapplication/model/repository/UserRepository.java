package com.swapnil.myapplication.model.repository;

import android.util.Log;

import com.swapnil.myapplication.di.components.DaggerAppComponent;
import com.swapnil.myapplication.model.network.service.NetworkService;
import com.swapnil.myapplication.model.pojo.CountryApiResponse;
import com.swapnil.myapplication.model.pojo.CountryModel;
import com.swapnil.myapplication.viewmodel.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

//    NetworkService service = DaggerApiComponent.create().provideNetworkService();
    @Inject
    NetworkService service;

    ViewModelImpl callback;

//    NetworkManager manager = DaggerApiComponent.create().provideNetworkManager();

    public String test;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public UserRepository() {
        DaggerAppComponent.create().injectNetworkServiceAndCallback(this);
    }

    public void getDataFromApi(CountryViewModel viewModel) {
        callback = viewModel;
        disposable.add(
                service.getCountriesFromApi()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryApiResponse>>() {
                    @Override
                    public void onSuccess(@NonNull List<CountryApiResponse> expectedRes) {
                        getCountriesSuccess(expectedRes);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        getCountriesError();
                    }
                })

        );
    }

    private void getCountriesSuccess(List<CountryApiResponse> expectedRes) {
        List<CountryModel> model = new ArrayList<>();
        for(int i = 0; i < expectedRes.size(); i++){
            CountryApiResponse countryApiResponse = expectedRes.get(i);
            model.add(new CountryModel(countryApiResponse.getName(),
                    countryApiResponse.getCapital(),
                    countryApiResponse.getFlagPNG()));
        }
        disposable.dispose();
        callback.onSuccess(model);

    }

    private void getCountriesError() {
        disposable.dispose();
        callback.onFailure();
    }

    public void testDum(){
        callback.onFailure();
//        service.getCountriesFromApi();
//        api.getCountriesResponse();
    }
}
