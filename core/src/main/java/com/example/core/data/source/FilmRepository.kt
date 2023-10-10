package com.example.core.data.source

import com.example.core.data.Resource
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.Film
import com.example.core.domain.repository.IFilmRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IFilmRepository {
    override fun getPopularFilm(): Flow<Resource<List<Film>>> =
        object : NetworkBoundResource<List<Film>, List<ResultsItem>>(), Flow<Resource<List<Film>>> {
            override fun loadFromNetwork(data: List<ResultsItem>): Flow<List<Film>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getPopularFilm()
            }

            @InternalCoroutinesApi
            override suspend fun collect(collector: FlowCollector<Resource<List<Film>>>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun searchFilm(query: String): Flow<Resource<List<Film>>> =
        object : NetworkBoundResource<List<Film>, List<ResultsItem>>(), Flow<Resource<List<Film>>> {
            override fun loadFromNetwork(data: List<ResultsItem>): Flow<List<Film>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.searchFilm(query)
            }

            @InternalCoroutinesApi
            override suspend fun collect(collector: FlowCollector<Resource<List<Film>>>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getFavorite(): Flow<List<Film>> {
        return localDataSource.getFavoriteFilm().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFilmById(id: String): Flow<Film> {

        return localDataSource.getFilmById(id).map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun insertFavFilm(film: Film) {
        val domainFilm = DataMapper.mapDomainToEntities(film)
        return localDataSource.insertFavFilm(domainFilm)
    }

    override suspend fun deleteFilm(film: Film): Int {
        val domainFilm = DataMapper.mapDomainToEntities(film)
        return localDataSource.deleteFilm(domainFilm)
    }


}