package com.pany.withrooms.model.users

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pany.withrooms.database.users.UsersDao
import com.pany.withrooms.model.users.Items

class UserRepository(private val usersDao: UsersDao) {

    val allUsers: LiveData<List<Items>> = usersDao.getItems

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userData: Items) {
        usersDao.insert(userData)
    }
}