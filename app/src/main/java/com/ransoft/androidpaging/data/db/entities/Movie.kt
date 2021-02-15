package com.ransoft.androidpaging.data.db.entities

import androidx.annotation.NonNull
import androidx.room.*

@Entity
data class Movie(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var _id: String = "0",

    var name: String?,
    var year: Int?,
    var rating: Double?,
    var yourRating: Int?,
    var genres: String?,
    var description: String?,
    var duration: String?,
    var directors: String?,
    var writers: String?,
    var stars: String?,
    var posterUrl: String?,
    var createdAt: String?,
    var updatedAt: String?,

    @ColumnInfo(name = "version")
    var _v: Int?
)