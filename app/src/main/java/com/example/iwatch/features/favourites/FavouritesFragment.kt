package com.example.iwatch.features.favourites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.iwatch.R
import com.example.iwatch.common.Database.getDatabase
import com.example.iwatch.common.viewholder.FavouriteMovies
import com.robusta.tripsappteam1.common.Database.MovieFavourites
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouritesFragment : Fragment(), FavouriteMoviesContract.view {


    private lateinit var favouritesPresenter: FavouriteMoviesPresenter

    val adapter = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favouritesPresenter = FavouriteMoviesPresenter()
        favouritesPresenter.setView(this@FavouritesFragment)
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Favourites"
        val database = getDatabase(activity!!.application)
        favouritesPresenter.getFavourites(database)

        recyclerFavourites.adapter = adapter

    }

    override fun showData(result: List<MovieFavourites>) {
        result.forEach {
            adapter.add(FavouriteMovies(it))
        }
    }
}
