package com.example.challenge3.ui.fragment.image

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.challenge3.BuildConfig
import com.example.challenge3.R
import com.example.challenge3.base.BaseFragment
import com.example.challenge3.databinding.FragmentImageBinding

class ImageFragment : BaseFragment<FragmentImageBinding>() {

    private lateinit var viewModel: ImageViewModel
    private lateinit var imageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            getBaseActivity().providerFactory
        )[ImageViewModel::class.java]

        val args = ImageFragmentArgs.fromBundle(requireArguments())
        imageUrl = args.imageUrl
    }
    override fun setupViews() {
        binding.ivFull.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.img_placeholder)
            error(R.drawable.img_placeholder)
            addHeader("Authorization", BuildConfig.API_KEY)
        }
    }

    override val layoutRes = R.layout.fragment_image

    override fun subscribe() {

    }

    override fun unSubscribe() {

    }


}