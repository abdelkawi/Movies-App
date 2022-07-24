package com.example.moviesapp.di

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.remote.api.GenreApi
import com.example.moviesapp.data.remote.api.MoviesApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

  @Provides
  @Singleton
  fun loggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
  }

  @Provides
  @Singleton
  fun okHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .connectTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .cache(null)
      .build()
  }

  @Provides
  @Singleton
  fun providesMoshi(): Moshi = Moshi.Builder().build()

  @Provides
  @Singleton
  fun getRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .client(okHttpClient)
      .build()
  }

  @Provides
  @Singleton
  fun getMoviesApiService(retrofit: Retrofit): MoviesApi {
    return retrofit.create(MoviesApi::class.java)
  }

  @Provides
  @Singleton
  fun getGenresApiService(retrofit: Retrofit): GenreApi {
    return retrofit.create(GenreApi::class.java)
  }
}