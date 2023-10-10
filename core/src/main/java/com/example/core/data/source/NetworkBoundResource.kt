package com.example.core.data.source

import com.example.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<com.example.core.data.Resource<ResultType>> = flow {
        emit(com.example.core.data.Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map {
                    com.example.core.data.Resource.Success(it)
                })
            }
            is ApiResponse.Error -> {
                emit(com.example.core.data.Resource.Error(apiResponse.errorMessage))
            }

            else -> {

            }
        }
    }


    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<com.example.core.data.Resource<ResultType>> = result
}