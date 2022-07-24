package com.example.moviesapp.domain.repository

import com.example.moviesapp.common.Result
import com.example.moviesapp.domain.HomeSection
import com.example.moviesapp.domain.Movie

interface MoviesRepository {
  suspend fun getMovies(filter:String):Result<List<HomeSection>>
  suspend fun getMovieDetails(movieId:Int):Result<Movie>
  suspend fun getOfflineMovies(filter: String):Result<List<HomeSection>>
  suspend fun deleteDatabase()
}