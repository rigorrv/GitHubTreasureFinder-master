package com.pany.withrooms.database.users

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query;
import com.pany.withrooms.model.users.Details
import com.pany.withrooms.model.users.Items


@Dao
interface UsersDao {

    @get:Query("SELECT * FROM items_table")
    val getItems: LiveData<List<Items>>


    @get:Query("SELECT * FROM details_table")
    val getUsers: LiveData<List<Details>>


    @Query("DELETE FROM items_table")
    fun delet()

    @Insert(onConflict = REPLACE)
    fun insert(vararg items: Items)

}
