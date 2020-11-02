package com.pany.withrooms.viewmodel

import androidx.lifecycle.ViewModel
import com.pany.withrooms.injection.DaggerViewModelInjector
import com.pany.withrooms.injection.NetworkModule
import com.pany.withrooms.injection.ViewModelInjector

abstract class MyViewModel : ViewModel() {

    val injection : ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()


    init {
        when(this){
            is UsersViewModel ->injection.injection(this)
            is ReposViewModel ->injection.injection(this)

        }
    }
}
