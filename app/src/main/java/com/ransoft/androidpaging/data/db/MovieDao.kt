package com.ransoft.androidpaging.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ransoft.androidpaging.data.db.entities.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movieEntity: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM movie WHERE id = :id;")
    suspend fun removeMovie(id: String)

    @Query("SELECT COUNT(id) FROM movie")
    suspend fun getMoviesCount(): Int

    @Query("SELECT * FROM movie")
    fun getAllMoviesFromDB(): DataSource.Factory<Int, Movie>
}