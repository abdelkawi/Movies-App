package com.example.moviesapp.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiError(
  val status_message: String? = null,
  val status_code: Int? = null,
  val success: Boolean? = null
) : Parcelable
