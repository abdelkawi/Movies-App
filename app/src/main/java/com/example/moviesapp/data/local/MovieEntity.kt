package com.example.moviesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
  @PrimaryKey val id:Int,
  val name:String,
  val image:String,
  val genres:List<Int>,
  val filter:String,
  val description:String,
  val releaseDate:String
)