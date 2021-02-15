package com.ransoft.androidpaging.data.repositories

import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.Movie
import com.ransoft.androidpaging.data.network.MyApiService
import com.ransoft.androidpaging.data.network.responses.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val myApiService: MyApiService,
    val appDatabase: AppDatabase
) {
    suspend fun getMovies(page: Int, pageSize: Int): Response<MovieResponse> {
        return myApiService.api.movieResponse(page, pageSize)
    }

    suspend fun addMovies(movieList: List<Movie>) {
        appDatabase.movieDao().addMovies(movieList)
    }

    fun getAllMovies() = appDatabase.movieDao().getAllMovies()

    suspend fun deleteAllMovies() = appDatabase.movieDao().deleteAllMovies()

    suspend fun getMoviesDataCount() = appDatabase.movieDao().getMoviesCount()
}