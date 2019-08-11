package com.example.iwatch.common.Model.reviews_response_movies

data class ReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<ResultReviews>,
    val total_pages: Int,
    val total_results: Int
)