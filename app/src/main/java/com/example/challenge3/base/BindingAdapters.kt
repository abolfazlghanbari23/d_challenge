package com.example.challenge3.base

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.challenge3.R

object BindingAdapters {

    @BindingAdapter(value = ["img_url"], requireAll = true)
    @JvmStatic
    fun setRound(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.img_placeholder)
            error(R.drawable.img_placeholder)
            transformations(CircleCropTransformation())
            addHeader("Authorization", "fsq3fCfwHcUlRlaFWSLpiV9BjjaA+159taXV6rugBwV8yZE=")
        }
    }

}