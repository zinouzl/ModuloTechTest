package com.example.modulotechtest.di

import android.app.Application
import com.example.modulotechtest.BaseApp
import com.example.modulotechtest.utils.JsonUtils
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApp> {
    @Singleton
    fun getJsonUtils():JsonUtils


    @Component.Builder
    interface Builder {


        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}