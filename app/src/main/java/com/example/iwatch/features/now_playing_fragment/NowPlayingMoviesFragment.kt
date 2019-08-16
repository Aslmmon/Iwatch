package com.example.iwatch.features.now_playing_fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.example.iwatch.R
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.viewholder.Movie
import com.example.iwatch.features.details_movies_fragment.MovieDetailsActivity
import com.example.iwatch.features.top_rated_fragment.NowPlayingMoviesContract
import com.example.iwatch.features.top_rated_fragment.NowPlayingMoviesPresenter
import com.example.iwatch.features.top_rated_fragment.TopRatedMoviesPresenter
import com.example.iwatch.features.upcoming_fragment.UpcomingMoviesPresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_now_playing_movies.*
import kotlinx.android.synthetic.main.fragment_top_rated.*


class NowPlayingMoviesFragment : Fragment(), NowPlayingMoviesContract.view {


    private lateinit var nowPlaying: NowPlayingMoviesPresenter
    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        adapter.clear()
        nowPlaying = NowPlayingMoviesPresenter()
        nowPlaying.setView(this@NowPlayingMoviesFragment)
        return inflater.inflate(R.layout.fragment_now_playing_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlaying.getNowPlaying()

        val mGridLayoutManager = GridLayoutManager(activity, 2)
        recyclerNowPlaying.layoutManager = mGridLayoutManager
        recyclerNowPlaying.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            goToDetailsFragment(item, view)
        }

    }

    private fun goToDetailsFragment(item: Item<ViewHolder>, view: View) {
        val movieResponse = item as Movie
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra("MovieDetails", movieResponse.response)
        context?.startActivity(intent)
    }

    override fun showDataInRecyclerView(
        it: Result
    ) {
        adapter.add(Movie(it))
    }
}
