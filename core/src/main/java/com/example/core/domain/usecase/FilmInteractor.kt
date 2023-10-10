package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Film
import com.example.core.domain.repository.IFilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmInteractor @Inject constructor(private val filmRepository: IFilmRepository): FilmUseCase{
    override fun getPopularFilm(): Flow<Resource<List<Film>>>  = filmRepository.getPopularFilm()
    override fun searchFilm(query: String): Flow<Resource<List<Film>>> = filmRepository.searchFilm(query)
    override fun getFavoriteFilm(): Flow<List<Film>> = filmRepository.getFavorite()
    override fun getFilmById(id: String): Flow<Film>  = filmRepository.getFilmById(id)

    override suspend fun insertFavFilm(film: Film) = filmRepository.insertFavFilm(film)

    override suspend fun deleteFilm(film: Film): Int = filmRepository.deleteFilm(film)


}