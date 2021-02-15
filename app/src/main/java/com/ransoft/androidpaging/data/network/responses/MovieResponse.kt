package com.ransoft.androidpaging.data.network.responses

import com.ransoft.androidpaging.data.db.entities.Movie

data class MovieResponse(
    var page: Int?,
    var totalPages: Int?,
    var pageSize: Int?,
    var totalMovies: Int?,
    var movie: List<Movie>
)