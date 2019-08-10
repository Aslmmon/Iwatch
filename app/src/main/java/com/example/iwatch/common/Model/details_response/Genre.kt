package com.example.iwatch.common.Model.details_response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val id: Int,
    val name: String
):Parcelable