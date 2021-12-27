package com.example.challenge3.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.challenge3.BuildConfig
import com.example.challenge3.R

object BindingAdapters {

    @BindingAdapter(value = ["img_url"], requireAll = true)
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.img_placeholder)
            error(R.drawable.img_placeholder)
            addHeader("Authorization", BuildConfig.API_KEY)
        }
    }

}