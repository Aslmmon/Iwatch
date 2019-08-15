package com.example.iwatch.features.upcoming_fragment

import android.util.Log
import com.example.iwatch.common.ApiService.service
import com.example.iwatch.common.Constants
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.example.iwatch.features.top_rated_fragment.TopRatedMoviesContract
import com.robusta.tripsappteam1.common.base.presenter.BasePresenter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UpcomingMoviesPresenter : UpcomingMoviesContract.presenter, BasePresenter<UpcomingMoviesContract.view>() {
    private lateinit var compositeDisposable: CompositeDisposable


    private val UpcomingMovieObservable: Observable<Result>
        get() = service.retrofitService.getUpcomingMovies(1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { movies ->
                Observable.fromIterable(movies.results).subscribeOn(Schedulers.io())
            }

    override fun getUpcomingMovies() {
        compositeDisposable = CompositeDisposable()
        UpcomingMovieObservable.subscribeOn(Schedulers.io()).concatMap { trailers -> Constants.getMovieTrailers(trailers) }
            .concatMap { result ->
                Constants.getDetails(result)
            }.concatMap{ reviewsResult -> Constants.getMovieReviews(reviewsResult) }.observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Result> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(result: Result) {
                    Log.i("Upcoming", "data is  $result")
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