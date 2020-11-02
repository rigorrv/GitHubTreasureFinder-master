package com.pany.withrooms.database.users

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pany.withrooms.model.users.ConverterJson
import com.pany.withrooms.model.users.Details
import com.pany.withrooms.model.users.Items

@Database(
        entities = [Items::class, Details::class],
        version = 1
)
@TypeConverters(ConverterJson::class)
abstract class AppUsersDataBase : RoomDatabase(){
    abstract fun itemsDao(): UsersDao
}