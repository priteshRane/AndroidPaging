package com.ransoft.androidpaging.sampleone.data.network.responses

import com.ransoft.androidpaging.sampleone.data.model.PreviousRequest

data class PreviousRequestReponse(
    val page: Int?,
    val totalPages: Int?,
    val pageSize: Int?,
    val totalPreviousRequests: Int?,
    val previousRequests: List<PreviousRequest>
)