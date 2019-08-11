package com.example.iwatch.features.top_rated_fragment

import android.util.Log
import com.example.iwatch.common.ApiService.service
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.robusta.tripsappteam1.common.base.presenter.BasePresenter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class TopRatedMoviesPresenter : TopRatedMoviesContract.presenter, BasePresenter<TopRatedMoviesContract.view>() {
    private lateinit var compositeDisposable: CompositeDisposable

    private val MovieObservable: Observable<Result>
        get() = service.retrofitService.getTopRatedMovies(1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { movies ->
                Observable.fromIterable(movies.results).subscribeOn(Schedulers.io())
            }

    override fun getTopRatedMovies() {
        compositeDisposable = CompositeDisposable()
        MovieObservable.subscribeOn(Schedulers.io()).flatMap { result ->
            getDetails(result)
        }.flatMap { reviewsResult -> getMovieReviews(reviewsResult) }.observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Result> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(result: Result) {
                    Log.i("TopRated", "data is  $result")
                    getView()?.showDataInRecyclerView(result)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
    }


    private fun getDetails(result: Result): Observable<Result> {
        return service.retrofitService.getDetailsofMovie(result.id.toString()).map { details ->
            result.genre = details.genres
            result
        }.subscribeOn(Schedulers.io())
    }

    private fun getMovieReviews(result: Result): Observable<Result> {
        return service.retrofitService.getReviewsOfMovie(result.id.toString()).map { details ->
            result.reviews = details.results
            result
        }.subscribeOn(Schedulers.io())
    }


    override fun getDetailsMovie(id: Int) {
//        compositeDisposable.add(
//            service.retrofitService.getDetailsofMovie(id.toString())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { it ->
//                    Log.i("details", it.genres.toString())
//
//                })
    }


}