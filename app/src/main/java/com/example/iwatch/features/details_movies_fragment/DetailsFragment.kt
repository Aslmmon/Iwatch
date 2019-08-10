package com.example.iwatch.features.details_movies_fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.iwatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = DetailsFragmentArgs.fromBundle(it)
            val movieDetails = safeArgs.resultMovies
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movieDetails.backdrop_path).into(backPosterImage)
            movieTitle.text = movieDetails.title
            val dateCut = movieDetails.release_date.split("-")
            filmDate.text = "(${dateCut[0]})"
            voteAverage.text = movieDetails.vote_average.toString() + "/10"
            ratingBar.rating = movieDetails.vote_average.toFloat()
            movieDescription.text =  movieDetails.overview
        }
    }
}
