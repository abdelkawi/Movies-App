package com.example.moviesapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "genres", primaryKeys = ["id","name"])
data class GenreEntity(
  @ColumnInfo(name = "id") val id: Int, @ColumnInfo(name = "name") val name: String
)