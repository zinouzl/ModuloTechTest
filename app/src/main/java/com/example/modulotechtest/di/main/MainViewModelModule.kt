package com.example.modulotechtest.di.main

import androidx.lifecycle.ViewModel
import com.example.modulotechtest.di.ViewModelKey
import com.example.modulotechtest.ui.main.home.HomeFragment
import com.example.modulotechtest.ui.main.home.HomeViewModel
import com.example.modulotechtest.ui.main.userfragment.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel):ViewModel




    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel):ViewModel


}