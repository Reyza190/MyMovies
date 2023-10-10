package com.example.core.data.source.local

import com.example.core.data.source.local.entity.FilmEntity
import com.example.core.data.source.local.room.FilmDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val filmDao: FilmDao) {

    fun getFavoriteFilm(): Flow<List<FilmEntity>> = filmDao.getFavoriteFilm()

    fun getFilmById(id: String): Flow<FilmEntity> = filmDao.getFilmByid(id)

    suspend fun insertFavFilm(filmEntity: FilmEntity){
        filmDao.insertFavFilm(filmEntity)
    }

    suspend fun deleteFilm(filmEntity: FilmEntity) = filmDao.deleteFilm(filmEntity)

}