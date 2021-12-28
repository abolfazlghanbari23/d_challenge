package com.example.challenge3.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge3.R
import com.example.challenge3.adapter.CategoryAdapter
import com.example.challenge3.adapter.Pager
import com.example.challenge3.adapter.SliderAdapter
import com.example.challenge3.base.BaseFragment
import com.example.challenge3.core.domain.PlaceImage
import com.example.challenge3.core.domain.sub.Category
import com.example.challenge3.databinding.FragmentPlaceDetailBinding
import com.example.challenge3.ui.fragment.places.PlacesFragmentDirections
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
        val placeId = args.placeId
        viewModel.getPlaceDetails(placeId)
        viewModel.getPlaceImage(placeId)
    }

    override fun setupViews() {

        binding.tvTitle.text = getString(R.string.all_three_dots)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_place_detail

    override fun subscribe() {

        viewModel.progressBarLiveData.observe(viewLifecycleOwner, {
            if (it) binding.pbLoading.visibility = View.VISIBLE
            else binding.pbLoading.visibility = View.GONE
        })

        viewModel.placeDetailsLiveData.observe(viewLifecycleOwner, {
            binding.tvTitle.text = it.name
            binding.tvGeocode.text = it.geocodes.getGeocodePretty()
            binding.tvTimezone.text = it.timezone
            if (it.categories != null && it.categories.isNotEmpty()) {
                val categoryAdapter = CategoryAdapter(object : CategoryAdapter.CallBack {
                    override fun onClick(category: Category) {

                    }
                })
                binding.rvCategories.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = categoryAdapter
                }
            }

        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {

        })

        viewModel.placeImageLiveData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty())
                binding.vpImages.visibility = View.VISIBLE
            val sliderAdapter = SliderAdapter(it, object : SliderAdapter.CallBack {
                override fun onClick(placeImage: PlaceImage) {
                    val action =
                        PlaceDetailFragmentDirections.actionPlaceDetailFragmentToImageFragment(
                            placeImage.getImgUrl()
                        )
                    Navigation.findNavController(binding.root).navigate(action)
                }
            })
            binding.vpImages.adapter = sliderAdapter
        })

        viewModel.placeImageErrorLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun unSubscribe() {

    }


}