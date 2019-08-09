package com.example.iwatch.common.Model.top_rated_movies_model

data class TopRatedMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)