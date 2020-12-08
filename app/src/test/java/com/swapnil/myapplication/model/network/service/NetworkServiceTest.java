package com.swapnil.myapplication.model.network.service;

import com.swapnil.myapplication.model.pojo.CountryApiResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class NetworkServiceTest{

    @InjectMocks
    NetworkService networkService;

    private Single<List<CountryApiResponse>> apiResponse;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        List<CountryApiResponse> responseList = new ArrayList<>();
        apiResponse = Single.just(responseList);
    }

    @After
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void getCountriesFromApi(){
        Assert.assertNotNull(networkService.api);
        Assert.assertNotNull(networkService.getCountriesFromApi());
    }
}