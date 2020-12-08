package com.swapnil.myapplication.di.components;

import com.swapnil.myapplication.di.modules.NetworkServiceModule;
import com.swapnil.myapplication.di.modules.UserRepoModule;
import com.swapnil.myapplication.model.network.interfaces.NetworkServiceApi;
import com.swapnil.myapplication.model.network.service.NetworkService;
import com.swapnil.myapplication.model.repository.UserRepository;
import com.swapnil.myapplication.viewmodel.CountryViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkServiceModule.class})
public interface AppComponent {
    void injectNetworkServiceImpl(NetworkService service);
    void injectNetworkServiceAndCallback(UserRepository repository);
    void injectUserRepository(CountryViewModel model);
}
