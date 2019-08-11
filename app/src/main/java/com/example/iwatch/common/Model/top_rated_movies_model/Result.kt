package com.example.iwatch.common.Model.top_rated_movies_model

import android.os.Parcelable
import com.example.iwatch.common.Model.details_response.DetailsResponse
import com.example.iwatch.common.Model.details_response.Genre
import com.example.iwatch.common.Model.reviews_response_movies.ResultReviews
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var genre: List<Genre>?,
    var reviews:List<ResultReviews>?
) : Parcelable