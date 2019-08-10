package com.example.iwatch.features.upcoming_fragment

import com.example.iwatch.common.ApiService.service
import com.example.iwatch.features.top_rated_fragment.TopRatedMoviesContract
import com.robusta.tripsappteam1.common.base.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpcomingMoviesPresenter : UpcomingMoviesContract.presenter, BasePresenter<UpcomingMoviesContract.view>() {
    private lateinit var compositeDisposable: CompositeDisposable


    override fun getUpcomingMovies() {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            service.retrofitService.getUpcomingMovies(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    print(it)
                    getView()?.showDataInRecyclerView(it)
                }, {
                    print(it.message)
                })
        )
    }

    override fun getDetailsMovie(id: Int) {
    }

}