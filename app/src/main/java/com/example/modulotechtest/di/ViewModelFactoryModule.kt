package com.example.modulotechtest.di

import androidx.lifecycle.ViewModelProvider
import com.example.modulotechtest.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {


    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory
}