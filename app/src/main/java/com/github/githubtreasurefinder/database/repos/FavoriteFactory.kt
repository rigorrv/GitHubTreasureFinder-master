package com.pany.withrooms.database.repos

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.pany.withrooms.viewmodel.FavoriteViewModel
import java.lang.IllegalArgumentException

class FavoriteFactory (private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            val db = Room.databaseBuilder(activity.applicationContext, AppRepoDataBase::class.java, "repo_table").build()
            @Suppress("unused")
            return FavoriteViewModel(db.reposDao()) as T
        }
        throw IllegalArgumentException("Unchecked ViewModel class")
    }
}
