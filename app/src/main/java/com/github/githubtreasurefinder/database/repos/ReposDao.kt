package com.pany.withrooms.database.repos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pany.withrooms.model.repos.Item
import com.pany.withrooms.model.repos.Owner
import com.pany.withrooms.model.repos.ReposFavotire


@Dao
interface ReposDao {


    @get:Query("SELECT * FROM item_table")
    val allRepos: List<Item>

    @get:Query("SELECT * FROM owner_table")
    val allOwners: LiveData<List<Owner>>


    @get: Query("SELECT * FROM repos_favorite")
    val allReposFavorite : List<ReposFavotire>

    @Query("DELETE FROM item_table")
    fun deletItem()


    @Query("DELETE FROM owner_table")
    fun deletOwner()

    @Query("DELETE FROM repos_favorite WHERE repo = :repo")
    fun deletFavorite(repo: String)

    @Query("UPDATE item_table SET favorite=:favorite WHERE name = :repo")
    fun updateSave(favorite: Boolean, repo : String)


    @Insert(onConflict = REPLACE)
    fun insertRepo(vararg items: Item)

    @Insert(onConflict = REPLACE)
    fun insertOwner(vararg items: Owner)

    @Insert(onConflict = REPLACE)
    fun insertFavorite(vararg items: ReposFavotire)
}
