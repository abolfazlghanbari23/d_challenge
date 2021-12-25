package com.example.challenge3.di

import android.content.Context
import androidx.room.Room
import com.example.challenge3.core.data.AppDataSource
import com.example.challenge3.core.data.AppDatabase
import com.example.challenge3.core.data.AppRepository
import com.example.challenge3.core.usecase.*
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
    fun provideFormDataBase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val oldRequest = chain.request()
            val newRequestBuilder: Request.Builder = oldRequest.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .method(oldRequest.method, oldRequest.body)
            chain.proceed(newRequestBuilder.build())
        }).addInterceptor(loggingInterceptor).build()


    @Singleton
    @Provides
    fun provideAppRepository(
        appDataSource: AppDataSource,
        appDatabase: AppDatabase
    ) = AppRepository(appDataSource, appDatabase)

    @Singleton
    @Provides
    fun provideAppDataSource(retrofit: Retrofit): AppDataSource =
        retrofit.create(AppDataSource::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.foursquare.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    @Singleton
    @Provides
    fun provideDeletePlaceDb(repository: AppRepository) = DeletePlaceDb(repository)

    @Singleton
    @Provides
    fun provideGetPlacesDbUseCase(repository: AppRepository) = GetPlacesDbUseCase(repository)

    @Singleton
    @Provides
    fun provideGetPlacesServerUseCase(repository: AppRepository) =
        GetPlacesServerUseCase(repository)

    @Singleton
    @Provides
    fun provideGetPlaceDetailsUseCase(repository: AppRepository) =
        GetPlaceDetailsUseCase(repository)

    @Singleton
    @Provides
    fun provideSavePlacesDbUseCase(repository: AppRepository) = SavePlacesDbUseCase(repository)

}