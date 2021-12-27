package com.example.challenge3.di

import android.app.Application
import com.example.challenge3.App
import com.example.challenge3.di.viewmodel.ViewModelProviderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelProviderModule::class,
        ContextModule::class,
        ActivityBuilderModule::class,
        ServiceBuilderModule::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
