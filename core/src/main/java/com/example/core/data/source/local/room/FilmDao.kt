package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun getFavoriteFilm(): Flow<List<FilmEntity>>

    @Query("SELECT * FROM film where filmId = :id")
    fun getFilmByid(id: String): Flow<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavFilm(film: FilmEntity)

    @Delete
    suspend fun deleteFilm(film: FilmEntity): Int
}