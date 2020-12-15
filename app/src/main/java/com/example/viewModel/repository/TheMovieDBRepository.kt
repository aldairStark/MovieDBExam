package com.example.viewModel.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.viewModel.common.MyApp
import com.example.viewModel.retrofit.TheMovieDBClient
import com.example.viewModel.retrofit.TheMovieServices
import com.example.viewModel.retrofit.models.Movie
import com.example.viewModel.retrofit.models.PopularMoviesDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {
    var theMovieServices:TheMovieServices?=null
    var theMovieDBClient:TheMovieDBClient?=null
    var popularMovie:MutableLiveData<List<Movie>>? = null


    init {
        theMovieDBClient=TheMovieDBClient.instance
        theMovieServices=theMovieDBClient?.getTheMovieDBService()
        popularMovie=PopularMovies()
    }

    private fun PopularMovies(): MutableLiveData<List<Movie>>? {
            if (popularMovie==null){
                popularMovie= MutableLiveData<List<Movie>>()
            }
        val call: Call<PopularMoviesDataResponse>? = theMovieServices?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMoviesDataResponse>{
            override fun onFailure(call: Call<PopularMoviesDataResponse>, t: Throwable) {
              Toast.makeText(MyApp.instance,"Error en la operacion",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<PopularMoviesDataResponse>,
                response: Response<PopularMoviesDataResponse>
            ) {
               if (response.isSuccessful){
                   popularMovie?.value=response.body()?.results
               }
            }

        })


        return popularMovie
    }

}