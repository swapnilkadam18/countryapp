package com.swapnil.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.swapnil.myapplication.model.pojo.CountryModel;
import com.swapnil.myapplication.model.repository.UserRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class CountryViewModelTest{


    @InjectMocks
    CountryViewModel viewModel = new CountryViewModel();

    @Mock
    UserRepository repository = new UserRepository();

    @Rule
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();

    List<CountryModel> mockParsedData;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);


        Scheduler immediate = new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run,true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);

        mockParsedData = new ArrayList<>();
        mockParsedData.add(new CountryModel(
                "Afghanistan",
                "Kabul",
                "https://raw.githubusercontent.com/DevTides/countries/master/afg.png"));
    }

    @After
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void getCountryData(){
        viewModel.getCountryData();
        Assert.assertEquals(true,viewModel.isLoading.getValue());
        Assert.assertNotNull(viewModel.repository);
    }

    @Test
    public void onSuccess(){
        viewModel.onSuccess(mockParsedData);
        Assert.assertEquals(1,viewModel.dataFromRepo.getValue().size());
        Assert.assertEquals(false,viewModel.isLoading.getValue());
        Assert.assertEquals(false,viewModel.errorFromRepo.getValue());
    }

    @Test
    public void onFailure(){
        viewModel.onFailure();
        Assert.assertEquals(false,viewModel.isLoading.getValue());
        Assert.assertEquals(true,viewModel.errorFromRepo.getValue());
    }

}