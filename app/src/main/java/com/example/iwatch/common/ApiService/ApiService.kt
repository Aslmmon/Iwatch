package com.example.iwatch.common.ApiService


import com.example.iwatch.common.Model.details_response.DetailsResponse
import com.example.iwatch.common.Model.movie_vedio_response.MovieVediosResponse
import com.example.iwatch.common.Model.reviews_response_movies.ReviewsResponse
import com.example.iwatch.common.Model.top_rated_movies_model.MoviesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://api.themoviedb.org"

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
     * Here Getting Top-Rated Movies
     * */
    @GET("/3/movie/top_rated?api_key=658680e409a5e9e11988f3e49361edae")
    fun getTopRatedMovies(@Query("page") id: Int): Observable<MoviesResponse>

    /**
     * Here Getting Upcoming Movies
     * */
    @GET("/3/movie/upcoming?api_key=658680e409a5e9e11988f3e49361edae")
    fun getUpcomingMovies(@Query("page") id: Int): Observable<MoviesResponse>


    /**
     * Here Getting Now Playing Movies
     * */
    @GET("/3/movie/now_playing?api_key=658680e409a5e9e11988f3e49361edae")
    fun getNowPlayingMovies(@Query("page") id: Int): Observable<MoviesResponse>

    /**
     * Here Getting PopularMovies
     * */
    @GET("/3/movie/popular?api_key=658680e409a5e9e11988f3e49361edae")
    fun getPopularMovies(@Query("page") id: Int): Observable<MoviesResponse>



    /**
     * Here Getting Details of Movie
     * */
    @GET("/3/movie/{movie_id}?api_key=658680e409a5e9e11988f3e49361edae")
    fun getDetailsofMovie(@Path("movie_id") movieId:String) : Observable<DetailsResponse>


    /**
     * Here Getting Reviews of Movies
     * */

    @GET("/3/movie/{movie_id}/reviews?api_key=658680e409a5e9e11988f3e49361edae&language")
    fun getReviewsOfMovie(@Path("movie_id") movieId:String) : Observable<ReviewsResponse>



    /**
     * Here Getting Trailers and Vedios of Movies
     * */
    @GET("/3/movie/{movie_id}/videos?api_key=658680e409a5e9e11988f3e49361edae")
    fun getTrailersOfMovie(@Path("movie_id") movieId:String) : Observable<MovieVediosResponse>

}

object service {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}