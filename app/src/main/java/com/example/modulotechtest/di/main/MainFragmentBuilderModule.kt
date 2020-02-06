package com.example.modulotechtest.di.main

import com.example.modulotechtest.ui.main.home.HomeFragment
import com.example.modulotechtest.ui.main.userfragment.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentBuilderModule {


    @ContributesAndroidInjector
    abstract fun contributeHomeFragment():HomeFragment


    @ContributesAndroidInjector
    abstract fun contributeUserFragment():UserFragment


}