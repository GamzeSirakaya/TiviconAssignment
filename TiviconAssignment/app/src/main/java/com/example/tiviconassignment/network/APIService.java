package com.example.tiviconassignment.network;

import com.example.tiviconassignment.model.MovieList;
import com.example.tiviconassignment.model.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getMovies")
    Call<MovieList> data();


}
