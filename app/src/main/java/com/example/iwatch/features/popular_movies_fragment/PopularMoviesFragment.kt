package com.example.iwatch.features.popular_movies_fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import com.example.iwatch.R
import com.example.iwatch.common.Constants
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.viewholder.Movie
import com.example.iwatch.features.details_movies_fragment.MovieDetailsActivity
import com.example.iwatch.features.top_rated_fragment.TopRatedMoviesPresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.fragment_top_rated.*


class PopularMoviesFragment : Fragment(), PopularMoviesContract.view {


    private lateinit var popularMovies: PopularMoviesPresenter
    val adapter = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        adapter.clear()
        popularMovies = PopularMoviesPresenter()
        popularMovies.setView(this@PopularMoviesFragment)


        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Top Rated Movies"
        popularMovies.getPopularMovies()


        val mGridLayoutManager = GridLayoutManager(activity, 2)
        recyclerPopular.layoutManager = mGridLayoutManager
        recyclerPopular.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            goToDetailsFragment(item, view)
        }
    }

    private fun goToDetailsFragment(item: Item<ViewHolder>, view: View) {
        this!!.activity?.let { Constants.goToDetailsActivity(it,item,view) }
    }

    override fun showDataInRecyclerView(
        it: Result
    ) {
        adapter.add(Movie(it))
    }


}
