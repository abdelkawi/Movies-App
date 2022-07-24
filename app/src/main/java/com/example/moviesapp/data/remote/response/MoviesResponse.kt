package com.example.moviesapp.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
  val page: Int,
  val total_pages: Int,
  val results: List<Movie>,
  val total_results: Int
)