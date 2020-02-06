package com.example.modulotechtest.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.modulotechtest.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object AppModule{



    @Singleton
    @JvmStatic
    @Provides
    fun provideRequestOptions() = RequestOptions.placeholderOf(R.drawable.background_heater)
        .error(R.drawable.background).centerCrop()


    @Singleton
    @JvmStatic
    @Provides
    fun provideRequestManager(application: Application,requestOptions: RequestOptions) = Glide.with(application)
        .setDefaultRequestOptions(requestOptions)





}