package com.ransoft.androidpaging.data.db.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreviousRequest(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var _id: String = "0",

    var ticketNo: String?,
    var raisedOn: String?,
    var status: String?,
    var createdAt: String?,
    var updatedAt: String?,

    @ColumnInfo(name = "version")
    var __v: Int?
)