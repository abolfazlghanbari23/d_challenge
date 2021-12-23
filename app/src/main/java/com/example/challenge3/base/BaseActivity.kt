package com.example.challenge3.base

import com.example.challenge3.di.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), ObserverView {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    override fun onStart() {
        super.onStart()
        setupViews()
    }
    protected abstract fun setupViews()

}