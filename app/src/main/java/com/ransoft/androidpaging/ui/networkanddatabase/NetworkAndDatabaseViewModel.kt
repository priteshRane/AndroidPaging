package com.ransoft.androidpaging.ui.networkanddatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.Movie
import javax.inject.Inject

class NetworkAndDatabaseViewModel @Inject constructor(
    appDatabase: AppDatabase,
    movieBoundaryCallback: MovieBoundaryCallback
) : ViewModel() {
    private var personsLiveData: LiveData<PagedList<Movie>>

    init {
        val factory: DataSource.Factory<Int, Movie> =
            appDatabase.movieDao().getAllMoviesFromDB()

        val pagedListBuilder: LivePagedListBuilder<Int, Movie> = LivePagedListBuilder<Int, Movie>(
            factory,
            10
        )
        personsLiveData = pagedListBuilder.setBoundaryCallback(movieBoundaryCallback).build()
    }

    fun getPersonsLiveData() = personsLiveData
}