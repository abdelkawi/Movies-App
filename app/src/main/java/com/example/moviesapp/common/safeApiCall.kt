package com.example.moviesapp.common

import com.example.moviesapp.common.Result.Success
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response


suspend inline fun <reified T> safeApiCall(
  noinline call: suspend () -> Response<T>
): Result<T> {
  return try {
    val res = call()
    return when (res.isSuccessful) {
      true -> {
        Success(
          res.body() as T
        )
      }
      false -> {
        Result.Error(
          res.errorBody()?.toError()!!.status_message
        )
      }
    }
  } catch (e: Exception) {
    Result.Error(e.toString())
  }
}

fun ResponseBody.toError(): ApiError {
  return Gson().fromJson(this.charStream(), ApiError::class.java)
}