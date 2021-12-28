package com.example.challenge3.di

import com.example.challenge3.ui.ImageFragment
import com.example.challenge3.ui.fragment.detail.PlaceDetailFragment
import com.example.challenge3.ui.fragment.detail.PlaceDetailViewModel
import com.example.challenge3.ui.fragment.places.PlacesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePlacesFragment(): PlacesFragment

    @ContributesAndroidInjector
    abstract fun contributePlaceDetailFragment(): PlaceDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeImageFragment(): ImageFragment
}