package com.example.core.domain.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@SuppressLint("ParcelCreator")
@VersionedParcelize
data class Film(
    val filmId: String,
    val title: String,
    val image: String?,
    val overview: String,
    val releaseDate: String,
    val adult: Boolean?,
    var isFavorite: Boolean?
): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}
