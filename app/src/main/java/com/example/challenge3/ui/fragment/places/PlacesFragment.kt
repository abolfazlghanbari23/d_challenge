package com.example.challenge3.ui.fragment.places

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge3.R
import com.example.challenge3.adapter.PlaceAdapter
import com.example.challenge3.base.BaseFragment
import com.example.challenge3.base.Extensions
import com.example.challenge3.base.Extensions.isBottomOfListReached
import com.example.challenge3.core.domain.Place
import com.example.challenge3.databinding.FragmentPlacesBinding

class PlacesFragment : BaseFragment<FragmentPlacesBinding>() {

    private lateinit var viewModel: PlacesViewModel
    private val placeAdapter = PlaceAdapter(object : PlaceAdapter.CallBack {
        override fun onItemClick(place: Place) {
            navigateToDetailsFragment(place.fsqId)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            getBaseActivity().providerFactory
        )[PlacesViewModel::class.java]

        viewModel.fetchPlaces(viewModel.pager.index, "1.283644,103.860753")
    }

    override fun setupViews() {

        binding.rvPlaces.apply {

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (viewModel.pager.isLoadingAllowed() && recyclerView.isBottomOfListReached()) {
                        viewModel.fetchPlaces(viewModel.pager.index, "1.283644,103.860753")
                    }
                }
            })
            layoutManager = LinearLayoutManager(context)
            adapter = placeAdapter
        }
    }

    override val layoutRes: Int
        get() = R.layout.fragment_places

    override fun subscribe() {

        viewModel.getAllPlaces().observe(viewLifecycleOwner, {
            placeAdapter.submitList(it)
        })

        viewModel.progressBarLiveData.observe(viewLifecycleOwner, {
            if (it) binding.pbLoading.visibility = View.VISIBLE
            else binding.pbLoading.visibility = View.GONE
        })

    }

    override fun unSubscribe() {

    }

    private fun navigateToDetailsFragment(placeId: String) {
        val action = PlacesFragmentDirections.actionPlacesFragmentToPlaceDetailFragment(placeId)
        Navigation.findNavController(binding.root).navigate(action)
    }
}