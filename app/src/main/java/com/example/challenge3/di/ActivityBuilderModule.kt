package com.example.challenge3.di

import com.example.challenge3.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ViewBuilderModule::class, ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity
}