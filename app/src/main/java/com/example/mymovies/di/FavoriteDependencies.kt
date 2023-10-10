package com.example.mymovies.di

import com.example.core.domain.usecase.FilmUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteDependencies {
    fun filmUseCase(): FilmUseCase
}