package com.example.challenge3.di

import android.content.Context
import androidx.room.Room
import com.example.challenge3.core.data.AppDataSource
import com.example.challenge3.core.data.AppDatabase
import com.example.challenge3.core.data.AppRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideFormDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val oldRequest = chain.request()
                val newRequestBuilder: Request.Builder = oldRequest.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .method(oldRequest.method, oldRequest.body)
                chain.proceed(newRequestBuilder.build())
            })

        clientBuilder.addInterceptor(loggingInterceptor)

        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideAppRepository(
        appDataSource: AppDataSource,
        appDatabase: AppDatabase
    ): AppRepository = AppRepository(appDataSource, appDatabase)

    @Singleton
    @Provides
    fun provideAppDataSource(retrofit: Retrofit): AppDataSource {
        return retrofit.create(AppDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

}