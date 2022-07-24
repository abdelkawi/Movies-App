package com.example.moviesapp.data.remote.api

import com.example.moviesapp.data.remote.response.GenreResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenreApi {
  @GET("/3/genre/movie/list?api_key=b70de8a7e7d449bca9758c31b3a11ab5&language=en-US")
  suspend fun getGenres(): Response<GenreResponse>
}