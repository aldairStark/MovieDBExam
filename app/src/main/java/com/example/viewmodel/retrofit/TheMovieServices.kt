package com.example.viewmodel.retrofit


import com.example.viewmodel.retrofit.models.PopularMoviesDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieServices {
    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesDataResponse>
}