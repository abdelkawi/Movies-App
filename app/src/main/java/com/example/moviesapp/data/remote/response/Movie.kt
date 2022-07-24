package com.example.moviesapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
  @Json(name = "original_language")
  val originalLanguage: String? = null,

  @Json(name = "imdb_id")
  val imdbId: String? = null,

  @Json(name = "video")
  val video: Boolean? = null,

  @Json(name = "title")
  val title: String? = null,

  @Json(name = "backdrop_path")
  val backdropPath: String? = null,

  @Json(name = "revenue")
  val revenue: Int? = null,

  @Json(name = "genre_ids")
  val genreIds: List<Int>? = null,

  @Json(name = "popularity")
  val popularity: Double? = null,

  @Json(name = "production_countries")
  val productionCountries: List<ProductionCountriesItem?>? = null,

  @Json(name = "id")
  val id: Int? = null,

  @Json(name = "vote_count")
  val voteCount: Int? = null,

  @Json(name = "budget")
  val budget: Int? = null,

  @Json(name = "overview")
  val overview: String? = null,

  @Json(name = "original_title")
  val originalTitle: String? = null,

  @Json(name = "runtime")
  val runtime: Int? = null,

  @Json(name = "poster_path")
  val posterPath: String? = null,

  @Json(name = "spoken_languages")
  val spokenLanguages: List<SpokenLanguagesItem?>? = null,

  @Json(name = "production_companies")
  val productionCompanies: List<ProductionCompaniesItem?>? = null,

  @Json(name = "release_date")
  val releaseDate: String? = null,

  @Json(name = "vote_average")
  val voteAverage: Double? = null,

  @Json(name = "belongs_to_collection")
  val belongsToCollection: String? = null,

  @Json(name = "tagline")
  val tagline: String? = null,

  @Json(name = "adult")
  val adult: Boolean? = null,

  @Json(name = "homepage")
  val homepage: String? = null,

  @Json(name = "status")
  val status: String? = null
)

@JsonClass(generateAdapter = true)
data class ProductionCountriesItem(
  @field:SerializedName("iso_3166_1")
  val iso31661: String? = null,

  @field:SerializedName("name")
  val name: String? = null
)

@JsonClass(generateAdapter = true)
data class SpokenLanguagesItem(

  @field:SerializedName("name")
  val name: String? = null,

  @field:SerializedName("iso_639_1")
  val iso6391: String? = null,

  @field:SerializedName("english_name")
  val englishName: String? = null
)

@JsonClass(generateAdapter = true)
data class GenresItem(
  @Json(name ="name")
  val name: String? = null,
  @Json(name="id")
  val id: Int? = null
)

@JsonClass(generateAdapter = true)
data class ProductionCompaniesItem(

  @field:SerializedName("logo_path")
  val logoPath: String? = null,

  @field:SerializedName("name")
  val name: String? = null,

  @field:SerializedName("id")
  val id: Int? = null,

  @field:SerializedName("origin_country")
  val originCountry: String? = null
)
