package com.example.moviesapp.domain

import com.example.moviesapp.data.local.GenreEntity
import com.example.moviesapp.data.local.MovieEntity
import com.example.moviesapp.data.remote.response.GenresItem
import com.example.moviesapp.data.remote.response.Movie

fun Movie.toLocal(filter: String): MovieEntity {
  return MovieEntity(
    id = this.id ?: 0,
    name = this.title ?: "",
    image = this.posterPath ?: "",
    genres = this.genreIds ?: emptyList(),
    filter = filter,
    description = this.overview ?: "",
    releaseDate =this.releaseDate?:""
  )
}


fun MovieEntity.fromLocal(genres: List<GenresItem>): com.example.moviesapp.domain.Movie {
  return com.example.moviesapp.domain.Movie(
    id = this.id ?: 0,
    image = this.image ?: "",
    name = this.name,
    description =this.description,
    genres = genres,
    releaseDate = this.releaseDate
  )
}

fun GenresItem.toGenreEntity(): GenreEntity {
  return GenreEntity(
    id = this.id ?: 0,
    name = this.name ?: ""
  )
}
