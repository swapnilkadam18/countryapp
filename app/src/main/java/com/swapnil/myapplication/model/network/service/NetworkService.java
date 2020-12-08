package com.swapnil.myapplication.model.network.service;

import com.swapnil.myapplication.di.components.DaggerAppComponent;
import com.swapnil.myapplication.model.network.interfaces.NetworkServiceApi;
import com.swapnil.myapplication.model.pojo.CountryApiResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class NetworkService {

    @Inject
    public NetworkServiceApi api;

    @Inject
    public NetworkService() {
        DaggerAppComponent.create().injectNetworkServiceImpl(this);
    }

    public Single<List<CountryApiResponse>> getCountriesFromApi() {
        return api.getCountriesResponse();
    }

}
