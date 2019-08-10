package com.example.iwatch.features.top_rated_fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

import com.example.iwatch.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_top_rated.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.Model.top_rated_movies_model.MoviesResponse
import com.example.iwatch.common.viewholder.Movie
import com.example.iwatch.features.details_movies_fragment.MovieDetailsActivity
import com.xwray.groupie.Item


class TopRatedMoviesFragment : Fragment(), TopRatedMoviesContract.view {


    private lateinit var topRatedPresenter: TopRatedMoviesPresenter
    val adapter = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter.clear()
        topRatedPresenter = TopRatedMoviesPresenter()
        topRatedPresenter.setView(this@TopRatedMoviesFragment)
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedPresenter.getTopRatedMovies()
        val mGridLayoutManager = GridLayoutManager(activity, 2)
        recycler.layoutManager = mGridLayoutManager
        recycler.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            goToDetailsFragment(item, view)
        }
    }

    private fun goToDetailsFragment(item: Item<ViewHolder>, view: View) {
        val movieResponse = item as Movie
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra("MovieDetails", movieResponse.response)
        context?.startActivity(intent)

//        val action = TopRatedMoviesFragmentDirections.actionDetails(movieResponse.response)
//        Navigation.findNavController(view).navigate(
//            action, NavOptions.Builder().setPopUpTo(
//                R.id.topRatedMoviesFragment,
//                true
//            ).build()
//        )
    }

    override fun showDataInRecyclerView(
        it: MoviesResponse
    ) {
        var myList: List<Result> = it.results

        myList.forEach {
            topRatedPresenter.getDetailsMovie(it.id)
            adapter.add(Movie(it))
        }

        Toast.makeText(activity, "data is ${myList[0].title}", Toast.LENGTH_SHORT).show()
    }
}
