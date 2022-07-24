package com.example.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.data.local.AppDataBase
import com.example.moviesapp.data.local.GenreDao
import com.example.moviesapp.data.local.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
  @Provides
  fun appDatabase(@ApplicationContext context: Context): AppDataBase =
    Room.databaseBuilder(context, AppDataBase::class.java, "movies").allowMainThreadQueries().build()

  @Provides
  fun provideGenreDao(appDataBase: AppDataBase): GenreDao = appDataBase.genreDao()

  @Provides
  fun provideMoviesDao(appDataBase: AppDataBase): MoviesDao = appDataBase.moviesDao()
}