package com.example.moviesapp.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(
	@Json(name="genres")
	val genres: List<GenresItem>? = null
)
