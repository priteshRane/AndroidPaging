package com.ransoft.androidpaging.sampleone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ransoft.androidpaging.MyApplicatin
import com.ransoft.androidpaging.R
import com.ransoft.androidpaging.databinding.SampleOneFragmentBinding
import javax.inject.Inject

class SampleOneFragment : Fragment() {

    private lateinit var binding: SampleOneFragmentBinding

    @Inject
    lateinit var viewModel: SampleOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApplicatin).appComponent.inject(this)
        binding =  DataBindingUtil.inflate(inflater, R.layout.sample_one_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.callPreviousRequestApi()
    }
}