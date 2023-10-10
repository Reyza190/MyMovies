package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getFilm(): ListMovieResponse

    @GET("search/movie")
    suspend fun searchFilm(
        @Query("query") query: String
    ): ListMovieResponse
}