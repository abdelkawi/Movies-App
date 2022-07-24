package com.example.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MoviesDao {

  @Query("SELECT * FROM movies WHERE filter = :filter and  genres like '%'  || :genreId || '%'")
  suspend fun loadCategoryMovies(genreId: Int,filter:String):List<MovieEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(movies: List<MovieEntity>)

  @Query("Select * from movies where id=:movieId")
  fun getMovieDetails(movieId:Int):MovieEntity

  @Query("Delete from movies")
  suspend fun deleteAll()
}