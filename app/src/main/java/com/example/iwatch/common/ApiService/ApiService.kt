package com.example.iwatch.common.ApiService


import com.example.iwatch.common.Model.top_rated_movies_model.TopRatedMovies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList


private const val BASE_URL = "https://api.themoviedb.org"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


/**
 * Here Creating Singleton Retrofit instance to be used with each Api
 * */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface ApiService {


    /**
     * Here Getting Events from Graph Facebook Api  Using GET Method
     * */
    @GET("/3/movie/top_rated?api_key=658680e409a5e9e11988f3e49361edae")
    fun getTopRatedMovies(@Query("page") id: Int): Single<TopRatedMovies>


}

object service {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}