package com.example.challenge3.ui.fragment.places

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.challenge3.R

class PlacesFragment : Fragment() {

    companion object {
        fun newInstance() = PlacesFragment()
    }

    private lateinit var viewModel: PlacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlacesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}