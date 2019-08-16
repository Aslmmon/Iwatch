package com.example.iwatch.features.top_rated_fragment

import com.example.iwatch.common.Model.top_rated_movies_model.Result

interface NowPlayingMoviesContract {

    interface presenter {
        fun getNowPlaying()
    }

    interface view {
        fun showDataInRecyclerView(
            it: Result
        )
    }
}