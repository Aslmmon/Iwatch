package com.example.iwatch.features.details_movies_fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iwatch.R
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.viewholder.MovieReviews
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    private val builder = StringBuilder()
    val adapter = GroupAdapter<ViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar?.title = "Details"

        adapter.clear()

        val response = intent.extras.getParcelable<Result>("MovieDetails")
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + response.backdrop_path).into(backPosterImage)
        movieTitle.text = response.title
        val dateCut = response.release_date.split("-")
        filmDate.text = "(${dateCut[0]})"
        voteAverage.text = response.vote_average.toString() + "/10"
        ratingBar.rating = response.vote_average.toFloat()
        movieDescription.text = response.overview
        response.genre?.forEach {
            builder.append(it.name + "|")
        }
        categories.text = builder.toString()
        reviewsRecycler.adapter = adapter
        if (response.reviews?.size == 0){
            reviewText.setText("No Reviews Yet")
        }
        response.reviews?.forEach {
            adapter.add(MovieReviews(it))
        }
    }
}
