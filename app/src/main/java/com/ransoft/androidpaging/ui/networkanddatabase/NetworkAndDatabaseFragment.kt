package com.ransoft.androidpaging.ui.networkanddatabase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ransoft.androidpaging.MyApplication
import com.ransoft.androidpaging.R
import com.ransoft.androidpaging.data.db.entities.Movie
import com.ransoft.androidpaging.databinding.NetworkAndDatabaseFragmentBinding
import com.ransoft.androidpaging.ui.MovieAdapter
import com.ransoft.androidpaging.ui.MovieInterface
import javax.inject.Inject

class NetworkAndDatabaseFragment : Fragment(), MovieInterface {

    private lateinit var binding: NetworkAndDatabaseFragmentBinding
    val movieAdapter: MovieAdapter =
        MovieAdapter(this)
    @Inject
    lateinit var viewModel: NetworkAndDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.network_and_database_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = movieAdapter

        viewModel.getPersonsLiveData().observe(requireActivity(), Observer {
            Log.d("API Frag", it.toString())
            movieAdapter.submitList(it)
        })
    }

    override fun onDelete(view: View, movie: Movie) {
        TODO("Not yet implemented")
    }
}