package com.example.moviesapp.data.remote.datasource

import com.example.moviesapp.common.Result
import com.example.moviesapp.common.safeApiCall
import com.example.moviesapp.data.remote.api.GenreApi
import com.example.moviesapp.data.remote.api.MoviesApi
import com.example.moviesapp.data.remote.response.GenreResponse
import com.example.moviesapp.data.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
  private val genreApi: GenreApi,
  private val moviesApi: MoviesApi
) {
  suspend fun getGenres(): Result<GenreResponse> =
    withContext(Dispatchers.IO) {
      safeApiCall { genreApi.getGenres() }
    }

  suspend fun getMovies(filter:String): Result<MoviesResponse> = withContext(Dispatchers.IO) {
    safeApiCall {
      moviesApi.getMovies(filter)
    }
  }

  suspend fun getMovieDetails() = moviesApi.getMovieDetails()
}