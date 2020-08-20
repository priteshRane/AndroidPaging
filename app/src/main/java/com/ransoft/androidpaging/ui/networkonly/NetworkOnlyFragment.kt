package com.ransoft.androidpaging.ui.networkonly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ransoft.androidpaging.MyApplicatin
import com.ransoft.androidpaging.R
import com.ransoft.androidpaging.databinding.NetworkOnlyFragmentBinding
import javax.inject.Inject

class NetworkOnlyFragment : Fragment() {

    private lateinit var binding: NetworkOnlyFragmentBinding

    @Inject
    lateinit var viewModel: NetworkOnlyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApplicatin).appComponent.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.network_only_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}