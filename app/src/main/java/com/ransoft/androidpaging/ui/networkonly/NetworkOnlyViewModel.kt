package com.ransoft.androidpaging.ui.networkonly

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.entities.Movie
import com.ransoft.androidpaging.data.repositories.MovieRepository
import javax.inject.Inject

class NetworkOnlyViewModel @Inject constructor(
    val movieRepository: MovieRepository,
    val movieDataSource: MovieDataSource
): ViewModel() {
    var movieLiveData  : LiveData<PagedList<Movie>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        movieLiveData  = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Movie> {

        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return movieDataSource
            }
        }
        return LivePagedListBuilder<Int, Movie>(dataSourceFactory, config)
    }

    fun getMovies():LiveData<PagedList<Movie>> = movieLiveData
}