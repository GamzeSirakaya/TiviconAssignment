package com.example.tiviconassignment.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiviconassignment.model.MovieList;
import com.example.tiviconassignment.model.Movies;
import com.example.tiviconassignment.network.APIService;
import com.example.tiviconassignment.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Movies>> movieList;

    public HomeViewModel() { //constructor
        movieList = new MutableLiveData<>();

    }

    public MutableLiveData<List<Movies>> getMovieListObserver() {
        return movieList;
    }

    public void makeApiCall() {
        APIService apiService = RetrofitInstance.getRetroClient().create(APIService.class);
        Call<MovieList> call = apiService.data();


        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieList.postValue(response.body().movies);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                movieList.postValue(null);
            }

        });


    }
}
