package com.example.iwatch.common

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.iwatch.common.ApiService.service
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.common.viewholder.Movie
import com.example.iwatch.features.details_movies_fragment.MovieDetailsActivity
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Constants {
    private lateinit var compositeDisposable: CompositeDisposable

    companion object {


        fun getDetails(result: Result): Observable<Result> {
            return service.retrofitService.getDetailsofMovie(result.id.toString()).map { details ->
                result.genre = details.genres
                result
            }.subscribeOn(Schedulers.io())
        }

        fun getMovieReviews(result: Result): Observable<Result> {
            return service.retrofitService.getReviewsOfMovie(result.id.toString()).map { details ->
                result.reviews = details.results
                result
            }.subscribeOn(Schedulers.io())
        }

        fun getMovieTrailers(result: Result): Observable<Result> {
            return service.retrofitService.getTrailersOfMovie(result.id.toString()).map { trailers ->
                result.MovieVedios = trailers.results
                result
            }.subscribeOn(Schedulers.io())
        }

         fun goToDetailsActivity(context :Context,item: Item<ViewHolder>, view: View) {
            val movieResponse = item as Movie
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("MovieDetails", movieResponse.response)
            context?.startActivity(intent)
        }
    }
}