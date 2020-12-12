package com.example.viewmodel.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodel.repository.TheMovieDBRepository
import com.example.viewmodel.retrofit.models.Movie

class MoviesViewModel: ViewModel() {

    private var theMovieDBRepository:TheMovieDBRepository
    private var popularMovie: LiveData<List<Movie>>
    init {
        theMovieDBRepository=TheMovieDBRepository()
        popularMovie= theMovieDBRepository?.popularMovie!!
    }
    fun getPopularmovies():LiveData<List<Movie>>{
        return popularMovie
    }
}