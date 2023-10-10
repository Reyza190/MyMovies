package com.example.core.di


import com.example.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NmIzYTE4MTIyYmQwNDAyNTgxMWIyYzk4MjhkODA0YSIsInN1YiI6IjY0ZGM1YWFiMzcxMDk3MDBlMjI3ZDcwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.HR1m5Mwd6rh9EJZjWjc40_xZ1tP9yMKXsUqS7bQS_Xw"
private const val BASE_URL = "https://api.themoviedb.org/3/"
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer $API_KEY")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

}