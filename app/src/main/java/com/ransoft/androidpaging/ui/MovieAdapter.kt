package com.ransoft.androidpaging.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ransoft.androidpaging.R
import com.ransoft.androidpaging.data.db.entities.Movie
import kotlinx.android.synthetic.main.movie_card.view.*

lateinit var context: Context
class MovieAdapter :
    PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        context = parent.context
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_card, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val movieEntity: Movie = getItem(position)!!
        Glide
            .with(context)
            .load(movieEntity.posterUrl)
            .centerCrop()
            .into(holder.moviePoster);

        holder.movieName.text = movieEntity.name
        holder.rating.text = movieEntity.rating.toString()
        holder.directors.text = movieEntity.directors
        holder.duration.text = movieEntity.duration
//        holder.moviePoster.setOnClickListener {
//            movieClickListener.onMovieClick(it, movieEntity)
//        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePoster = itemView.iv_movie_poster
        val movieName = itemView.tv_movie_name
        val rating = itemView.tv_rating
        val directors = itemView.tv_directors
        val duration = itemView.tv_duration
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie,
                                         newItem: Movie) = oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: Movie,
                                            newItem: Movie) = oldItem == newItem
        }
    }
}