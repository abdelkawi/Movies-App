package com.example.moviesapp.data.remote.api

import com.example.moviesapp.data.remote.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
  @GET("{filter}?api_key=b70de8a7e7d449bca9758c31b3a11ab5&language=en-US")
  suspend fun getMovies(@Path("filter") filter:String):Response<MoviesResponse>

  @GET("")
  suspend fun getMovieDetails()
}