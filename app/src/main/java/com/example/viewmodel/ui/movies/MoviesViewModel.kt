package com.example.viewModel.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.viewModel.repository.TheMovieDBRepository
import com.example.viewModel.retrofit.models.Movie

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