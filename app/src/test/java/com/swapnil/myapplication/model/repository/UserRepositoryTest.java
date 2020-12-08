package com.swapnil.myapplication.model.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.swapnil.myapplication.model.network.service.NetworkService;
import com.swapnil.myapplication.model.pojo.CountryApiResponse;
import com.swapnil.myapplication.model.pojo.CountryModel;
import com.swapnil.myapplication.viewmodel.CountryViewModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class UserRepositoryTest {

    @InjectMocks
    UserRepository repository;

    @Mock
    CountryViewModel model;

    @Mock
    NetworkService service;

    Single<List<CountryApiResponse>> responseSingle;

    List<CountryModel> mockParsedData;
    List<CountryApiResponse> mockExpectedRes;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final String wrongData = "/Users/swapnilkadam/Desktop/Android_Projects/MyWeather/app/src/main/assets/mock_wrong_network_response.json";
    private final String correctData = "/Users/swapnilkadam/Desktop/Android_Projects/MyWeather/app/src/main/assets/mock_network_response.json";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Scheduler immediate = new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);

        mockParsedData = new ArrayList<>();
        mockParsedData.add(new CountryModel(
                "Afghanistan",
                "Kabul",
                "https://raw.githubusercontent.com/DevTides/countries/master/afg.png"));
        mockExpectedRes = new ArrayList<>();

        model = new CountryViewModel();
        service = new NetworkService();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctResponse() {
        List<CountryApiResponse> apiResponses = findFile(correctData);
        responseSingle = Single.just(apiResponses);
        Mockito.when(repository.service.getCountriesFromApi()).thenReturn(responseSingle);

        repository.getDataFromApi(model);
        mockExpectedRes.addAll(apiResponses);
        CountryApiResponse response = mockExpectedRes.get(0);
        CountryModel countryModel = mockParsedData.get(0);
        Assert.assertNotNull(response.getName());
        Assert.assertEquals(response.getName(), countryModel.getCountryName());
        Assert.assertNotNull(response.getCapital());
        Assert.assertEquals(response.getCapital(), countryModel.getCountryCapital());
        Assert.assertNotNull(response.getFlagPNG());
        Assert.assertEquals(response.getFlagPNG(), countryModel.getCountryFlag());
        Assert.assertNotNull(repository.callback);
    }

    @Test
    public void errorResponse(){
        responseSingle = Single.error(new Throwable());
        Mockito.when(repository.service.getCountriesFromApi()).thenReturn(responseSingle);
        repository.getDataFromApi(model);
        Assert.assertNotNull(repository.callback);
    }

    private List<CountryApiResponse> findFile(String filePath) {
        List<CountryApiResponse> data = new ArrayList<>();
        File dir = new File(filePath);
        Type REVIEW_TYPE = new TypeToken<List<CountryApiResponse>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(dir));
            data = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
            return data; // prints to screen some values
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}