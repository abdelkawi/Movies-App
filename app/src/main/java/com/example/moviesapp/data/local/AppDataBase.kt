package com.example.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesapp.common.Converters

@Database(version = 1, entities = [GenreEntity::class, MovieEntity::class])
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
  abstract fun genreDao(): GenreDao
  abstract fun moviesDao(): MoviesDao
}