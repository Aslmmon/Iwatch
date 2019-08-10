package com.example.iwatch.features.top_rated_fragment

import android.util.Log
import com.example.iwatch.common.ApiService.service
import com.robusta.tripsappteam1.common.base.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TopRatedMoviesPresenter : TopRatedMoviesContract.presenter, BasePresenter<TopRatedMoviesContract.view>() {
    private lateinit var compositeDisposable: CompositeDisposable


    override fun getTopRatedMovies() {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            service.retrofitService.getTopRatedMovies(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    print(it)
                    getView()?.showDataInRecyclerView(it)
                },{
                    print(it.message)
                })
        )
    }


    override fun getDetailsMovie(id: Int) {
        compositeDisposable.add(
            service.retrofitService.getDetailsofMovie(id.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    Log.i("details", it.genres.toString())

                })
    }


}