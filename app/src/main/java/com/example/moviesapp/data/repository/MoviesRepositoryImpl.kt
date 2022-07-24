package com.example.moviesapp.data.repository

import android.util.Log
import com.example.moviesapp.common.Result
import com.example.moviesapp.common.Result.Error
import com.example.moviesapp.common.Result.Success
import com.example.moviesapp.data.local.LocalDataSource
import com.example.moviesapp.data.remote.datasource.RemoteDataSource
import com.example.moviesapp.data.remote.response.GenresItem
import com.example.moviesapp.domain.HomeSection
import com.example.moviesapp.domain.Movie
import com.example.moviesapp.domain.fromLocal
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.domain.toGenreEntity
import com.example.moviesapp.domain.toLocal
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
  private val remoteDataSource: RemoteDataSource,
  private val localDataSource: LocalDataSource
) : MoviesRepository {


  override suspend fun getMovies(filter: String): Result<List<HomeSection>> {
    val moviesRes = remoteDataSource.getMovies(filter)
    val homeSection = arrayListOf<HomeSection>()
    return when (moviesRes) {
      is Error -> {
        Error(moviesRes.error)
      }
      is Success -> {
        when (val genresRes = remoteDataSource.getGenres()) {
          is Error -> {
            Log.d("error in genres", genresRes.error ?: "")
          }
          is Success -> {
            localDataSource.insertGenres(genresRes.data.genres?.map { it.toGenreEntity() } ?: emptyList())
            genresRes.data.genres?.forEach { genre ->
              val movies = localDataSource.getMovies(genre.id ?: 0, filter)
              if (movies.isNotEmpty()) {
                val genres = ArrayList<GenresItem>()
                homeSection.add(HomeSection(genre.name ?: "", movies.map {
                  it.genres.forEach {
                    val genre = localDataSource.getGenreById(it)
                    genres.add(GenresItem(name = genre.name, id = genre.id))
                  }
                  it.fromLocal(genres)
                }))
              }

            }
          }
        }
        localDataSource.insertMovies(moviesRes.data.results.map { it.toLocal(filter) })
        Success(homeSection)
      }
    }
  }

  override suspend fun getMovieDetails(movieId: Int): Result<Movie> {
    val localMovie = localDataSource.getMovieDetails(movieId)
    val genres = ArrayList<GenresItem>()
    localMovie.genres.forEach {
      val genre = localDataSource.getGenreById(it)
      genres.add(GenresItem(name = genre.name, id = genre.id))
    }
    return Success(localMovie.fromLocal(genres))
  }

  override suspend fun getOfflineMovies(filter: String): Result<List<HomeSection>> {
    val genres = localDataSource.getGenres()
    return if (genres.isNullOrEmpty()) {
      val homeSection = arrayListOf<HomeSection>()
      genres.forEach { genre ->
        val movies = localDataSource.getMovies(genre.id ?: 0, filter)
        if (movies.isNotEmpty()) {
          val genres = ArrayList<GenresItem>()
          homeSection.add(HomeSection(genre.name ?: "", movies.map {
            it.genres.forEach {
              val genre = localDataSource.getGenreById(it)
              genres.add(GenresItem(name = genre.name, id = genre.id))
            }
            it.fromLocal(genres)
          }))
        }
      }
      Success(homeSection)
    } else Error("Data Not Synced Please Connect to Internet")
  }

  override suspend fun deleteDatabase() {
    localDataSource.deleteGenres()
    localDataSource.deleteMovies()
  }
}