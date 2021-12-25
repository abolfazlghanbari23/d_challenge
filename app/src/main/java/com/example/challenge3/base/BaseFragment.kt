package com.example.challenge3.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T : ViewDataBinding> : DaggerFragment(), ObserverView {
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        val rootView = binding.root as ViewGroup
        setupViews()
        return rootView
    }

    override fun onStart() {
        super.onStart()
        binding.lifecycleOwner = viewLifecycleOwner
        subscribe()
    }

    override fun onStop() {
        super.onStop()
        unSubscribe()
    }

    abstract fun setupViews()

    @get:LayoutRes
    abstract val layoutRes: Int

    protected fun getBaseActivity(): BaseActivity {
        return requireActivity() as BaseActivity
    }

}