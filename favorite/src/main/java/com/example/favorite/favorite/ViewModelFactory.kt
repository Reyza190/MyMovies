package com.example.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.FilmUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val filmUseCase: FilmUseCase) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(filmUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}