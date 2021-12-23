package com.example.challenge3.ui.fragment.places

import android.os.Bundle
import com.example.challenge3.R
import com.example.challenge3.base.BaseFragment
import com.example.challenge3.databinding.FragmentPlacesBinding

class PlacesFragment : BaseFragment<FragmentPlacesBinding>() {

    private lateinit var viewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupViews() {

    }

    override val layoutRes: Int
        get() = R.layout.fragment_places

    override fun subscribe() {

    }

    override fun unSubscribe() {

    }

}