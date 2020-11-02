package com.pany.withrooms.model.repos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owner_table")
data class Owner(
        @field:PrimaryKey
        val id: Int,
        val login: String,
        val avatar_url: String,
        val html_url: String,
        val type: String
)