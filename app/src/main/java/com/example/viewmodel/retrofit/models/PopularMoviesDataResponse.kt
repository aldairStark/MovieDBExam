package com.example.viewModel.retrofit.models

data class PopularMoviesDataResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)