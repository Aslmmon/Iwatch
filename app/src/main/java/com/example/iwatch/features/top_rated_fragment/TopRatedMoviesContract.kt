package com.example.iwatch.features.top_rated_fragment

import com.example.iwatch.common.Model.top_rated_movies_model.TopRatedMovies

interface TopRatedMoviesContract {

    interface presenter {
        fun getTopRatedMovies()
    }

    interface view {
        fun showDataInRecyclerView(it: TopRatedMovies)
    }
}