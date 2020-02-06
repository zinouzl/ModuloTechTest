package com.example.modulotechtest.di.main

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.modulotechtest.R
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
object MainModule {

    @JvmStatic
    @Provides
    fun provideUserDrawable(application: Application):Drawable{
        return ContextCompat.getDrawable(application, R.drawable.user_image)!!
    }




}