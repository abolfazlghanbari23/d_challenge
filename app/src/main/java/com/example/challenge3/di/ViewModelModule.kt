package com.example.challenge3.di

import androidx.lifecycle.ViewModel
import com.example.challenge3.di.viewmodel.ViewModelKey
import com.example.challenge3.ui.fragment.detail.PlaceDetailViewModel
import com.example.challenge3.ui.fragment.places.PlacesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlacesViewModel::class)
    abstract fun providePlacesViewModel(placesViewModel: PlacesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceDetailViewModel::class)
    abstract fun providePlaceDetailViewModel(placeDetailViewModel: PlaceDetailViewModel): ViewModel

}