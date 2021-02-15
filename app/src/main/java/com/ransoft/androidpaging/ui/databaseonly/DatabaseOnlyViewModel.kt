package com.ransoft.androidpaging.ui.databaseonly

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.Movie
import javax.inject.Inject

class DatabaseOnlyViewModel @Inject constructor(val appDatabase: AppDatabase) : ViewModel() {
    private var personsLiveData: LiveData<PagedList<Movie>>

    init {
        val factory: DataSource.Factory<Int, Movie> =
            appDatabase.movieDao().getAllMoviesFromDB()

        val pagedListBuilder: LivePagedListBuilder<Int, Movie> = LivePagedListBuilder<Int, Movie>(factory,
            10)
        personsLiveData = pagedListBuilder.build()
    }

    fun getPersonsLiveData() = personsLiveData

    suspend fun addMovie(movie: Movie) = appDatabase.movieDao().addMovie(movie)

    suspend fun removeMovie(id: String) = appDatabase.movieDao().removeMovie(id)
}