package com.ransoft.androidpaging.data.db.entities

import androidx.annotation.NonNull
import androidx.room.*

@Entity
data class Item(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "answerId")
    var answer_id: Int = 0,

    @ColumnInfo(name = "questionId")
    var question_id: Int?,

    @ColumnInfo(name = "contentLicense")
    var content_license: String?,

    @ColumnInfo(name = "isAccepted")
    var is_accepted: Boolean?,

    var score: Int?,

    @ColumnInfo(name = "lastActivityDate")
    var last_activity_date: Int?,

    @ColumnInfo(name = "creationDate")
    var creation_date: Int?,

    @Embedded
    var owner: Owner?
)