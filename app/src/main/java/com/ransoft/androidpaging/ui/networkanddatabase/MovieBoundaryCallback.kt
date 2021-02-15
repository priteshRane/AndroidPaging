package com.ransoft.androidpaging.ui.networkanddatabase

import android.util.Log
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.Movie
import com.ransoft.androidpaging.data.repositories.MovieRepository
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.NoInternetException
import javax.inject.Inject

class MovieBoundaryCallback @Inject constructor(val appDatabase: AppDatabase, val movieRepository: MovieRepository) : PagedList.BoundaryCallback<Movie>() {
    private val TAG = "Boundary Callback"
    private val PAGE_SIZE = 10

    override fun onItemAtEndLoaded(movie: Movie) {
        super.onItemAtEndLoaded(movie)

        Log.d(TAG, "Movie (Movie End): ${movie._id}, ${movie.name}, ${movie.genres}")

        Coroutines.main {
            val movieCount = appDatabase.movieDao().getMoviesCount()
            Log.d(TAG, "Movie (Count): ${movieCount}")
            var page: Int = (movieCount / 10) + 1
            Log.d(TAG, "Movie (Page value): $page")
            try {
            val movieResponse = movieRepository.getMovies(page, PAGE_SIZE)
            movieRepository.addMovies(movieResponse.body()?.movie!!)
            } catch (e: NoInternetException) {
                Log.d(TAG, "NoInternetException in MovieDataSource: $e")
            } catch (e: Exception) {
                Log.d(TAG, "Exception in MovieDataSource: $e")
            }
        }
    }
}