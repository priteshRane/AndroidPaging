package com.ransoft.androidpaging.data.network.responses

import com.ransoft.androidpaging.data.db.entities.PreviousRequest

data class PreviousRequestReponse(
    val page: Int?,
    val totalPages: Int?,
    val pageSize: Int?,
    val totalPreviousRequests: Int?,
    val previousRequests: List<PreviousRequest>
)