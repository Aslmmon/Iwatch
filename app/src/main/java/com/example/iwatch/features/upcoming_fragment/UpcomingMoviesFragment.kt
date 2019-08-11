package com.example.iwatch.features.upcoming_fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import com.example.iwatch.R
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.Model.top_rated_movies_model.MoviesResponse
import com.example.iwatch.common.viewholder.Movie
import com.example.iwatch.features.details_movies_fragment.MovieDetailsActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_upcoming_movies.*


class UpcomingMoviesFragment : Fragment(), UpcomingMoviesContract.view {

    private lateinit var upcomingPresenter: UpcomingMoviesPresenter
    val adapter = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        upcomingPresenter = UpcomingMoviesPresenter()
        upcomingPresenter.setView(this@UpcomingMoviesFragment)
        return inflater.inflate(R.layout.fragment_upcoming_movies, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Upcoming Movies"

        upcomingPresenter.getUpcomingMovies()

        val mGridLayoutManager = GridLayoutManager(activity, 3)
        recyclerUpcoming.layoutManager = mGridLayoutManager
        recyclerUpcoming.adapter = adapter

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

    override fun showDataInRecyclerView(it: MoviesResponse) {

        var myList: List<Result> = it.results

        myList.forEach {
            adapter.add(Movie(it))
        }

    }
}
