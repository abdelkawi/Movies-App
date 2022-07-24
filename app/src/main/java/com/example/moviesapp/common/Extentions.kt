package com.example.moviesapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit.HOURS

 fun isNetworkAvailable(context: Context): Boolean {
  val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
      else -> false
    }
  } else return false
}

 fun createWorkRequest() = PeriodicWorkRequestBuilder<CleanDatabaseWorker>(4, HOURS)
  .build()

 fun startWork(context: Context) {
  val work = createWorkRequest()
  WorkManager.getInstance(context).enqueueUniquePeriodicWork("CleanDatabase", ExistingPeriodicWorkPolicy.REPLACE, work)
}