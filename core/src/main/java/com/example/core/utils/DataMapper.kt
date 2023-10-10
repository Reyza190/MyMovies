package com.example.core.utils

import com.example.core.data.source.local.entity.FilmEntity
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToDomain(result: List<ResultsItem>): Flow<List<Film>> {
        val filmList = ArrayList<Film>()
        result.map {
            val film = Film(
                filmId = it.id.toString(),
                title = it.title,
                image = it.posterPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                adult = it.adult,
                isFavorite = false
            )
            filmList.add(film)
        }
        return flowOf(filmList)
    }

    fun mapEntitiesToDomain(input: List<FilmEntity>): List<Film> =
        input.map {
            Film(
                filmId = it.filmId,
                title = it.title,
                image = it.image.toString(),
                overview = it.overview,
                releaseDate = it.releaseDate,
                adult = it.adult,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntities(input: Film) = FilmEntity(
        filmId = input.filmId,
        title = input.title,
        image = input.image,
        overview = input.overview,
        releaseDate = input.releaseDate,
        adult = input.adult!!,
        isFavorite = input.isFavorite!!
    )

    fun mapEntityToDomain(input: FilmEntity?) = Film(
        filmId = input?.filmId.toString(),
        title = input?.title.toString(),
        image = input?.image.toString(),
        overview = input?.overview.toString(),
        releaseDate = input?.releaseDate.toString(),
        adult = input?.adult,
        isFavorite = input?.isFavorite
    )
}