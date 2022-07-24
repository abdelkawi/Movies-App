package com.example.moviesapp.presentation

import androidx.lifecycle.ViewModel
import com.example.moviesapp.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
  suspend fun getOnlineMovies(filter: String) = moviesRepository.getMovies(filter)
  suspend fun getOfflineMovies(filter: String) = moviesRepository.getOfflineMovies(filter)
  suspend fun getMovieDetails(movieId:Int) = moviesRepository.getMovieDetails(movieId)
}