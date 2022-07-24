package com.example.moviesapp.di

import com.example.moviesapp.data.repository.MoviesRepositoryImpl
import com.example.moviesapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {
  @Provides
  fun provideRepo(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository = moviesRepositoryImpl
}