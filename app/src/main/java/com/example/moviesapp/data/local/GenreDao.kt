package com.example.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenreDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert( genres:List<GenreEntity>)

  @Query("Delete from genres")
  suspend fun deleteAll()

  @Query("Select * from genres")
  suspend fun getGenres():List<GenreEntity>

  @Query("Select * from genres where id =:id")
  suspend fun getGenresById(id:Int):GenreEntity
}