package com.example.mymovies.di

import com.example.core.data.source.FilmRepository
import com.example.core.domain.usecase.FilmInteractor
import com.example.core.domain.usecase.FilmUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsUseCase(filmRepository: FilmRepository): FilmUseCase=FilmInteractor(filmRepository)
}