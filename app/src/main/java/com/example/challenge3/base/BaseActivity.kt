package com.example.challenge3.base

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), ObserverView {

    override fun onStart() {
        super.onStart()
        setupViews()
    }
    protected abstract fun setupViews()

}