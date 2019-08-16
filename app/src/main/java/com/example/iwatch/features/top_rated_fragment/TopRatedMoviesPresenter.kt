package com.example.iwatch.features.top_rated_fragment

import android.util.Log
import com.example.iwatch.common.ApiService.service
import com.example.iwatch.common.Constants
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.robusta.tripsappteam1.common.base.presenter.BasePresenter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

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
        MovieObservable.subscribeOn(Schedulers.io()).concatMap { trailers -> Constants.getMovieTrailers(trailers)}
            .doOnError { t -> print("doOnerror $t") }
            .concatMap { result ->
                Constants.getDetails(result)
            }.concatMap{ reviewsResult -> Constants.getMovieReviews(reviewsResult) }.observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Result> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(result: Result) {
                    Log.i("TopRated", "data is  $result")
                    getView()?.showDataInRecyclerView(result)
                }

                override fun onError(e: Throwable) {
                    Log.i("TopRated", "data is  $e.message")

                }

                override fun onComplete() {

                }
            })
    }





}