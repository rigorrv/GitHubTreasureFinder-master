package com.pany.withrooms.model.repos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
        @field:PrimaryKey
        val id: Int,
        val name: String,
        val full_name: String,
        val html_url: String,
        val watchers_count: String,
        val score: String,
        val owner: Owner,
        val favorite : Boolean

)