package com.swapnil.myapplication.di.modules;

import com.swapnil.myapplication.model.network.interfaces.NetworkServiceApi;
import com.swapnil.myapplication.model.network.utils.NetworkUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkServiceModule {

    @Singleton
    @Provides
    static NetworkServiceApi initRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkServiceApi.class);
    }
}
