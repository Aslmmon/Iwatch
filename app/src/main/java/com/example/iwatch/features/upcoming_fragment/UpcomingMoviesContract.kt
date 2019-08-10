package com.example.iwatch.features.upcoming_fragment

import com.example.iwatch.common.Model.top_rated_movies_model.MoviesResponse

interface UpcomingMoviesContract {

    interface presenter {
        fun getUpcomingMovies()
        fun getDetailsMovie(id: Int)
    }

    interface view {
        fun showDataInRecyclerView(
            it: MoviesResponse
        )
    }
}