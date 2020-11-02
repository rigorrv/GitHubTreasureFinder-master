package com.pany.withrooms.injection

import com.pany.withrooms.viewmodel.UsersViewModel
import com.pany.withrooms.viewmodel.ReposViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun injection(postListViewModel: UsersViewModel)
    fun injection(reposPostListViewModel: ReposViewModel)


    @Component.Builder
    interface Builder{
        fun build():ViewModelInjector
        fun networkModule(nerworkModule :NetworkModule):Builder
    }
}