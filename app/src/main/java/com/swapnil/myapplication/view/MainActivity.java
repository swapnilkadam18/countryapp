package com.swapnil.myapplication.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.swapnil.myapplication.R;
import com.swapnil.myapplication.viewmodel.CountryViewModel;

public class MainActivity extends AppCompatActivity {

    private CountryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        viewModel.getCountryData();

        observeResponse();
    }

    private void observeResponse() {
        viewModel.dataFromRepo.observe(this, countryModelList -> {
            Log.e("TEST","countryModelList: "+countryModelList.size());
        });

        viewModel.isLoading.observe(this, isLoading -> {
            Log.e("TEST", "isLoading: "+isLoading);
        });

        viewModel.errorFromRepo.observe(this, isError -> {
            Log.e("TEST", "isError: "+isError);
        });
    }
}