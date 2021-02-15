package com.ransoft.androidpaging.ui.databaseonly

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ransoft.androidpaging.MyApplication
import com.ransoft.androidpaging.R
import com.ransoft.androidpaging.data.db.entities.Movie
import com.ransoft.androidpaging.databinding.DatabaseOnlyFragmentBinding
import com.ransoft.androidpaging.ui.MovieAdapter
import com.ransoft.androidpaging.ui.MovieInterface
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.toast
import kotlinx.android.synthetic.main.database_only_fragment.*
import javax.inject.Inject

class DatabaseOnlyFragment : Fragment(), MovieInterface {
    private lateinit var binding: DatabaseOnlyFragmentBinding
    val movieAdapter: MovieAdapter =
        MovieAdapter(this)
    @Inject
    lateinit var viewModel: DatabaseOnlyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.database_only_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = movieAdapter

        viewModel.getPersonsLiveData().observe(requireActivity(), Observer {
            Log.d("API Frag", it.toString())
            movieAdapter.submitList(it)
        })

        fab_add_movie.setOnClickListener {
            val movie = Movie(
                "100",
                "Demo movie",
                1994,
                9.3,
                0,
                "Drama",
                "Demo description of movie 100",
                "2h 22min",
                "Frank Darabont",
                "Stephen King",
                "Frank Darabont, Tim Robbins",
                "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/the-shawshank-redemption-poster.jpg?alt=media&token=0b945c32-4d53-4eff-a389-46c019eab958",
                "2020-11-11T16:52:41.756Z",
                "2020-11-11T16:52:41.756Z",
                0
            )

            Coroutines.main {
                viewModel.addMovie(movie)
                requireActivity().toast("New movie added!")
            }
        }
    }

    override fun onDelete(view: View, movie: Movie) {
        Coroutines.main {
            viewModel.removeMovie(movie._id)
            requireActivity().toast("Movie removed!")
        }
    }
}