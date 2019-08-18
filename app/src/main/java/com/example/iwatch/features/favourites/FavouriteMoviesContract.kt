package com.example.iwatch.features.favourites

import com.example.iwatch.common.Database.sourceMoviesDatabase
import com.robusta.tripsappteam1.common.Database.MovieFavourites

interface FavouriteMoviesContract {

    interface presenter {
        fun getFavourites(database: sourceMoviesDatabase)
    }

    interface view {
        fun showData(result: List<MovieFavourites>)

    }
}