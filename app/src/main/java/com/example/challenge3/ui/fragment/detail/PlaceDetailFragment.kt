package com.example.challenge3.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.challenge3.R
import com.example.challenge3.adapter.Pager
import com.example.challenge3.base.BaseFragment
import com.example.challenge3.databinding.FragmentPlaceDetailBinding
import com.example.challenge3.ui.fragment.places.PlacesViewModel

class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>() {

    private lateinit var viewModel: PlaceDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            getBaseActivity().providerFactory
        )[PlaceDetailViewModel::class.java]

        val args = PlaceDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.getPlaceDetails(args.placeId)
    }

    override fun setupViews() {

    }

    override val layoutRes: Int
        get() = R.layout.fragment_place_detail

    override fun subscribe() {

        viewModel.progressBarLiveData.observe(viewLifecycleOwner, {
            if (it) binding.pbLoading.visibility = View.VISIBLE
            else binding.pbLoading.visibility = View.GONE
        })

        viewModel.placeDetailsLiveData.observe(viewLifecycleOwner, {

        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun unSubscribe() {

    }


}