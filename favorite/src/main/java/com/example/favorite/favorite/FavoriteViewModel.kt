package com.example.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val filmUseCase: FilmUseCase): ViewModel(){
    fun getFav() = filmUseCase.getFavoriteFilm().asLiveData()
}