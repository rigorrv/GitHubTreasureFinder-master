package com.pany.withrooms.database.repos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pany.withrooms.model.repos.ConverterReposJson
import com.pany.withrooms.model.repos.Item
import com.pany.withrooms.model.repos.Owner
import com.pany.withrooms.model.repos.ReposFavotire

@Database(
        entities = [Item::class, Owner::class, ReposFavotire::class],
        version = 2
)
@TypeConverters(ConverterReposJson::class)
abstract class AppRepoDataBase : RoomDatabase(){
    abstract fun reposDao(): ReposDao
}