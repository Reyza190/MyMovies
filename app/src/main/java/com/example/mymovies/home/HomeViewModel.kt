package com.example.mymovies.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.Film
import com.example.core.domain.usecase.FilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(filmUseCase: FilmUseCase): ViewModel() {
    private var title: MutableLiveData<String> = MutableLiveData()
    fun setSearch(query: String) {
        if (title.value == query) {
            return
        }
        title.value = query
    }

    val film = filmUseCase.getPopularFilm().asLiveData()
    val searchFilm: LiveData<Resource<List<Film>>> = Transformations
        .switchMap(title){
            filmUseCase.searchFilm(it).asLiveData()
        }

}