package com.example.iwatch.common.Model.reviews_response_movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultReviews(
    val author: String,
    val content: String,
    val id: String,
    val url: String
) : Parcelable