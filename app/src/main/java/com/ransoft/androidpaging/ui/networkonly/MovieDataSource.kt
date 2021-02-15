package com.ransoft.androidpaging.ui.networkonly

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ransoft.androidpaging.data.db.entities.Movie
import com.ransoft.androidpaging.data.repositories.MovieRepository
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.NoInternetException
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    val movieRepository: MovieRepository
) : PageKeyedDataSource<Int, Movie>() {

    var LOG_TAG_API = "API"
    var PAGE_SIZE = 10
    var FIRST_PAGE = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        Coroutines.main {
            try {
                val movieResponse =
                    movieRepository.getMovies(FIRST_PAGE, PAGE_SIZE)
                movieRepository.deleteAllMovies()
                movieRepository.addMovies(movieResponse.body()?.movie!!)
                movieRepository.getAllMovies()
                Log.d(LOG_TAG_API, movieRepository.getMoviesDataCount().toString())
                callback.onResult(movieResponse.body()?.movie!!, null, FIRST_PAGE + 1)
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in MovieDataSource: $e")
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in MovieDataSource: $e")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        Coroutines.main {
            try {
                val movieResponse =
                    movieRepository.getMovies(params.key, PAGE_SIZE)
                movieRepository.addMovies(movieResponse.body()?.movie!!)
                movieRepository.getAllMovies()
                Log.d(LOG_TAG_API, movieRepository.getMoviesDataCount().toString())
                if (movieResponse.body()?.page!! <=  movieResponse.body()?.totalPages!!) {
                    callback.onResult(movieResponse.body()?.movie!!, params.key + 1)
                }
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in MovieDataSource: $e")
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in MovieDataSource: $e")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        Coroutines.main {
            try {
                val movieResponse =
                    movieRepository.getMovies(params.key, PAGE_SIZE)
                movieRepository.addMovies(movieResponse.body()?.movie!!)
                movieRepository.getAllMovies()
                Log.d(LOG_TAG_API, movieRepository.getMoviesDataCount().toString())
                if (params.key > 1) {
                    callback.onResult(movieResponse.body()?.movie!!, params.key - 1)
                }
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in MovieDataSource: $e")
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in MovieDataSource: $e")
            }
        }
    }
}