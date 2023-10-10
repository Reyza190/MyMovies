package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmUseCase {
    fun getPopularFilm(): Flow<Resource<List<Film>>>
    fun searchFilm(query: String): Flow<Resource<List<Film>>>
    fun getFavoriteFilm(): Flow<List<Film>>
    fun getFilmById(id: String): Flow<Film>
    suspend fun insertFavFilm(film: Film)
    suspend fun deleteFilm(film: Film): Int
}