package com.example.iwatch.features.details_movies_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iwatch.R
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.viewholder.MovieReviews
import com.example.iwatch.common.viewholder.TrailerImages
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_movie_details.*
import android.content.Intent
import android.net.Uri


class MovieDetailsActivity : AppCompatActivity() {

    private val builder = StringBuilder()
    val adapter = GroupAdapter<ViewHolder>()
    val adapter2 = GroupAdapter<ViewHolder>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar?.title = "Details"

        adapter.clear()
        adapter2.clear()

        val response = intent.extras.getParcelable<Result>("MovieDetails")
        trailersImagesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
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
        trailersImagesRecycler.adapter = adapter2
        if (response.reviews?.size == 0){
            reviewText.setText("No Reviews Yet")
        }else {
            response.reviews?.forEach {
                adapter.add(MovieReviews(it))
            }
        }

        if(response.MovieVedios?.size == 0){
            trailerText.setText("No Trailers Found")
        }else{
            response.MovieVedios?.forEach {
                adapter2.add(TrailerImages(it))
            }
        }

        adapter2.setOnItemClickListener { item, view ->
            showAlertDialog(item)
        }
    }

    private fun showAlertDialog(item: Item<ViewHolder>) {
        var myItem = item as TrailerImages
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Watch Trailer")
        builder.setMessage("Do you want to play this Trailer")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=${myItem.movieVediosResponse.key}")))

        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }
}
