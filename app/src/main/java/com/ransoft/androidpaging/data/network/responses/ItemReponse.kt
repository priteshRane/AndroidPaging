package com.ransoft.androidpaging.data.network.responses

import com.ransoft.androidpaging.data.db.entities.Item

data class ItemReponse(
    val has_more: Boolean?,
    val backoff: Int?,
    val quota_max: Int?,
    val quota_remaining: Int?,
    val items: List<Item>
)