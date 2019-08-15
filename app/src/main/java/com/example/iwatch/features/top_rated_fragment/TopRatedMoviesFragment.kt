package com.example.iwatch.features.top_rated_fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import com.example.iwatch.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.viewholder.Movie
import com.example.iwatch.features.details_movies_fragment.MovieDetailsActivity
import com.xwray.groupie.Item
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_top_rated.*


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
        (activity as AppCompatActivity).supportActionBar?.title = "Top Rated Movies"
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
    }

    override fun showDataInRecyclerView(
        it: Result
    ) {
        adapter.add(Movie(it))
    }
}
