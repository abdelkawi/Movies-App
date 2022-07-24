package com.example.moviesapp.domain

import com.example.moviesapp.data.remote.response.GenresItem

data class Movie(
  val id: Int,
  val image: String,
  val name: String,
  val description: String,
  val releaseDate:String,
  val genres: List<GenresItem>
)