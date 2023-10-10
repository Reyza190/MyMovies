package com.example.favorite.di

import android.content.Context
import com.example.core.domain.usecase.FilmInteractor
import com.example.core.domain.usecase.FilmUseCase
import com.example.favorite.favorite.FavoriteFragment
import com.example.favorite.favorite.FavoriteViewModel
import com.example.mymovies.di.FavoriteDependencies
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Component(dependencies = [FavoriteDependencies::class])
interface FavoriteModule{

    fun inject(favoriteFragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteDependencies: FavoriteDependencies): Builder
        fun build(): FavoriteModule
    }

}