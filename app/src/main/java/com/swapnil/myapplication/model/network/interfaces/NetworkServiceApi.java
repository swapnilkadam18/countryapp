package com.swapnil.myapplication.model.network.interfaces;

import com.swapnil.myapplication.model.pojo.CountryApiResponse;
import com.swapnil.myapplication.model.network.utils.NetworkUtils;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NetworkServiceApi {

    @GET(NetworkUtils.COUNTRIES_URL)
    Single<List<CountryApiResponse>> getCountriesResponse();

}
