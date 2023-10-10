package com.example.mymovies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Film
import com.example.core.domain.usecase.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val filmUseCase: FilmUseCase): ViewModel() {

    fun insertFavFilm(film: Film) = viewModelScope.launch {
        filmUseCase.insertFavFilm(film)
    }

    fun deleteFilm(film: Film) = viewModelScope.launch {
        filmUseCase.deleteFilm(film)
    }

    fun getFilmById(id: String) = filmUseCase.getFilmById(id).asLiveData()


}