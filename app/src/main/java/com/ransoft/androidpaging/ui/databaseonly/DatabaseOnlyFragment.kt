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
import com.ransoft.androidpaging.databinding.DatabaseOnlyFragmentBinding
import com.ransoft.androidpaging.ui.MovieAdapter
import com.ransoft.androidpaging.util.toast
import kotlinx.android.synthetic.main.database_only_fragment.*
import javax.inject.Inject

class DatabaseOnlyFragment : Fragment() {
    private lateinit var binding: DatabaseOnlyFragmentBinding
    val movieAdapter: MovieAdapter =
        MovieAdapter()
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
    }
}