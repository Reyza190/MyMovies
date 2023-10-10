package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class FilmEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "filmId")
    val filmId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)
