package com.example.moviesapp.common

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.moviesapp.data.local.AppDataBase
import javax.inject.Inject


class CleanDatabaseWorker(context: Context, parameters: WorkerParameters) :
  CoroutineWorker(context, parameters) {

  @Inject lateinit var appDataBase: AppDataBase
  override suspend  fun doWork(): Result {
    appDataBase.moviesDao().deleteAll()
    appDataBase.genreDao().deleteAll()
    return Result.success()
  }
}