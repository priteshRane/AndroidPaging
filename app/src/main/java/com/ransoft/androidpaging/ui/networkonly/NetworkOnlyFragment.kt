package com.ransoft.androidpaging.ui.networkonly

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
import com.ransoft.androidpaging.databinding.NetworkOnlyFragmentBinding
import javax.inject.Inject

class NetworkOnlyFragment : Fragment() {

    private lateinit var binding: NetworkOnlyFragmentBinding
    val previousRequestAdapter: PreviousRequestAdapter = PreviousRequestAdapter()
    @Inject
    lateinit var viewModel: NetworkOnlyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.network_only_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = previousRequestAdapter

        viewModel.getPreviousRequests().observe(requireActivity(), Observer {
            Log.d("API Frag", it.toString())
            previousRequestAdapter.submitList(it)
        })

        binding.floatingActionButton.setOnClickListener {

        }
    }
}