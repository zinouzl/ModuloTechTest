package com.example.modulotechtest.di

import com.example.modulotechtest.di.main.MainFragmentBuilderModule
import com.example.modulotechtest.di.main.MainModule
import com.example.modulotechtest.di.main.MainViewModelModule
import com.example.modulotechtest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuilderModule::class,
            MainModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}