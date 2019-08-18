package com.example.iwatch.common.viewholder

import android.util.Log
import com.example.iwatch.R
import com.example.iwatch.common.Model.details_response.DetailsResponse
import com.example.iwatch.common.Model.details_response.Genre
import com.example.iwatch.common.Model.movie_vedio_response.MovieVediosResponse
import com.example.iwatch.common.Model.movie_vedio_response.MovieVediosResult
import com.example.iwatch.common.Model.reviews_response_movies.ResultReviews
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.robusta.tripsappteam1.common.Database.MovieFavourites
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.favourite_list_item.view.*
import kotlinx.android.synthetic.main.image_trailers.view.*
import kotlinx.android.synthetic.main.movie_layout.view.*
import kotlinx.android.synthetic.main.review_movie.view.*

class Movie(val response: Result) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.movie_layout
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + response.poster_path)
            .into(viewHolder.itemView.posterImage)
    }



}
class MovieReviews(val resultReviews: ResultReviews): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.review_movie
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.reviewText.text = resultReviews.content
        viewHolder.itemView.authorReview.text = resultReviews.author

    }
}
class TrailerImages(val movieVediosResponse: MovieVediosResult):Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.image_trailers
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Log.i("imageTrailer","https://img.youtube.com/vi/" + movieVediosResponse.key+"/sddefault.jpg")

        Picasso.get().load("https://img.youtube.com/vi/" + movieVediosResponse.key+"/sddefault.jpg")
            .into(viewHolder.itemView.imageTrailer)
    }
}

class FavouriteMovies(val favMovie:MovieFavourites):Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.favourite_list_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.favouriteText.text = favMovie.MovieName
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + favMovie.imageMovie)
            .into(viewHolder.itemView.imageFavourite)

    }

}