package com.example.iwatch.features.upcoming_fragment

import com.example.iwatch.common.Model.top_rated_movies_model.MoviesResponse
import com.example.iwatch.common.Model.top_rated_movies_model.Result

interface UpcomingMoviesContract {

    interface presenter {
        fun getUpcomingMovies()
    }

    interface view {
        fun showDataInRecyclerView(
            it: Result
        )
    }
}