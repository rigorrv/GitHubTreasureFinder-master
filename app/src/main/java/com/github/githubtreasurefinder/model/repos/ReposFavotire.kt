package com.pany.withrooms.model.repos

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos_favorite")
data class ReposFavotire(
        //val id : Int,
        @field:PrimaryKey
        val repo: String
)