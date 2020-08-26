package com.ransoft.androidpaging.data.db.entities;

import androidx.room.ColumnInfo

data class Owner(
    val reputation: Int?,

    @ColumnInfo(name = "userId")
    val user_id: Int?,

    @ColumnInfo(name = "userType")
    val user_type: String?,

    @ColumnInfo(name = "acceptRate")
    val accept_rate: Int?,

    @ColumnInfo(name = "profileImage")
    val profile_image: String?,

    @ColumnInfo(name = "displayName")
    val display_name: String?,

    val link: String?
)
