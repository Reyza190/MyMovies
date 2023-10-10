package com.example.core.di

import com.example.core.data.source.FilmRepository
import com.example.core.domain.repository.IFilmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(filmRepository: FilmRepository): IFilmRepository

}