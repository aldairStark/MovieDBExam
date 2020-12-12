package com.example.viewmodel.retrofit.models

import com.example.viewmodel.retrofit.models.Movie

data class PopularMoviesDataResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)