package com.example.iwatch.features.favourites

import android.util.Log
import com.example.iwatch.common.Database.sourceMoviesDatabase
import com.robusta.tripsappteam1.common.base.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavouriteMoviesPresenter : FavouriteMoviesContract.presenter, BasePresenter<FavouriteMoviesContract.view>() {
    private lateinit var compositeDisposable: CompositeDisposable

    override fun getFavourites(database: sourceMoviesDatabase) {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            database.moviesDao.getAllMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    Log.i("favourite", "result is $result")
                    getView()?.showData(result)
                }

        )

    }


}