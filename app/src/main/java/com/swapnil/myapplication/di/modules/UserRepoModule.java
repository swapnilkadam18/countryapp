package com.swapnil.myapplication.di.modules;

import com.swapnil.myapplication.model.repository.ViewModelImpl;
import com.swapnil.myapplication.viewmodel.CountryViewModel;

import dagger.Module;
import dagger.Provides;
@Module
public class UserRepoModule {

    @Provides
    static ViewModelImpl provideImpl(CountryViewModel viewModel){
        return viewModel;
    }


}
