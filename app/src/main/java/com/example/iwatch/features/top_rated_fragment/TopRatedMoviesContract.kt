package com.example.iwatch.features.top_rated_fragment

import com.example.iwatch.common.Model.top_rated_movies_model.Result

interface TopRatedMoviesContract {

    interface presenter {
        fun getTopRatedMovies()
        fun getDetailsMovie(id: Int)
    }

    interface view {
        fun showDataInRecyclerView(
            it: Result
        )
    }
}