package com.example.challenge3.di

import com.example.challenge3.service.ForegroundOnlyLocationService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeForegroundOnlyLocationService(): ForegroundOnlyLocationService
}