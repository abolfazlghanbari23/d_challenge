package com.example.challenge3.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.challenge3.R
import com.example.challenge3.base.BaseActivity
import com.example.challenge3.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun setupViews() {

    }


    override fun subscribe() {

    }

    override fun unSubscribe() {

    }
}