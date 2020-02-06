package com.example.modulotechtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory
    @Inject constructor(
        val creators: MutableMap<Class<out  ViewModel>,Provider<ViewModel>>
    )
 : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T = creators[modelClass]?.get() as T
}