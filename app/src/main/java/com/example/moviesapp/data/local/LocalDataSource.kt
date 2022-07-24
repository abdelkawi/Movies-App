package com.example.moviesapp.data.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(private val appDataBase: AppDataBase) {
  suspend fun getGenres() = appDataBase.genreDao().getGenres()


  suspend fun insertMovies(movies: List<MovieEntity>) {
    appDataBase.moviesDao().insertMovie(movies)
  }

  suspend fun insertGenres(genresItems: List<GenreEntity>){
    appDataBase.genreDao().insert(genresItems)
  }

  suspend fun getMovies(genreId: Int,filter:String): List<MovieEntity> {
    return appDataBase.moviesDao().loadCategoryMovies(genreId,filter)
  }
  suspend fun deleteMovies(){
    appDataBase.moviesDao().deleteAll()
  }
  suspend fun getMovieDetails(movieId:Int):MovieEntity{
    return appDataBase.moviesDao().getMovieDetails(movieId)
  }
  suspend fun getGenreById(genreId:Int):GenreEntity {
    return appDataBase.genreDao().getGenresById(genreId)
  }

  suspend fun deleteGenres(){
    appDataBase.genreDao().deleteAll()
  }

}