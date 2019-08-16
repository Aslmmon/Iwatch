package com.example.iwatch.common.Database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Room
import com.robusta.tripsappteam1.common.Database.MovieFavourites
import io.reactivex.Completable
import io.reactivex.Single


class DatabaseRoom {
    @Dao
    interface MoviesDao {
        @Query("select * from MovieFavourites")
        fun getAllMovies(): Single<List<MovieFavourites>>


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertMovie( movieName: MovieFavourites) :Completable


        @Delete
        fun deleteMovie(movieName: MovieFavourites) :Completable
    }




}

@Database(entities = [MovieFavourites::class], version = 1)
abstract class sourceMoviesDatabase : RoomDatabase() {
    abstract val moviesDao: DatabaseRoom.MoviesDao

}


private lateinit var INSTANCE: sourceMoviesDatabase
fun getDatabase(context: Context): sourceMoviesDatabase {

    synchronized(sourceMoviesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                sourceMoviesDatabase::class.java
                , "Movies"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE

}
